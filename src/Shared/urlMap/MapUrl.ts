import {LngLat} from "@yandex/ymaps3-types";

export enum typeOfRoute {
    auto = 'auto',
    bus = 'mt',
    walk = 'pd',
    bicycle = 'bc'
}
export const redirectToMap = (from: LngLat, to: LngLat, by: typeOfRoute = typeOfRoute.auto):string => {
    return `https://yandex.ru/maps/?rtext=${from[1]},${from[0]}~${to[1]},${to[0]}&rtt=${by}`
}