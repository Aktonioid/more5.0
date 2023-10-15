import {Dispatch} from "redux";
import {OneEntitiesAction, OneEntitiesActions} from "../types/typesActions";
import axios from "axios";
import {IAtm} from "../../../Entities/atm";
import {IOffice} from "../../../Entities/office";

export const fetchOneAtm = (id: string) => {
    return async (dispatch: Dispatch<OneEntitiesAction>) => {
        try {
            dispatch({type: OneEntitiesActions.FETCH_ONE_ENTITIES, payload: true})
            const response = await axios.get<IAtm>(`http://localhost:8080/atm/${id}`, {
                withCredentials: false,
                headers: {
                    'Access-Control-Allow-Origin' : '*',
                    'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
                }
            })

            dispatch({type: OneEntitiesActions.FETCH_ONE_ENTITIES_SUCCESS, payload: {entity: response.data, office: null}})
        } catch (err: any){
            dispatch({type: OneEntitiesActions.FETCH_ONE_ENTITIES_ERROR, payload: err})
        }
    }
}

export const fetchOneOffice = (id: string) => {
    return async (dispatch: Dispatch<OneEntitiesAction>) => {
        try {
            dispatch({type: OneEntitiesActions.FETCH_ONE_ENTITIES, payload: true})
            const response = await axios.get<IOffice>(`http://localhost:8080/office/${id}`, {
                withCredentials: false
            })
            dispatch({type: OneEntitiesActions.FETCH_ONE_ENTITIES_SUCCESS, payload: {office: response.data, entity: null}})
        } catch (err: any){
            dispatch({type: OneEntitiesActions.FETCH_ONE_ENTITIES_ERROR, payload: err})
        }
    }
}