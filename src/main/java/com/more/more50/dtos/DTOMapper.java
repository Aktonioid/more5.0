package com.more.more50.dtos;

import com.more.more50.models.atm.ATMModel;

public class DTOMapper 
{
    public static ATMModelDto AsDto(ATMModel model)
    {
        ATMModelDto dto = new ATMModelDto();

        dto.address = model.getAddress();
        dto.allDay = model.isAllDay();
        dto.distance = model.getDistance();
        dto.latitude = model.getLatitude();
        dto.longitude = model.getLongitude();
        dto.services = model.getServices();

        return null;
    }    
}
