import React, {useState} from 'react';
import TableComponent from "../components/TableComponent";
import {Container} from '@mui/material';
import {GebindePageData} from "../models/types";
import {useLoaderData} from "react-router-dom";
import Modal from "../components/Modal";
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';


function Gebinde() {
    const [fillDialogIsOpen, setFillDialogIsOpen] = useState(false);
    const [emptyDialogIsOpen, setEmptyDialogIsOpen] = useState(false);
    const pageData = useLoaderData() as GebindePageData;
    const {gebinde, kapazitaeten} = pageData;

    function toggleFillDialog() {
        setFillDialogIsOpen(() => !fillDialogIsOpen);
    }
    function toggleEmptyDialog() {
        setEmptyDialogIsOpen(() => !emptyDialogIsOpen);
    }

    return (
        <React.Fragment>
            <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                <TableComponent title={"Freie Kapazitäten"} objectsToDisplay={kapazitaeten}></TableComponent>
                <TableComponent title={"Gebinde im Lager"} objectsToDisplay={gebinde}></TableComponent>
                <Modal actiontype={"fill"} dialogTitle={"Gebinde befüllen"} selectItems={gebinde.filter((singleGebinde) => {
                    return singleGebinde["status"] === 'leer';
                    })}
                       onClose={toggleFillDialog}
                       isOpen={fillDialogIsOpen}
                />
                <Modal actiontype={"empty"} dialogTitle={"Gebinde leeren"} selectItems={gebinde.filter((singleGebinde) => {
                    return singleGebinde["status"] === 'voll';
                })}
                       onClose={toggleEmptyDialog}
                       isOpen={emptyDialogIsOpen}
                />
                <ButtonGroup>
                    <Button variant="outlined" onClick={toggleFillDialog}>
                        Gebinde füllen
                    </Button>
                    <Button variant="outlined" onClick={toggleEmptyDialog}>
                        Gebinde leeren
                    </Button>
                </ButtonGroup>
            </Container>
        </React.Fragment>
    );
}

export default Gebinde;