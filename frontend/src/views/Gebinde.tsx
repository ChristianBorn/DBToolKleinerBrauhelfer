import React, {useState, useCallback, useEffect} from 'react';
import TableComponent from "../components/TableComponent";
import {Kapazitaet} from "../models/Kapazitaet";
import axios from "axios";
import {GebindeModel} from "../models/GebindeModel";
import { Container } from '@mui/material';


function Gebinde() {
    const [kapazitaeten, setkapazitaeten] = useState<Kapazitaet[]>([{name: "", anzahl: 0, summierte_Kapazitaet: ""}])
    const [allGebinde, setAllGebinde] = useState<GebindeModel[]>([{name: "", anzahl: 0, gebindeStatus: "", fassungsvermoegen: ""}])
    const getKapazitaeten = useCallback(() => {
        axios.get("/gebinde/kapazität/grouped")
            .then((response) => response.data)
            .catch((error) => console.error("Error while getting Kapazitäten:" + error))
            .then((data) => {
                return data.map((kapa:Kapazitaet) => {
                    kapa["Summierte Kapazität"] = kapa["Summierte Kapazität"] + " L";
                    return kapa;
                })
            })
            .then(setkapazitaeten)

    }, [])
    const getAllGebinde = useCallback(() => {
        axios.get("/gebinde")
            .then((response) => response.data)
            .catch((error) => console.error("Error while getting Gebinde:" + error))
            .then((data) => {
                return data.map((gebindeEntry:GebindeModel) => {
                    gebindeEntry.fassungsvermoegen = gebindeEntry.fassungsvermoegen + " L";
                    return gebindeEntry;
                })
            })
            .then(setAllGebinde)
    }, [])
    useEffect(() => {
        getKapazitaeten()
        getAllGebinde()
    },[getKapazitaeten, getAllGebinde])
    return (
        <React.Fragment>
            <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                <TableComponent title={"Freie Kapazitäten"} objectsToDisplay={kapazitaeten}></TableComponent>
                <TableComponent title={"Gebinde im Lager"} objectsToDisplay={allGebinde}></TableComponent>
            </Container>
        </React.Fragment>
    );
}

export default Gebinde;