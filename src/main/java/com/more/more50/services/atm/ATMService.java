package com.more.more50.services.atm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.more.more50.dtos.ATMModelDto;
import com.more.more50.dtos.DTOMapper;
import com.more.more50.models.UserGeolocation;
import com.more.more50.models.atm.ATMModel;
import com.more.more50.models.atm.AtmRequest;
import com.more.more50.models.atm.Services;
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
            double distance = HelpService.CalculateDistance(geolocation.latitude, geolocation.longitude,
                                                            allAtms.get(i).getLatitude(),allAtms.get(i).getLongitude());
            if(distance <= geolocation.distance)
            {
                atmViewsInRadius.add(ViewMapper.AsView(allAtms.get(i), distance));
            }
        }
        System.out.println("ExecutionTime is " + (System.currentTimeMillis() - startTime) +"ms");
        return CompletableFuture.completedFuture(atmViewsInRadius);
    }

    @Async
    public CompletableFuture<List<ATMView>> SortByServices(AtmRequest request)
    {
        logger.info("start sortin by services");
        long start = System.currentTimeMillis();

        List<UUID> ids = request.getAtms().stream().map(ViewMapper::AsId).toList(); // преобразование из requst.atms в лист UUID
        List<ATMModel> models =new ArrayList<>();//модели по листу id
        
        for(int i =0; i < ids.size(); i++)
        {
            ATMModel model = repo.findById(ids.get(i)).get();
            model.setDistance(request.getAtms().get(i).getDistance());
            models.add(model);
        }

        List<ATMModel> modelsWithReq = new ArrayList<>(); //лист для моделей, которые подходят по запросам
        List<ATMView> result = new ArrayList<>(); // итоговый результат ф-ции

        int reqServices = 0;

        if(request.isBlindReq()) reqServices++;
        if(request.isNfcForBankCardsReq()) reqServices++;
        if(request.isQrReadReq()) reqServices++; 
        if(request.isSupportsChargeRubReq()) reqServices++;
        if(request.isSupportsEurReq()) reqServices++;
        if(request.isSupportsRubReq()) reqServices++;
        if(request.isSupportsUsdReq()) reqServices++;
        if(request.isWheelchairReq()) reqServices++;
        System.out.println(reqServices);
        System.out.println();

        // если в зарпосе не указаны требования, то сортируем просто по удаленности
        if(reqServices == 0){
                        
            Collections.sort(request.getAtms(), Comparator.comparing(ATMView::getDistance));

            result = request.getAtms();
            logger.info("sorting by services completed in "+(System.currentTimeMillis() - start)+"ms");
            return CompletableFuture.completedFuture(result);
        }

        for(int i = 0; i < models.size(); i ++)
        {
            Services services = models.get(i).getServices();
            ATMModel mod = models.get(i);
            int req = mod.getReqServices();
            //проверка на то подходит ли банкомат по требованиям
            if(services.blind.isServiceActivity() && services.blind.isServiceCapability() 
                    && request.isBlindReq()) req++;

            if(services.nfcForBankCards.isServiceActivity() && services.nfcForBankCards.isServiceCapability() 
                    && request.isNfcForBankCardsReq()) req++;

            if(services.qrRead.isServiceActivity() && services.qrRead.isServiceCapability() 
                    && request.isQrReadReq()) req++; 

            if(services.supportsChargeRub.isServiceActivity() && services.supportsChargeRub.isServiceCapability() 
                    && request.isSupportsChargeRubReq()) req++;

            if(services.supportsEur.isServiceActivity() && services.supportsEur.isServiceCapability() 
                    && request.isSupportsEurReq()) req++;

            if(services.supportsRub.isServiceActivity() && services.supportsRub.isServiceCapability() 
                    && request.isSupportsRubReq()) req++;

            if(services.supportsUsd.isServiceActivity() && services.supportsUsd.isServiceCapability() 
                    && request.isSupportsUsdReq()) req++;

            if(services.wheelchair.isServiceActivity() && services.wheelchair.isServiceCapability() 
                    && request.isWheelchairReq()) req++;

                    mod.setReqServices(req);
            models.set(i, mod);
        }
        Collections.sort(models, Comparator.comparing(ATMModel::getReqServices));

        for(int i = 0; i < models.size(); i++)
        {
            System.out.println(models.get(i).getReqServices()); 
            if(models.get(i).getReqServices() < reqServices) continue;  
            result.add(ViewMapper.AsAtmViewS(models.get(i)));
            
        }

        Collections.sort(result, Comparator.comparing(ATMView::getDistance));

        logger.info("sorting by services completed in "+(System.currentTimeMillis() - start)+"ms");
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<ATMModelDto> getModelById(UUID id)
    {
        ATMModelDto dto = DTOMapper.AsDto(repo.findById(id).get());
        return CompletableFuture.completedFuture(dto);
    }
}
