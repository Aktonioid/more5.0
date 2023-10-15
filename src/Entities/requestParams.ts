
export interface RequestParamsOffice {
    isIndividual: boolean,
    isRampReq: boolean,
    isRKO: boolean,
    isClearest: boolean,
}

export interface RequestParamsATM {
    wheelchairReq: boolean,
    blindReq: boolean,
    nfcForBankCardReq: boolean,
    qrReadReq: boolean,
    supportsUsdReq: boolean,
    supportsChargeRubReq: boolean,
    supportsEurReq: boolean,
    supportsRubReq: boolean
}