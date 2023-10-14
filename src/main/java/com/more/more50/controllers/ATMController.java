package com.more.more50.controllers;

import java.util.List;
import java.util.UUID;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.more.more50.dtos.ATMModelDto;
import com.more.more50.models.UserGeolocation;
import com.more.more50.models.atm.AtmRequest;
import com.more.more50.repos.ATMRepo;
import com.more.more50.services.atm.ATMService;
import com.more.more50.views.ATMView;

@RestController
@RequestMapping("/atm")
public class ATMController 
{
    @Autowired
    private ATMService service;

    //получение отделений в радиусе
    @GetMapping("/radius")
    public ResponseEntity<List<ATMView>> getAtmsInRadius(@RequestBody UserGeolocation geolocation)throws Exception
    {
        System.out.println(geolocation.latitude+"  "+geolocation.longitude);
        System.out.println(geolocation.distance);
        
        
        return ResponseEntity.ok(service.GetATMsInRadius(geolocation).get());
    }

    @GetMapping("/sort")
    public ResponseEntity<List<ATMView>> sortByServices(@RequestBody AtmRequest request)
    {
        
        return ResponseEntity.ok(service.SortByServices(request).join());
    }

    @GetMapping("/getatm")
    public ResponseEntity<ATMModelDto> getById(UUID id)
    {
        return ResponseEntity.ok(service.getModelById(id).join());
    }
}
