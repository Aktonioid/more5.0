import {combineReducers} from "redux";
import {oneEntitiesReducer} from "./oneEntityReducer";
import {manyEntitiesReducer} from "./manyEntitiesReducer";


export const rootReducer = combineReducers({
    oneEntity: oneEntitiesReducer,
    manyEntities: manyEntitiesReducer
})

