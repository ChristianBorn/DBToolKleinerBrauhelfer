import React from 'react';
import {useLoaderData} from "react-router-dom";
import TableComponent from "../components/TableComponent";
import {AusruestungPageData} from "../models/types";


function Ausruestung() {
    const pageData = useLoaderData() as AusruestungPageData;
    const geraete = pageData.geraete;
    const ausruestung = pageData.ausruestung;

    return (
        <React.Fragment>
            <TableComponent title={"Ausrüstung"} objectsToDisplay={ausruestung}></TableComponent>
            <TableComponent title={"Geräte"} objectsToDisplay={geraete}></TableComponent>
        </React.Fragment>
    );
}

export default Ausruestung;