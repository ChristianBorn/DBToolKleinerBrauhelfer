import React from 'react';
import TableComponent from "../components/TableComponent";
import {Container} from '@mui/material';
import {GebindePageData} from "../models/types";
import {useLoaderData} from "react-router-dom";

function Gebinde() {
    const pageData = useLoaderData() as GebindePageData;
    const {gebinde, kapazitaeten} = pageData;

    return (
        <React.Fragment>
            <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                <TableComponent title={"Freie Kapazitäten"} objectsToDisplay={kapazitaeten}></TableComponent>
                <TableComponent title={"Gebinde im Lager"} objectsToDisplay={gebinde}></TableComponent>
            </Container>
        </React.Fragment>
    );
}

export default Gebinde;