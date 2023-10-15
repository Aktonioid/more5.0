import React, {FC, MouseEventHandler} from 'react';
import classes from "./OfficeFilterComponent.module.css";
import {useInput} from "../../Shared/hooks/useInput";
import CheckBox from "../../Shared/ui/CheckBox/CheckBox";
import {RequestParamsOffice} from "../../Entities/requestParams";
import {useActions} from "../../Shared/hooks/useActions";
import {LngLat} from "@yandex/ymaps3-types";

interface FilteredComponent {
    coordinates: LngLat
}
const OfficeFilterComponent: FC<FilteredComponent> = ({coordinates}) => {
    const {fetchFilteredOffices} = useActions()
    const isIndividual = useInput(false)
    const isRampReq = useInput(false)
    const isRKO = useInput(false)
    const isClearest = useInput(false)
    const submitHandler: MouseEventHandler<HTMLButtonElement> = (event) => {
        event.preventDefault()
        const reqObject: RequestParamsOffice = {
            isIndividual: isIndividual.state,
            isRampReq: isRampReq.state,
            isRKO: isRKO.state,
            isClearest: isClearest.state
        }
        fetchFilteredOffices(reqObject, coordinates)
    }
    return (
        <form className={classes.form} >
            <CheckBox label={'Принимает Физлица'} value={isIndividual.state} changeHandler={isIndividual.changeHandler}/>
            <CheckBox label={'Есть пандус для колясок'} value={isRampReq.state} changeHandler={isRampReq.changeHandler}/>
            <CheckBox label={'Есть РКО'} value={isRKO.state} changeHandler={isRKO.changeHandler}/>
            <CheckBox label={'Смотреть ближайшее'} value={isClearest.state} changeHandler={isClearest.changeHandler}/>
            <button className={classes.button} type="button" onClick={submitHandler}>Submit</button>
        </form>
    );
};

export default OfficeFilterComponent;