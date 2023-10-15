import React, {FC} from 'react';
import {LngLat} from "@yandex/ymaps3-types";
import {IAtm} from "../../Entities/atm";
import {redirectToMap} from "../../Shared/urlMap/MapUrl";
import classes from "./ATMComponent.module.css";
interface ATMComponentProps {
    userCoordinates: LngLat,
    ATM: IAtm
}
const ATMComponent:FC<ATMComponentProps> = ({ATM, userCoordinates}) => {
    return (
        <div className={classes.container}>
            <p className={classes.text}>{ATM.address}</p>
            <p className={classes.text}>{ATM.allDay ? 'круглосуточно': 'не круглосуточно'}</p>
            {ATM.services.wheelchair.serviceCapability
            &&
            ATM.services.wheelchair.serviceActivity
                ? <p className={classes.text}>Пандус для колясок</p>
                : null
            }
            {ATM.services.blind.serviceCapability
            &&
            ATM.services.blind.serviceActivity
                ? <p className={classes.text}>Доступно слабовидящим</p>
                : null
            }
            {ATM.services.nfcForBankCards.serviceCapability
            &&
            ATM.services.nfcForBankCards.serviceActivity
                ? <p className={classes.text}>Поддерживает NFC</p>
                : null
            }
            {ATM.services.qrRead.serviceCapability
            &&
            ATM.services.qrRead.serviceActivity
                ? <p className={classes.text}>Поддерживает QR-коды</p>
                : null
            }
            {ATM.services.supportsUsd.serviceCapability
            &&
            ATM.services.supportsUsd.serviceActivity
                ? <p className={classes.text}>Принимает/выдает доллары</p>
                : null
            }
            {ATM.services.supportsChargeRub.serviceCapability
            &&
            ATM.services.supportsChargeRub.serviceActivity
                ? <p className={classes.text}>Доступен обмен рубля</p>
                : null
            }
            {ATM.services.supportsEur.serviceCapability
            &&
            ATM.services.supportsEur.serviceActivity
                ? <p className={classes.text}>Принимает/выдает евро</p>
                : null
            }
            {ATM.services.supportsRub.serviceCapability
            &&
            ATM.services.supportsRub.serviceActivity
                ? <p className={classes.text}>Принимает/выдает рубли</p>
                : null
            }
            <a className={classes.text} href={redirectToMap(userCoordinates, [ATM.longitude, ATM.latitude])} target="_blank">Построить маршрут</a>
        </div>
    );
};

export default ATMComponent;