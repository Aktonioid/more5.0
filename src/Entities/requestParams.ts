import {Service} from "./atm";
import {IShortAtm} from "./shortAtm";
import {IShortOffice} from "./shortOffice";

export interface RequestParamsOffice {
    offices: IShortOffice,
    isIndividual: boolean,
    isRampReq: boolean,
    isRKO: boolean,
    isClearest: boolean
}

export interface RequestParamsATM {
    atms: IShortAtm[]
    wheelchairReq: boolean,
    blindReq: boolean,
    nfcForBankCardReq: boolean,
    qrReadReq: boolean,
    supportsUsdReq: boolean,
    supportsChargeRubReq: boolean,
    supportsEurReq: boolean,
    supportsRubReq: boolean
}