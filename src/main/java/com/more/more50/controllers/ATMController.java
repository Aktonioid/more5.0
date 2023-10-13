package com.more.more50.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.more.more50.models.UserGeolocation;
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
    public ResponseEntity<List<ATMView>> getAtmsInRadius(@RequestBody UserGeolocation geolocation)
    {
        return ResponseEntity.ok(service.GetATMsInRadius(geolocation).join());
    }
}
