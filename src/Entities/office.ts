export interface IOffice {
    id: string
    salePointName: string,
    address: string,
    openHours : OpenHours[],
    rko: string,
    openHoursIndividual: OpenHours[],
    officeType: string,
    salePointFormat: string,
    suoAvailability: boolean,
    hasRamp: boolean,
    latitude: number,
    longitude: number,
    metroStation: string | null,
    distance: number,
    kep: boolean,
    myBranch: boolean
}

export interface OpenHours {
    days: string,
    hours: string
}

