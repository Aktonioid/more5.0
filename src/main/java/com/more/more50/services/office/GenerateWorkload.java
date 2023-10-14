package com.more.more50.services.office;

import java.util.concurrent.ThreadLocalRandom;

//класс для генерации загруженности отделений в условиях, когда нет доступа к внешгним данным
public class GenerateWorkload 
{
    //просто слуйчайно генерирует, потому что идеи просто кончились
    public static int workload()
    {
        
        return ThreadLocalRandom.current().nextInt(0,11);
    }
}
