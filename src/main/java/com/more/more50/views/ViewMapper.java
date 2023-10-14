package com.more.more50.views;

import java.util.UUID;

import com.more.more50.models.atm.ATMModel;

public class ViewMapper 
{
    public static ATMView AsView(ATMModel model,double distance)
    {
        return new ATMView(model.getId(),distance);
    }    

    public static UUID AsId(ATMView view)
    {
        return view.id;
    }
}
