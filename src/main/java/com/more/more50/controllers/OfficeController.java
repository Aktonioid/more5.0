package com.more.more50.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<OfficeView>> getOfficesInRadius(@RequestBody UserGeolocation geolocation)
    {
        List<OfficeView> views = service.getOfficeViews(geolocation).join();
        return ResponseEntity.ok(views);
    }

    @GetMapping("/sort")
    //возврат отсортированных офисов с либо по близости, либо по заполненности
    public ResponseEntity<List<OfficeView>> sortOfficesByReq(@RequestBody OfficeRequest request)
    {
        return ResponseEntity.ok(service.sortByRequest(request).join());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<OfficeModelDto> getOfficeById(UUID id)
    {
        return ResponseEntity.ok(service.getOfficeById(id).join());
    }
}
