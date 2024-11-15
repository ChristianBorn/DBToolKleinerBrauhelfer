import React from 'react';
import {useLoaderData} from "react-router-dom";
import TableComponent from "../components/TableComponent";
import {AusruestungPageData} from "../models/types";


function Ausruestung() {
    const pageData = useLoaderData() as AusruestungPageData;
    const {geraete, ausruestung} = pageData;

    return (
        <React.Fragment>
            <TableComponent hasActionColumn={false} title={"Ausrüstung"} objectsToDisplay={ausruestung}></TableComponent>
            <TableComponent hasActionColumn={false} title={"Geräte"} objectsToDisplay={geraete}></TableComponent>
        </React.Fragment>
    );
}

export default Ausruestung;