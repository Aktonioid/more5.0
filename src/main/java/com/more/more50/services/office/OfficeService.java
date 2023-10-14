package com.more.more50.services.office;

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

import com.more.more50.dtos.DTOMapper;
import com.more.more50.dtos.OfficeModelDto;
import com.more.more50.models.UserGeolocation;
import com.more.more50.models.office.OfficeModel;
import com.more.more50.models.office.OfficeRequest;
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
    public CompletableFuture<List<OfficeView>> sortByRequest(OfficeRequest request)
    {   
        logger.info("Sorting offices by request");
        List<OfficeModel> models = new ArrayList<>();
        for(int i =0; i < request.offices.size(); i++)
        {
            OfficeModel model =repo.findById(request.offices.get(i).getId()).get();
            model.setDistance(request.offices.get(i).getDistance());
            model.setWorkload(GenerateWorkload.workload());
            models.add(model);
        }
        List<OfficeView> views;
        
        if(request.isIndividual)
        {
            views = individual(request, models);
            return CompletableFuture.completedFuture(views);     
        }
        
        views = notIndividual(request, models);
        logger.info("End sorting offices");
        return CompletableFuture.completedFuture(views);
    }

    private static List<OfficeView> individual(OfficeRequest request, List<OfficeModel> models)
    {
        List<OfficeView> views = new ArrayList<>();
        if(request.isRampReq && request.isRko)
        {
            for(int i = 0; i < models.size();i++)
            {
                if(models.get(i).isHasRamp() && models.get(i).isRko() && models.get(i).getOpenHoursIndividual().size()>1)
                {
                    views.add(ViewMapper.asOfficeView(models.get(i), models.get(i).getDistance()));
                }
            }
        }   
        else if(request.isRampReq)
        {
            for(int i = 0; i < models.size();i++)
            {
                if(models.get(i).isHasRamp() && models.get(i).getOpenHoursIndividual().size()>1)
                {
                    views.add(ViewMapper.asOfficeView(models.get(i), models.get(i).getDistance()));
                }
            }
        }
        else if(request.isRko)
        {
            for(int i = 0; i < models.size();i++)
            {
                if(models.get(i).isRko() && models.get(i).getOpenHoursIndividual().size()>1)
                {
                    views.add(ViewMapper.asOfficeView(models.get(i), models.get(i).getDistance()));
                }
            }
        }
        else
        {
            for(int i = 0; i < models.size();i++)
            {
                if(models.get(i).getOpenHoursIndividual().size()>1)
                    views.add(ViewMapper.asOfficeView(models.get(i), models.get(i).getDistance()));
            }
        }

        if(request.isClearest)
        {
            Collections.sort(views, Comparator.comparing(OfficeView::getWorkload));
            return views;
        }

        Collections.sort(views, Comparator.comparing(OfficeView::getDistance));
        return views;
    }

    private static List<OfficeView> notIndividual(OfficeRequest request, List<OfficeModel> models)
    {
        List<OfficeView> views = new ArrayList<>();
        if(request.isRampReq && request.isRko)
        {
            for(int i = 0; i < models.size();i++)
            {
                if(models.get(i).isHasRamp() && models.get(i).isRko() && models.get(i).getOpenHours().size()>1)
                {
                    views.add(ViewMapper.asOfficeView(models.get(i), models.get(i).getDistance()));
                }
            }
        }   
        else if(request.isRampReq)
        {
            for(int i = 0; i < models.size();i++)
            {
                if(models.get(i).isHasRamp() && models.get(i).getOpenHours().size()>1)
                {
                    views.add(ViewMapper.asOfficeView(models.get(i), models.get(i).getDistance()));
                }
            }
        }
        else if(request.isRko)
        {
            for(int i = 0; i < models.size();i++)
            {
                if(models.get(i).isRko() && models.get(i).getOpenHours().size()>1)
                {
                    views.add(ViewMapper.asOfficeView(models.get(i), models.get(i).getDistance()));
                }
            }
        }
        else
        {
            for(int i = 0; i < models.size();i++)
            {
                if(models.get(i).getOpenHours().size()>1)
                    views.add(ViewMapper.asOfficeView(models.get(i), models.get(i).getDistance()));
            }
        }

        if(request.isClearest)
        {
            Collections.sort(views, Comparator.comparing(OfficeView::getWorkload));
            return views;
        }

        Collections.sort(views, Comparator.comparing(OfficeView::getDistance));
        return views;
    }

    @Async
    public CompletableFuture<OfficeModelDto> getOfficeById(UUID id)
    {
        logger.info("getting office by id");
        return CompletableFuture.completedFuture(DTOMapper.AsOfficeDto(repo.findById(id).get()));
    }
}
