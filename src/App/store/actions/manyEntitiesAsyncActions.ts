import {Dispatch} from "redux";
import {ManyEntitiesAction, ManyEntitiesActions} from "../types/typesActions";
import axios from "axios";
import {IShortOffice} from "../../../Entities/shortOffice";
import {IShortAtm} from "../../../Entities/shortAtm";
import {RequestParamsATM, RequestParamsOffice} from "../../../Entities/requestParams";
import {LngLat} from "@yandex/ymaps3-types";


export const fetchManyEntities = (coords: [number, number], area: number = 5) => {
    return async (dispatch: Dispatch<ManyEntitiesAction>) => {
        try {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES, payload: true})
            const atmResponse = await axios.get<IShortAtm[]>('http://localhost:8080/atm/radius',{
                params: {
                    latitude: coords[1],
                    longitude: coords[0],
                    distance: area
                },
                withCredentials: false
            })
            const officeResponse = await axios.get<IShortOffice[]>('http://localhost:8080/office/ofs',{
                params: {
                    latitude: coords[1],
                    longitude: coords[0],
                    distance: area
                },
                withCredentials: false
            })
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_SUCCESS, payload: {entities: atmResponse.data, offices: officeResponse.data}})

        } catch (err: any) {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_ERROR, payload: String(err)})
        }
    }
}

export const fetchFilteredAtms = (param: RequestParamsATM, coordinates: LngLat, area: number = 5) => {
    return async (dispatch: Dispatch<ManyEntitiesAction>) => {
        try {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES, payload: true})
            const response = await axios.get<IShortAtm[]>('http://localhost:8080/atm/sort', {
                params: {
                    ...param,
                    distance: area,
                    latitude: coordinates[1],
                    longitude: coordinates[0]
                },
                withCredentials: false
            })
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_SUCCESS, payload: {entities: response.data, offices: []}})
        } catch (err: any) {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_ERROR, payload: String(err)})
        }
    }
}

export const fetchFilteredOffices = (param: RequestParamsOffice, coordinates: LngLat, area: number = 5) => {
    return async (dispatch: Dispatch<ManyEntitiesAction>) => {
        try {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES, payload: true})
            const response = await axios.get<IShortOffice[]>('http://localhost:8080/office/sort', {
                params: {
                    isIndividual: param.isIndividual,
                    isRko: param.isRKO,
                    isRampReq: param.isRampReq,
                    isClearest: param.isClearest,
                    distance: area,
                    latitude: coordinates[1],
                    longitude: coordinates[0]
                },
            
                withCredentials: false
            })
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_SUCCESS, payload: {offices: response.data, entities: []}})
        } catch (err: any) {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_ERROR, payload: String(err)})
            console.log(coordinates)
        }
    }
}