import React, {useState} from 'react';
import TableComponent from "../components/TableComponent";
import {Container} from '@mui/material';
import {GebindePageData, Message} from "../models/types";
import {useLoaderData, useActionData} from "react-router-dom";
import Modal from "../components/Modal";
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import GebindeStatusForm from "../components/GebindeStatusForm";
import GebindeDeleteForm from '../components/GebindeDeleteForm';


function Gebinde() {
    const [fillDialogIsOpen, setFillDialogIsOpen] = useState(false);
    const [emptyDialogIsOpen, setEmptyDialogIsOpen] = useState(false);
    const [deleteDialogIsOpen, setDeleteDialogIsOpen] = useState(false);
    // const [showMessage, setShowMessage] = useState(true);
    const pageData = useLoaderData() as GebindePageData;
    const {gebinde, kapazitaeten} = pageData;
    let actionData = useActionData() as Message | undefined;

    const fillSelectItems = gebinde.filter((singleGebinde) => {
        return singleGebinde["status"] === 'leer';
    })
    const emptySelectItems = gebinde.filter((singleGebinde) => {
        return singleGebinde["status"] === 'voll';
    })
    function toggleDeleteDialog() {
        setDeleteDialogIsOpen(() => !deleteDialogIsOpen);
    }
    function toggleFillDialog() {
        setFillDialogIsOpen(() => !fillDialogIsOpen);
    }

    function toggleEmptyDialog() {
        setEmptyDialogIsOpen(() => !emptyDialogIsOpen);
    }

    return (
        <React.Fragment>
            <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                <TableComponent hasActionColumn={false} title={"Freie Kapazitäten"} objectsToDisplay={kapazitaeten}></TableComponent>
                <TableComponent hasActionColumn={true} title={"Gebinde im Lager"} objectsToDisplay={gebinde}></TableComponent>
                <Modal message={actionData}
                       dialogTitle={"Gebinde befüllen"}
                       onClose={toggleFillDialog}
                       isOpen={fillDialogIsOpen}
                >
                    <GebindeStatusForm actiontype={"fill"} selectItems={fillSelectItems}/>
                </Modal>
                <Modal message={actionData}
                       dialogTitle={"Gebinde leeren"}
                       onClose={toggleEmptyDialog}
                       isOpen={emptyDialogIsOpen}
                >
                    <GebindeStatusForm actiontype={"empty"} selectItems={emptySelectItems}/>
                </Modal>
                <Modal dialogTitle={"Gebinde löschen"}
                       onClose={toggleDeleteDialog}
                       isOpen={deleteDialogIsOpen}
                >
                    <GebindeDeleteForm onClose={toggleDeleteDialog} selectItems={fillSelectItems} actiontype={"delete"}></GebindeDeleteForm>
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
                        <Button color={"error"} variant="outlined" onClick={toggleDeleteDialog}>
                            Gebinde löschen
                        </Button>
                    </ButtonGroup>
                </Container>
            </Container>
        </React.Fragment>
    );
}

export default Gebinde;