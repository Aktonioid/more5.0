import {ManyEntitiesAction, ManyEntitiesActions} from "../types/typesActions";
import {IShortAtm} from "../../../Entities/shortAtm";
import {IShortOffice} from "../../../Entities/shortOffice";

interface manyEntitiesState {
    loading: boolean,
    error: string | null,
    entities: IShortAtm[],
    offices: IShortOffice[]
}

const initialState: manyEntitiesState = {
    loading: false,
    error: null,
    entities: [],
    offices: []
}

export const manyEntitiesReducer = (state: manyEntitiesState = initialState, action: ManyEntitiesAction): manyEntitiesState => {
    switch (action.type){
        case ManyEntitiesActions.FETCH_ENTITIES:
            return {loading: action.payload, error: null, entities: [], offices: []};
        case ManyEntitiesActions.FETCH_ENTITIES_SUCCESS:
            return {loading: false, error: null, entities: action.payload.entities, offices: action.payload.offices};
        case ManyEntitiesActions.FETCH_ENTITIES_ERROR:
            return {loading: false, error: action.payload, entities: [], offices: []}
        default:
            return state
    }
}