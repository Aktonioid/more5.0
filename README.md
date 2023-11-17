# more5.0
Репо для хакатона more5.0

Это апи для выдачи информации о близлежащих отделений банка и близлежащих банкоматов. 
Так же api может фильтровать отделения по категориям, которые необходимы польщователю. Например есть ли пандус для инвалидных колясок

## Модели
**UserGeolocation**

    public double latitude; // широта на которой находиться польозватель
    
    public double longitude; // долгота на которой находиться пользователь
    
    public double distance; //расстояние на котором пользователю необходимо найти отделения?
**ATMRequest**

    List<ATMView> atms;
    
    boolean wheelchairReq;// нужна ли услуга для колясок
    
    boolean blindReq;// нужна ли услуга для слепых
    
    boolean nfcForBankCardsReq;// нужна ли услуга для бесконтактных карт
    boolean qrReadReq;// нужна ли услуга qr
    
    boolean supportsUsdReq;// нужна ли услуга для принятия долларов
    
    boolean supportsChargeRubReq;// нужна ли услуга обмена рублей
    
    boolean supportsEurReq;// нужна ли услуга для принятия евр
    
    boolean supportsRubReq;// нужна ли услуга для принятия рублей

**OfficeRequest**

    public List<OfficeView> offices;
    public boolean isIndividual;//физлицо true - да, false -нет
    public boolean isRko; //и для юрриков и для физиков
    public boolean isRampReq; //нужна ли рампа
    public boolean isClearest; //проверка на то нужно ли пользователю ближайшее отделение
                                //Если fasle, то сортируем по нагрузке
**OfficeView**

    UUID id; //id отделения 
    double latitude;//широта
    double longitude;//долгота
    int workload;
    double distance;
    
**ATMView**

    UUID id;
    double latitude;//широта
    double longitude;//долгота
    double distance;
    int reqServices;
    
**ATMModelDto** 

    UUID id;
    String address;
    double latitude;//широта
    double longitude;//долгота
    boolean allDay; //работает ли банкомат круглосуточно
    Services services; //особенности банкомата
    double distance; //расстояние от пользователя до банкомата 
    
**OfficeModelDto** 

    UUID id;
    String salePointName;
    String address;
    double distance;
    double latitude;//широта
    double longitude;//долгота
    OfficeType officeType;
    List<Hours> openHoursIndividual;
    List<Hours> openHours;
    boolean hasRamp;
    boolean suoAvailability;
    String metroStation;
    boolean rko;
    
**Hours**

    String days;
    int open;
    int close;

** enum OfficeType** 

    ClosedAccess,
    Privilege,
    Yes,
    No


## Роуты

    atm/radius - присласть UserGeolocation geolocation
        ответ: List<ATMView>
    atm/sort - прислать ATMRequest request
        ответ: List<AtmView>
    atm/{id} - вместо {id} при запросе вставлять uuid
        ответ: ATMDto

    office/ofs - прислать UserGeolocation geolocation
        ответ: List<OfficeView>
    office/sort - прислать OfficeRequest request
        ответ: List<OfficeView>
    office/{id} - вместо {id} при запросе вставлять uuid
        ответ: OfficeModelDto
