package com.more.more50.services.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.more.more50.dtos.ATMModelDto;
import com.more.more50.models.UserGeolocation;
import com.more.more50.models.atm.ATMModel;
import com.more.more50.repos.ATMRepo;
import com.more.more50.services.HelpService;
import com.more.more50.views.ATMView;
import com.more.more50.views.ViewMapper;

@EnableAsync
@Service
public class ATMService 
{
    @Autowired
    private ATMRepo repo;
    private static Logger logger = LoggerFactory.getLogger(ATMService.class);

    @Async
    public CompletableFuture<List<ATMView>> GetATMsInRadius(UserGeolocation geolocation) //для возвращения
    {
        long startTime = System.currentTimeMillis();
        logger.info("Stared filtrating by radius");
        List<ATMView> atmViewsInRadius = new ArrayList<>();
        List<ATMModel> allAtms = repo.findAll();
        
        for(int i = 0; i < allAtms.size(); i++)
        {
            double distance = HelpService.CalculateDistance(allAtms.get(i).getLatitude(), allAtms.get(i).getLongitude(),
                                                             geolocation.latitude, geolocation.longitude);
            if(distance <= geolocation.distance)
            {
                atmViewsInRadius.add(ViewMapper.AsView(allAtms.get(i), distance));
            }
        }
        System.out.println("ExecutionTime is " + (System.currentTimeMillis() - startTime) +"ms");
        return CompletableFuture.completedFuture(atmViewsInRadius);
    }
}
