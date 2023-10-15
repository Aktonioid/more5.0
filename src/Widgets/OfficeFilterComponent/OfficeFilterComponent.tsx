import React, {FC, FormEventHandler} from 'react';
import classes from "./OfficeFilterComponent.module.css";
import {useInput} from "../../Shared/hooks/useInput";
import CheckBox from "../../Shared/ui/CheckBox/CheckBox";
const OfficeFilterComponent: FC = () => {
    const isIndividual = useInput(false)
    const isRampReq = useInput(false)
    const isRKO = useInput(false)
    const isClearest = useInput(false)
    const submitHandler: FormEventHandler<HTMLFormElement> = (event) => {
        event.preventDefault()
    }
    return (
        <form className={classes.form} onSubmit={submitHandler}>
            <CheckBox label={'Принимает Физлица'} value={isIndividual.state} changeHandler={isIndividual.changeHandler}/>
            <CheckBox label={'Есть пандус для колясок'} value={isRampReq.state} changeHandler={isRampReq.changeHandler}/>
            <CheckBox label={'Есть РКО'} value={isRKO.state} changeHandler={isRKO.changeHandler}/>
            <CheckBox label={'Смотреть ближайшее'} value={isClearest.state} changeHandler={isClearest.changeHandler}/>
            <button className={classes.button} type="submit">Submit</button>
        </form>
    );
};

export default OfficeFilterComponent;