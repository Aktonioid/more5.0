package com.more.more50.views;

import java.util.UUID;

import com.more.more50.models.atm.ATMModel;
import com.more.more50.models.office.OfficeModel;

public class ViewMapper 
{
    public static ATMView AsView(ATMModel model,double distance) //первая вариация превода из модели во view
    {
        
        return new ATMView(model.getId(),distance, model.getLatitude(), model.getLongitude(), model.getReqServices());
    }    

    public static ATMView AsAtmViewS(ATMModel model) //вторая вариация превода из модели во view
    {                                   //сделано так, чтобы при точно не возникало никаких ошибок при мапинге в листах
        return new ATMView(model.getId(), model.getDistance(), model.getLatitude(), model.getLongitude(), model.getReqServices());
    }

    public static UUID AsId(ATMView view)
    {
        return view.id;
    }

    public static OfficeView asOfficeView(OfficeModel model, double distance)
    {
        return new OfficeView(model.getId(), distance, model.getLatitude(), model.getLongitude());
    } 
}
