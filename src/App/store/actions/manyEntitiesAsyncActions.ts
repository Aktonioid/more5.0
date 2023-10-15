import {Dispatch} from "redux";
import {ManyEntitiesAction, ManyEntitiesActions} from "../types/typesActions";
import axios from "axios";
import {IShortOffice} from "../../../Entities/shortOffice";
import {IShortAtm} from "../../../Entities/shortAtm";
import {RequestParamsATM, RequestParamsOffice} from "../../../Entities/requestParams";


export const fetchManyEntities = (coords: [number, number], area: number = 30) => {
    return async (dispatch: Dispatch<ManyEntitiesAction>) => {
        try {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES, payload: true})
            const atmResponse = await axios.get<IShortAtm[]>('http://localhost:8080/atm/radius',{
                data: {
                    geolocation: [...coords, area]
                }
            })
            const officeResponse = await axios.get<IShortOffice[]>('http://localhost:8080/office/ofs',{
                data: {
                    geolocation: [...coords, area]
                }
            })
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_SUCCESS, payload: {entities: atmResponse.data, offices: officeResponse.data}})

        } catch (err: any) {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_ERROR, payload: String(err)})
        }
    }
}

export const fetchFilteredAtms = (param: RequestParamsATM) => {
    return async (dispatch: Dispatch<ManyEntitiesAction>) => {
        try {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES, payload: true})
            const response = await axios.get<IShortAtm[]>('http://localhost:8080/atm/sort', {
                data: {
                    request: param
                }
            })
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_SUCCESS, payload: {entities: response.data, offices: []}})
        } catch (err: any) {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_ERROR, payload: String(err)})
        }
    }
}

export const fetchFilteredOffices = (param: RequestParamsOffice) => {
    return async (dispatch: Dispatch<ManyEntitiesAction>) => {
        try {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES, payload: true})
            const response = await axios.get<IShortOffice[]>('http://localhost:8080/office/sort', {
                data: {
                    request: param
                }
            })
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_SUCCESS, payload: {offices: response.data, entities: []}})
        } catch (err: any) {
            dispatch({type: ManyEntitiesActions.FETCH_ENTITIES_ERROR, payload: String(err)})
        }
    }
}