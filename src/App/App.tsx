import React, {FC, useState} from 'react';
import MapComponent from "../Widgets/MapComponent/MapComponent";
import classes from "./App.module.css";
import ParamComponent from "../Widgets/ParamComponent/ParamComponent";
import {useInput} from "../Shared/hooks/useInput";
import {useSwitcher} from "../Shared/hooks/useSwitcher";
import InfoComponent from "../Widgets/InfoComponent/InfoComponent";
import {LngLat} from "@yandex/ymaps3-types";
const App: FC = () => {
    const isAtm = useInput(false)
    const isOpenResult = useSwitcher(false)
    const [coordinates, setCoordinates] = useState<LngLat>([37.64, 55.76])
    const changeCoordinates = (coordinates: LngLat) => {
        setCoordinates(coordinates)
    }
    return (
        <section className={classes.app}>
            <InfoComponent userCoordinates={coordinates} isOpenResult={isOpenResult}/>
            <ParamComponent isAtm={isAtm} 
                            coordinates={coordinates}/>
            <MapComponent isAtm={isAtm.state}
                          resultSwitcher={isOpenResult}
                          coordinates={coordinates}
                          changeCoordinates={changeCoordinates}
            />
        </section>
    );
};

export default App;