package com.more.more50.views;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
// модель для отображения на карте отделений в заданном радиусе
public class ATMView 
{
    UUID id;
    
    double latitude;//широта
    double longitude;//долгота

    double distance;

    int reqServices;

    public ATMView(UUID id, double distance,double latitude, double longitude, int reqServices)
    {
        this.id = id;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reqServices = reqServices;
    }
}
