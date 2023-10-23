import React, {useState, useCallback, useEffect} from 'react';
import TableComponent from "../components/TableComponent";
import {Kapazitaet} from "../models/Kapazitaet";
import axios from "axios";
import {GebindeModel} from "../models/GebindeModel";


function Gebinde() {
    const [kapazitaeten, setkapazitaeten] = useState<Kapazitaet[]>([{name: "", anzahl: 0, summierte_Kapazitaet: 0}])
    const [allGebinde, setAllGebinde] = useState<GebindeModel[]>([{name: "", anzahl: 0, gebindeStatus: "", fassungsvermoegen: 0}])
    const getKapazitaeten = useCallback(() => {
        axios.get("/gebinde/kapazität/grouped")
            .then((response) => response.data)
            .catch((error) => console.error("Error while getting Kapazitäten:" + error))
            .then(setkapazitaeten)
    }, [])
    const getAllGebinde = useCallback(() => {
        axios.get("/gebinde")
            .then((response) => response.data)
            .catch((error) => console.error("Error while getting Gebinde:" + error))
            .then(setAllGebinde)
    }, [])
    useEffect(() => {
        getKapazitaeten()
        getAllGebinde()
    },[getKapazitaeten, getAllGebinde])
    return (
        <React.Fragment>
            <TableComponent title={"Freie Kapazitäten"} objectsToDisplay={kapazitaeten}></TableComponent>
            <TableComponent title={"Gebinde im Lager"} objectsToDisplay={allGebinde}></TableComponent>
        </React.Fragment>
    );
}

export default Gebinde;