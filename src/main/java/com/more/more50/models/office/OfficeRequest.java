package com.more.more50.models.office;

import java.util.List;

import com.mongodb.lang.Nullable;
import com.more.more50.views.OfficeView;

public class OfficeRequest 
{
    public List<OfficeView> offices;
    public boolean isIndividual;//физлицо true - да, false -нет
    public boolean isRko; //и для юрриков и для физиков
    public boolean isRampReq; //нужна ли рампа
    public boolean isClearest; //проверка на то нужно ли пользователю ближайшее отделение
                                //Если fasle, то сортируем по нагрузке
}
