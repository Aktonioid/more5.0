import {IAtm} from "../../../Entities/atm";
import {IOffice} from "../../../Entities/office";
import {OneEntitiesAction, OneEntitiesActions} from "../types/typesActions";

interface oneEntitiesState {
    loading: boolean,
    error: string | null,
    entity: IAtm | null,
    office: IOffice | null
}

const initialState: oneEntitiesState = {
    loading: false,
    error: null,
    entity: null,
    office: null
}

export const oneEntitiesReducer = (state: oneEntitiesState = initialState, action: OneEntitiesAction): oneEntitiesState => {
    switch (action.type){
        case OneEntitiesActions.FETCH_ONE_ENTITIES:
            return {loading: action.payload, error: null, entity: null, office: null};
        case OneEntitiesActions.FETCH_ONE_ENTITIES_SUCCESS:
            return {loading: false, error: null, entity: action.payload.entity, office: action.payload.office};
        case OneEntitiesActions.FETCH_ONE_ENTITIES_ERROR:
            return {loading: false, error: action.payload, entity: null, office: null}
        default:
            return state
    }
}