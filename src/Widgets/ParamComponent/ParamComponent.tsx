import React, {FC, MouseEventHandler} from 'react';
import classes from "./ParamComponent.module.css";
import AtmFilterComponent from "../AtmFilterComponent/AtmFilterComponent";
import CheckBox from "../../Shared/ui/CheckBox/CheckBox";
import OfficeFilterComponent from "../OfficeFilterComponent/OfficeFilterComponent";
import {LngLat} from "@yandex/ymaps3-types";

interface ParamComponentProps {
    coordinates: LngLat
    isAtm: {
        state: boolean,
        changeHandler: MouseEventHandler<HTMLInputElement>
    }
}
const ParamComponent: FC<ParamComponentProps> = ({isAtm, coordinates}) => {

    return (
        <div className={classes.container}>
            <div className={classes.switchers}>
                <CheckBox label={'Банкомат'} value={isAtm.state} changeHandler={isAtm.changeHandler}/>
                <CheckBox label={'Отделение'} value={!isAtm.state} changeHandler={isAtm.changeHandler}/>
            </div>
            {isAtm.state
                ?<AtmFilterComponent coordinates={coordinates}/>
                :<OfficeFilterComponent coordinates={coordinates}/>
            }
        </div>
    );
};

export default ParamComponent;