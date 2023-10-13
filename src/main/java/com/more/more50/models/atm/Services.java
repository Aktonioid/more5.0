package com.more.more50.models.atm;

import java.io.Serializable;

//Сборник особенностей банкомата
public class Services implements Serializable 
{
    public Service wheelchair;
    public Service blind;
    public Service nfcForBankCards;
    public Service qrRead;
    public Service supportsUsd;
    public Service supportsChargeRub;
    public Service supportsEur;
    public Service supportsRub;
}
