import React, {FC, MouseEventHandler, useState} from 'react';
import classes from "./CheckBox.module.css";
import {Simulate} from "react-dom/test-utils";
import change = Simulate.change;
interface ICheckBoxProps {
    label: string,
    value: boolean,
    changeHandler: MouseEventHandler<HTMLInputElement>
}
const CheckBox: FC<ICheckBoxProps> = ({value, label, changeHandler}) => {

    return (
        <div className={classes.block}>
            <input type="checkbox" checked={value} className={classes.checkbox} onClick={changeHandler}/>
            <label className={classes.label}>{label}</label>
        </div>
    );
};

export default CheckBox;