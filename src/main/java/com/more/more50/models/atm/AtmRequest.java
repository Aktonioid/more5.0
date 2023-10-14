package com.more.more50.models.atm;

import java.util.List;

import com.more.more50.views.ATMView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtmRequest 
{
    List<ATMView> atms;

    boolean wheelchairReq;// нужна ли услуга для колясок
    boolean blindReq;// нужна ли услуга для слепых
    boolean nfcForBankCardsReq;// нужна ли услуга для бесконтактных карт
    boolean qrReadReq;// нужна ли услуга qr
    boolean supportsUsdReq;// нужна ли услуга для принятия долларов
    boolean supportsChargeRubReq;// нужна ли услуга обмена рублей
    boolean supportsEurReq;// нужна ли услуга для принятия евро
    boolean supportsRubReq;// нужна ли услуга для принятия рублей
}
