package com.more.more50.controllers;

import java.util.List;
import java.util.UUID;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    //Это костыль, потому что, инчае боди не передать, ибо axios
    @GetMapping("/radius")
    public ResponseEntity<List<ATMView>> getAtmsInRadius(double latitude, double longitude, double distance)throws Exception
    {   
        UserGeolocation geolocation = new UserGeolocation();
        geolocation.distance=distance;
        geolocation.latitude = latitude;
        geolocation.longitude = longitude;

        return ResponseEntity.ok(service.GetATMsInRadius(geolocation).join());
    }
    @GetMapping("/sort")
    public ResponseEntity<List<ATMView>> sortByServices(boolean wheelchairReq,boolean blindReq,
                                                        boolean nfcForBankCardsReq, boolean qrReadReq,
                                                        boolean supportsUsdReq,boolean supportsChargeRubReq,
                                                        boolean supportsEurReq, boolean supportsRubReq,
                                                        double latitude, double longitude, double distance)
    {
        UserGeolocation geolocation = new UserGeolocation();
        geolocation.distance=distance;
        geolocation.latitude = latitude;
        geolocation.longitude = longitude;

        AtmRequest request = new AtmRequest();
        
        request.setAtms(service.GetATMsInRadius(geolocation).join());

        request.setWheelchairReq(wheelchairReq);
        request.setBlindReq(blindReq);
        request.setNfcForBankCardsReq(nfcForBankCardsReq);
        request.setQrReadReq(qrReadReq);
        request.setSupportsUsdReq(supportsUsdReq);
        request.setSupportsChargeRubReq(supportsChargeRubReq);
        request.setSupportsEurReq(supportsEurReq);
        request.setSupportsRubReq(supportsRubReq);
        
        return ResponseEntity.ok(service.SortByServices(request).join());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ATMModelDto> getById(@PathVariable String id)
    {
        UUID uid = UUID.fromString(id);
        ATMModelDto dto = service.getModelById(uid).join();
        return ResponseEntity.ok(dto);
    }
}
