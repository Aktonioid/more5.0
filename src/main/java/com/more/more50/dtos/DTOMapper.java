package com.more.more50.dtos;

import com.more.more50.models.atm.ATMModel;
import com.more.more50.models.office.OfficeModel;

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

        return dto;
    }    

    public static OfficeModelDto AsOfficeDto(OfficeModel model)
    {
        OfficeModelDto dto = new OfficeModelDto();

        dto.address = model.getAddress();
        dto.distance = model.getDistance();
        dto.hasRamp = model.isHasRamp();
        dto.id = model.getId();
        dto.latitude = model.getLatitude();
        dto.longitude = model.getLongitude();
        dto.metroStation = model.getMetroStation();
        dto.officeType = model.getOfficeType();
        dto.openHours = model.getOpenHours();
        dto.openHoursIndividual = model.getOpenHoursIndividual();
        dto.rko = model.isRko();
        dto.salePointName = model.getSalePointName();

        return dto;
    }
}
