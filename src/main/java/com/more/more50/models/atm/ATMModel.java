package com.more.more50.models.atm;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document("ATMModel")
//модель для банкомата
public class ATMModel {
    @Id
    @Nullable
    UUID id;

    String address;

    double latitude;//широта
    double longitude;//долгота

    boolean allDay; //работает ли банкомат круглосуточно

    Services services; //особенности банкомата

    double distance; //расстояние от пользователя до банкомата 

    int reqServices; // количество необходимых сервисов
}
