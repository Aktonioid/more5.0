package com.more.more50.views;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OfficeView 
{
    UUID id; //id отделения 
    double latitude;//широта
    double longitude;//долгота

    int workload;
    double distance;

    public OfficeView(UUID id,double distance ,double latitude, double longitude, int workload)
    {
        this.id = id;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workload = workload;
    }
}
