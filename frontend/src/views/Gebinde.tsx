import React, {useState} from 'react';
import TableComponent from "../components/TableComponent";
import {Container} from '@mui/material';
import {GebindePageData} from "../models/types";
import {useLoaderData} from "react-router-dom";
import Modal from "../components/Modal";
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import GebindeStatusForm from "../components/GebindeStatusForm";


function Gebinde() {
    const [fillDialogIsOpen, setFillDialogIsOpen] = useState(false);
    const [emptyDialogIsOpen, setEmptyDialogIsOpen] = useState(false);
    const pageData = useLoaderData() as GebindePageData;
    const {gebinde, kapazitaeten} = pageData;
    const fillSelectItems = gebinde.filter((singleGebinde) => {
        return singleGebinde["status"] === 'leer';
    })
    const emptySelectItems = gebinde.filter((singleGebinde) => {
        return singleGebinde["status"] === 'voll';
    })

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
                <Modal dialogTitle={"Gebinde befüllen"}
                       onClose={toggleFillDialog}
                       isOpen={fillDialogIsOpen}
                >
                    <GebindeStatusForm actiontype={"fill"} selectItems={fillSelectItems}/>
                </Modal>
                <Modal dialogTitle={"Gebinde leeren"}
                       onClose={toggleEmptyDialog}
                       isOpen={emptyDialogIsOpen}
                >
                    <GebindeStatusForm actiontype={"empty"} selectItems={emptySelectItems}/>
                </Modal>
                <Container sx={{mt: 2, display:"inline-flex"}}>
                    <ButtonGroup>
                        <Button variant="outlined" onClick={toggleFillDialog}>
                            Gebinde füllen
                        </Button>
                        <Button variant="outlined" onClick={toggleEmptyDialog}>
                            Gebinde leeren
                        </Button>
                    </ButtonGroup>
                    <ButtonGroup sx={{ml:"auto"}}>
                        <Button color={"success"} variant="outlined">
                            Gebinde anlegen
                        </Button>
                        <Button color={"error"} variant="outlined">
                            Gebinde löschen
                        </Button>
                    </ButtonGroup>
                </Container>
            </Container>
        </React.Fragment>
    );
}

export default Gebinde;