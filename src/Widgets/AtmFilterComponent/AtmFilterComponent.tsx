import React, {FC,  MouseEventHandler} from 'react';
import classes from './AtmFilterComponent.module.css'
import CheckBox from "../../Shared/ui/CheckBox/CheckBox";
import {useInput} from "../../Shared/hooks/useInput";
import {RequestParamsATM} from "../../Entities/requestParams";
import {fetchFilteredAtms} from "../../App/store/actions/manyEntitiesAsyncActions";
import {useActions} from "../../Shared/hooks/useActions";
import {LngLat} from "@yandex/ymaps3-types";

interface FilteredComponents {
    coordinates: LngLat
}

const AtmFilterComponent: FC<FilteredComponents> = ({coordinates}) => {
    const {fetchFilteredAtms} = useActions()
    const wheelchair = useInput(false)
    const blind = useInput(false)
    const nfcForBankCard = useInput(false)
    const qrRead = useInput(false)
    const supportUsd = useInput(false)
    const supportChargeRub = useInput(false)
    const supportEur = useInput(false)
    const supportRub = useInput(false)
    const submitHandler: MouseEventHandler<HTMLButtonElement> = (event) => {
        event.preventDefault()

        const reqObject: RequestParamsATM = {
            wheelchairReq: wheelchair.state,
            blindReq: blind.state,
            nfcForBankCardReq: nfcForBankCard.state,
            qrReadReq: qrRead.state,
            supportsUsdReq: supportUsd.state,
            supportsChargeRubReq: supportChargeRub.state,
            supportsEurReq: supportEur.state,
            supportsRubReq: supportRub.state
        }

        fetchFilteredAtms(reqObject, coordinates)


    }
    return (
        <form className={classes.form} >
            <CheckBox label={'Пандус для колясок'} value={wheelchair.state} changeHandler={wheelchair.changeHandler}/>
            <CheckBox label={'Для слабовидящих'} value={blind.state} changeHandler={blind.changeHandler}/>
            <CheckBox label={'Поддержка NFC'} value={nfcForBankCard.state} changeHandler={nfcForBankCard.changeHandler}/>
            <CheckBox label={'Поддержка QR-кодов'} value={qrRead.state} changeHandler={qrRead.changeHandler}/>
            <CheckBox label={'Прием/Выдача Долларов'} value={supportUsd.state} changeHandler={supportUsd.changeHandler}/>
            <CheckBox label={'Обмен Рубля'} value={supportChargeRub.state} changeHandler={supportChargeRub.changeHandler}/>
            <CheckBox label={'Прием/Выдача Евро'} value={supportEur.state} changeHandler={supportEur.changeHandler}/>
            <CheckBox label={'Прием/Выдача Рублей'} value={supportRub.state} changeHandler={supportRub.changeHandler}/>
            <button type="button" className={classes.button} onClick={submitHandler}>Submit</button>
        </form>
    );
};

export default AtmFilterComponent;