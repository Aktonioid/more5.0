package com.more.more50.models.atm;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//класс особенности банкомата
public class Service implements Serializable{
    boolean serviceCapability;
    boolean serviceActivity;
}
