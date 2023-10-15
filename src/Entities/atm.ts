export interface IAtm {
    id: string
    address: string,
    latitude: number,
    longitude: number,
    allDay: boolean,
    services: {
        wheelchair: Service,
        blind: Service,
        nfcForBankCard: Service,
        qrRead: Service,
        supportsUsd: Service,
        supportsChargeRub: Service,
        supportsEur: Service,
        supportsRub: Service
    }
}

export enum AtmService {
    WHEELCHAIR = 'wheelchair',
    BLIND = 'blind',
    NFCFORBANKCARD = 'nfcForBankCards',
    QRREAD = 'qrRead',
    SUPPORTSUSD = 'supportsUsd',
    SUPPORSTCHARGERUB = 'supportsChargeRub',
    SUPPORTSEUR = 'supportsEur',
    SUPPORTSRUB = 'supportsRub'
}
export enum ServiceCapability {
    UNKNOWN='UNKNOWN',
    UNSUPPORTED='UNSUPPORTED',
    SUPPORTED='SUPPORTED'
}

export enum ServiceActivity {
    UNKNOWN = 'UNKNOWN',
    UNAVAILABLE = 'UNAVAILABLE',
    AVAILABLE = 'AVAILABLE'
}

export interface Service {
    serviceCapability: boolean,
    serviceActivity: boolean
}