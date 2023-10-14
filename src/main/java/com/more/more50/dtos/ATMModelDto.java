package com.more.more50.dtos;

import java.util.UUID;

import org.springframework.lang.Nullable;

import com.more.more50.models.atm.Services;

public class ATMModelDto 
{
    @Nullable
    UUID id;

    String address;

    double latitude;//широта
    double longitude;//долгота

    boolean allDay; //работает ли банкомат круглосуточно

    Services services; //особенности банкомата

    double distance; //расстояние от пользователя до банкомата 

    
}
