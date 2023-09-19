import React, {useState, useCallback, useEffect} from 'react';
import axios from "axios";

import {GeraetModel} from "../models/GeraetModel";
import {AusruestungModel} from "../models/AusruestungModel";
import TableComponent from "../components/TableComponent";

function Ausruestung() {
    const [geraete, setGeraete] = useState<GeraetModel[]>([{id: -1, bezeichnung: "", ausruestungAnlagenId:
            {id: -1, name: "", typ: -1, sudhausausbeute: -1}}
    ])
    const [ausruestung, setAusruestung] = useState<AusruestungModel[]>([{id: -1, name: "", typ: -1, sudhausausbeute: -1}])
    const getGerate = useCallback(() => {
        axios.get("/geraete")
            .then((response) => response.data)
            .catch((error) => console.error("Error while getting Geraete:" + error))
            .then(setGeraete)
    }, [])
    const getAusruestung = useCallback(() => {
        axios.get("/ausruestung")
            .then((response) => response.data)
            .catch((error) => console.error("Error while getting Ausrüstung:" + error))
            .then(setAusruestung)
    }, [])
    useEffect(() => {
        getGerate()
        getAusruestung()
    },[getGerate, getAusruestung])
    return (
        <React.Fragment>
            <TableComponent title={"Ausrüstung"} objectsToDisplay={ausruestung}></TableComponent>
            <TableComponent title={"Geräte"} objectsToDisplay={geraete}></TableComponent>
        </React.Fragment>

    );
}

export default Ausruestung;