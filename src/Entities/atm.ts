export interface IAtm {
    id: string
    address: string,
    latitude: number,
    longitude: number,
    allDay: boolean,
    services: {
        wheelchair: Service,
        blind: Service,
        nfcForBankCards: Service,
        qrRead: Service,
        supportsUsd: Service,
        supportsChargeRub: Service,
        supportsEur: Service,
        supportsRub: Service
    }
}

export interface Service {
    serviceCapability: boolean,
    serviceActivity: boolean
}