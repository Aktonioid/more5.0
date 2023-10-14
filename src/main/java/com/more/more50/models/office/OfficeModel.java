package com.more.more50.models.office;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document("Office")
public class OfficeModel 
{
    @Id
    UUID id;
    String salePointName;
    
    String address;

    int workload;
   
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

