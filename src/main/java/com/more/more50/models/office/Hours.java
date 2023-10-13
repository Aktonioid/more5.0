package com.more.more50.models.office;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//время работы банкомата в дни
public class Hours implements Serializable
{
    String days;
    int open;
    int close;
}
