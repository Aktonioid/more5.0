import React, {FC, MouseEventHandler} from 'react';
import classes from "./ParamComponent.module.css";
import AtmFilterComponent from "../AtmFilterComponent/AtmFilterComponent";
import {useInput} from "../../Shared/hooks/useInput";
import CheckBox from "../../Shared/ui/CheckBox/CheckBox";
import OfficeFilterComponent from "../OfficeFilterComponent/OfficeFilterComponent";

interface ParamComponentProps {
    isAtm: {
        state: boolean,
        changeHandler: MouseEventHandler<HTMLInputElement>
    }
}
const ParamComponent: FC<ParamComponentProps> = ({isAtm}) => {

    return (
        <div className={classes.container}>
            <div className={classes.switchers}>
                <CheckBox label={'Банкомат'} value={isAtm.state} changeHandler={isAtm.changeHandler}/>
                <CheckBox label={'Отделение'} value={!isAtm.state} changeHandler={isAtm.changeHandler}/>
            </div>
            {isAtm.state
                ?<AtmFilterComponent/>
                :<OfficeFilterComponent/>
            }
        </div>
    );
};

export default ParamComponent;