package com.more.more50.services.office;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.more.more50.models.UserGeolocation;
import com.more.more50.models.office.OfficeModel;
import com.more.more50.repos.OfficeRepo;
import com.more.more50.services.HelpService;
import com.more.more50.views.ATMView;
import com.more.more50.views.OfficeView;
import com.more.more50.views.ViewMapper;

@Service
@EnableAsync
public class OfficeService {
    @Autowired
    private OfficeRepo repo;

    private final Logger logger = LoggerFactory.getLogger(OfficeService.class);

    @Async
    public CompletableFuture<List<OfficeView>> getOfficeViews(UserGeolocation geolocation)
    {
        logger.info("Stated getting officeViews");
        long time = System.currentTimeMillis();
        List<OfficeModel> models = repo.findAll();
        List<OfficeView> views = new ArrayList<>();

        for(int i = 0; i < models.size(); i++)
        {
            double distance = HelpService.CalculateDistance(geolocation.latitude, geolocation.longitude,
                                                            models.get(i).getLatitude(), models.get(i).getLongitude());
            if(distance <= geolocation.distance)
            {
                views.add(ViewMapper.asOfficeView(models.get(i), distance));
            }
        }
        logger.info("Completed creating OfficeViews in "+(System.currentTimeMillis() - time) + "ms");
        return CompletableFuture.completedFuture(views);
    } 

    @Async
    public CompletableFuture<List<ATMView>> sortByRequest()
    {   

        return null;
    }
}
