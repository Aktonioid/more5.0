import React, {FC, MouseEventHandler} from 'react';
import {useAppSelector} from "../../Shared/hooks/useAppSelector";
import OfficeComponent from "../OfficeComponent/OfficeComponent";
import {LngLat} from "@yandex/ymaps3-types";
import ATMComponent from "../ATMComponent/ATMComponent";
import classes from "./InfoComponent.module.css";

interface InfoComponentProps {
    userCoordinates: LngLat,
    isOpenResult: {
        state: boolean,
        switchHandler: () => void
    }
}
const InfoComponent:FC<InfoComponentProps> = ({userCoordinates, isOpenResult}) => {
    const {loading, error, office, entity} = useAppSelector(state => state.oneEntity)
    const className = isOpenResult.state ? [classes.wrapper, classes.open].join(' '): classes.wrapper
    const clickHandler: MouseEventHandler<HTMLButtonElement> = () => {
        if(isOpenResult.state){
            isOpenResult.switchHandler()
        }
        return
    }
    if(loading){
        return (
            <div>Loading...</div>
        )
    }
    return (
        <div className={className}>
            <button className={classes.button} type="button" onClick={clickHandler}>Close</button>
            {office && <OfficeComponent Office={office} userCoordinates={userCoordinates}/>}
            {entity && <ATMComponent userCoordinates={userCoordinates} ATM={entity}/>}
        </div>
    )
};

export default InfoComponent;