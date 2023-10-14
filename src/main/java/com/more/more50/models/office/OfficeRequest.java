package com.more.more50.models.office;

import java.util.List;

import com.mongodb.lang.Nullable;
import com.more.more50.views.OfficeView;

public class OfficeRequest 
{
    List<OfficeView> offices;

    boolean isIndividual;//физлицо true - да, false -нет
    boolean isRko; //и для юрриков и для физиков

    boolean isRampReq; //нужна ли рампа
}
