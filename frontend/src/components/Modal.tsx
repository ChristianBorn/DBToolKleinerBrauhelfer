import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import FormControl from '@mui/material/FormControl';
import Input from '@mui/material/Input';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import DialogTitle from '@mui/material/DialogTitle';
import {ChangeEvent, useState} from "react";
import {FormGroup, SelectChangeEvent} from "@mui/material";
import {GebindeModel} from "../models/GebindeModel";
import {Form, useActionData} from "react-router-dom";
import {Message} from "../models/types";
import Alert from '@mui/material/Alert';


type ModalProps = {
    isOpen: boolean,
    onClose: () => void,
    selectItems?: GebindeModel[]
}
export default function Modal(props: ModalProps) {
    const [nameGebinde, setNameGebinde] = useState('');
    const [numGebinde, setNumGebinde] = useState(0);

    let actionData = useActionData() as Message;

    function handleNameGebinde(event: SelectChangeEvent) {
        setNameGebinde(event.target.value.trim());
    }

    function handleNumGebinde(event: ChangeEvent<HTMLInputElement>) {
        setNumGebinde(+event.target.value);
    }

    return (
        <>
            <Dialog fullWidth onBackdropClick={props.onClose} open={props.isOpen}>
                <DialogContent>
                    <DialogTitle>Gebinde befüllen</DialogTitle>
                    { actionData &&
                    <Alert severity={actionData.status}>{actionData.messageText}</Alert>
                    }
                    <Form method="put">
                        <FormControl fullWidth>
                            <FormGroup>
                                <Select
                                    required
                                    name="name-gebinde"
                                    onChange={handleNameGebinde}
                                    displayEmpty
                                    value={nameGebinde}
                                    inputProps={{ 'aria-label': 'Without label' }}
                                >
                                    <MenuItem disabled value="">
                                        <em>Gebinde auswählen</em>
                                    </MenuItem>
                                    {props.selectItems?.map((item) => {
                                        return <MenuItem key={item.id} value={item.name}>{item.name}</MenuItem>
                                    })}
                                </Select>
                            </FormGroup>
                        </FormControl>
                        <FormControl fullWidth>
                            <FormGroup>
                                <Input value={numGebinde} onChange={handleNumGebinde} required placeholder='Anzahl zu füllender Gebinde' sx={{mt:2}} type={'number'} id="num-gebinde" name='num-gebinde' />
                            </FormGroup>
                        </FormControl>
                        <Button sx={{mt: 4}} variant="outlined" onClick={props.onClose}>
                            Abbrechen
                        </Button>
                        <Button type={'submit'} sx={{mt: 4}} variant="outlined">Abschicken</Button>
                    </Form>
                </DialogContent>
            </Dialog>
        </>
    )
}