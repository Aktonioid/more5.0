import React, {FC} from 'react';
import {IOffice} from "../../Entities/office";
import {LngLat} from "@yandex/ymaps3-types";
import classes from './OfficeComponent.module.css'
import {redirectToMap} from "../../Shared/urlMap/MapUrl";
interface OfficeComponentProps {
    Office: IOffice,
    userCoordinates: LngLat
}
const OfficeComponent:FC<OfficeComponentProps> = ({Office, userCoordinates}) => {
    return (
        <div className={classes.container}>
            <p className={classes.text}>{Office.salePointName}</p>
            <p className={classes.text}>{Office.address}</p>
            <p className={classes.text}>{Office.rko}</p>
            <p className={classes.text}>Часы работы для Юрлиц:</p>
            {Office.openHours.map( hours => (
                <p key={hours.days + hours.hours} className={classes.text}>{`${hours.days}: ${hours.hours}`}</p>
            ))}
            <p className={classes.text}>Часы работы для Физлиц:</p>
            {Office.openHoursIndividual.map( hours => (
                <p key={hours.days + hours.hours} className={classes.text}>{`${hours.days}: ${hours.hours}`}</p>
            ))}
            {Office.hasRamp && <p className={classes.text}>Есть пандус для колясок</p>}
            {Office.metroStation && <p className={classes.text}>{Office.metroStation}</p>}
            <a className={classes.text} href={redirectToMap(userCoordinates, [Office.longitude, Office.latitude])} target="_blank">Построить маршрут</a>
        </div>
    );
};

export default OfficeComponent;