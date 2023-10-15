import {IAtm} from "../../../Entities/atm";
import {IOffice} from "../../../Entities/office";
import {IShortOffice} from "../../../Entities/shortOffice";
import {IShortAtm} from "../../../Entities/shortAtm";

export enum ManyEntitiesActions {
    FETCH_ENTITIES = 'FETCH_ENTITIES',
    FETCH_ENTITIES_ERROR = 'FETCH_ENTITIES_ERROR',
    FETCH_ENTITIES_SUCCESS = 'FETCH_ENTITIES_SUCCESS',
}

export enum OneEntitiesActions {
    FETCH_ONE_ENTITIES = 'FETCH_ONE_ENTITIES',
    FETCH_ONE_ENTITIES_ERROR = 'FETCH_ONE_ENTITIES_ERROR',
    FETCH_ONE_ENTITIES_SUCCESS = 'FETCH_ONE_ENTITIES_SUCCESS',
}

type FetchEntitiesAction = {
    type: ManyEntitiesActions.FETCH_ENTITIES,
    payload: boolean
}
type FetchEntitiesSuccessAction = {
    type: ManyEntitiesActions.FETCH_ENTITIES_SUCCESS,
    payload: {
        entities: IShortAtm[],
        offices: IShortOffice[]
    }
}
type FetchEntitiesErrorAction = {
    type: ManyEntitiesActions.FETCH_ENTITIES_ERROR,
    payload: string
}

export type ManyEntitiesAction = FetchEntitiesAction | FetchEntitiesSuccessAction | FetchEntitiesErrorAction

type FetchOneEntityAction = {
    type: OneEntitiesActions.FETCH_ONE_ENTITIES,
    payload: boolean
}
type FetchOneEntitySuccessAction = {
    type: OneEntitiesActions.FETCH_ONE_ENTITIES_SUCCESS,
    payload: {
        entity: IAtm | null,
        office: IOffice | null
    }
}
type FetchOneEntityErrorAction = {
    type: OneEntitiesActions.FETCH_ONE_ENTITIES_ERROR,
    payload: string
}

export type OneEntitiesAction = FetchOneEntityAction | FetchOneEntitySuccessAction | FetchOneEntityErrorAction



