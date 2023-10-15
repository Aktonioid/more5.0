import React, {FC, useEffect, useState} from 'react';
import {LngLat, YMapMarkerEventHandler} from "@yandex/ymaps3-types";
import ReactDOM from "react-dom";
import classes from './MapComponent.module.css';
import {useAppSelector} from "../../Shared/hooks/useAppSelector";
import {useActions} from "../../Shared/hooks/useActions";
import {userMarker} from "../../Shared/ui/markersContent/userMarker";
import {entityMarker} from "../../Shared/ui/markersContent/entityMarker";

interface MapComponentProps {
    isAtm: boolean,
    resultSwitcher: {
        state: boolean,
        switchHandler: () => void
    },
    coordinates: LngLat,
    changeCoordinates: (coordinates: LngLat) => void

}

const ymaps3Reactify = await ymaps3.import('@yandex/ymaps3-reactify');
const reactify = ymaps3Reactify.reactify.bindTo(React, ReactDOM);
const {
    YMap,
    YMapDefaultSchemeLayer,
    YMapDefaultFeaturesLayer,
    YMapMarker,
} = reactify.module(ymaps3);


const MapComponent:FC<MapComponentProps> =  ({isAtm, resultSwitcher, coordinates, changeCoordinates}) => {
    const {entities, offices, loading, error}  = useAppSelector(state => state.manyEntities)
    const {fetchManyEntities, fetchOneAtm, fetchOneOffice} = useActions()
    useEffect(() => {
        // navigator.geolocation.getCurrentPosition((position) => {
        //     setCoordinates([position.coords.longitude, position.coords.latitude ])
        // })
        fetchManyEntities([coordinates[0], coordinates[1]])
    }, [])

    const onDragMarker: YMapMarkerEventHandler = (coordinates) => {
        changeCoordinates(coordinates)
    }

    const markerClickHandler = (id: string) => {
        if(!resultSwitcher.state){
            resultSwitcher.switchHandler()
        }
        if(isAtm){
            fetchOneAtm(id)
        }else {
            fetchOneOffice(id)
        }
    }

    if(error){
        console.log(error)
    }


    return (
        <YMap
            location={{center: coordinates, zoom: 15}}
            mode="raster"
            className={classes.map}
        >
            <YMapDefaultSchemeLayer />
            <YMapDefaultFeaturesLayer />

            <YMapMarker coordinates={coordinates} draggable={true} onDragEnd={onDragMarker} markerElement={userMarker()} />
            {isAtm && entities.length > 0 && entities.map(entity => (
                <YMapMarker
                    key={entity.id}
                    coordinates={[entity.latitude, entity.longitude]}
                    id={entity.id}
                    markerElement={entityMarker()}
                    onClick={() => markerClickHandler(entity.id)}
                />
            ))}
            {!isAtm && offices.length > 0 && offices.map(entity => (
                <YMapMarker
                    key={entity.id}
                    coordinates={[entity.latitude, entity.longitude]}
                    id={entity.id}
                    markerElement={entityMarker()}
                    onClick={() => markerClickHandler(entity.id)}
                />
            ))}



        </YMap>
    );
};

export default MapComponent;