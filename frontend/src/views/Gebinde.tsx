import React, {useState} from 'react';
import TableComponent from "../components/TableComponent";
import {Container} from '@mui/material';
import {GebindePageData} from "../models/types";
import {useLoaderData} from "react-router-dom";
import Modal from "../components/Modal";
import Button from '@mui/material/Button';


function Gebinde() {
    const [dialogIsOpen, setDialogIsOpen] = useState(false);
    const pageData = useLoaderData() as GebindePageData;
    const {gebinde, kapazitaeten} = pageData;

    function toggleDialog() {
        setDialogIsOpen(() => !dialogIsOpen);
    }

    return (
        <React.Fragment>
            <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                <TableComponent title={"Freie Kapazitäten"} objectsToDisplay={kapazitaeten}></TableComponent>
                <TableComponent title={"Gebinde im Lager"} objectsToDisplay={gebinde}></TableComponent>
                <Modal selectItems={gebinde.filter((singleGebinde) => {
                    return singleGebinde["status"] === 'leer';
                    })}
                       onClose={toggleDialog}
                       isOpen={dialogIsOpen}
                />
                <Button variant="outlined" onClick={toggleDialog}>
                    Gebinde füllen
                </Button>
            </Container>
        </React.Fragment>
    );
}

export default Gebinde;