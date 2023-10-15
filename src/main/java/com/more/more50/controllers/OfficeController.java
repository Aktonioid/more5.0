package com.more.more50.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.more.more50.dtos.OfficeModelDto;
import com.more.more50.models.UserGeolocation;
import com.more.more50.models.office.OfficeRequest;
import com.more.more50.services.office.OfficeService;
import com.more.more50.views.OfficeView;

@RestController
@RequestMapping("/office")
public class OfficeController 
{
    @Autowired
    private OfficeService service;

    @GetMapping("/ofs") //получение модели для отображения на карте офисов
    public ResponseEntity<List<OfficeView>> getOfficesInRadius(double latitude, double longitude, double distance)
    {
        UserGeolocation geolocation = new UserGeolocation();
        geolocation.distance=distance;
        geolocation.latitude = latitude;
        geolocation.longitude = longitude;

        List<OfficeView> views = service.getOfficeViews(geolocation).join();
        return ResponseEntity.ok(views);
    }

    @GetMapping("/sort")
    //возврат отсортированных офисов с либо по близости, либо по заполненности
    public ResponseEntity<List<OfficeView>> sortOfficesByReq(boolean isIndividual,boolean isRko,
                                                            boolean isRampReq,boolean isClearest,
                                                            double latitude, double longitude, double distance)
    {   

        UserGeolocation geolocation = new UserGeolocation();
        geolocation.distance=distance;
        geolocation.latitude = latitude;
        geolocation.longitude = longitude;

        OfficeRequest request = new OfficeRequest();
        request.offices = service.getOfficeViews(geolocation).join();
        request.isIndividual = isIndividual;
        request.isRko = isRko;
        request.isRampReq = isRampReq;
        request.isClearest = isClearest;

        return ResponseEntity.ok(service.sortByRequest(request).join());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficeModelDto> getOfficeById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(service.getOfficeById(id).join());
    }
}
