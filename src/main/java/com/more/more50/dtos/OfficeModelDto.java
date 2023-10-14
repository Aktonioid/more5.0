package com.more.more50.dtos;

import java.util.List;
import java.util.UUID;

import com.more.more50.models.office.Hours;
import com.more.more50.models.office.OfficeType;

public class OfficeModelDto 
{
    UUID id;
    String salePointName;
    
    String address;

   
    double distance;

    double latitude;//широта
    double longitude;//долгота

    OfficeType officeType;

    List<Hours> openHoursIndividual;
    List<Hours> openHours;

    boolean hasRamp;
    boolean suoAvailability;
    String metroStation;

    boolean rko;
}
