package com.more.more50.views;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// модель для отображения на карте отделений в заданном радиусе
public class ATMView 
{
    UUID id;
    
    double distance;
}
