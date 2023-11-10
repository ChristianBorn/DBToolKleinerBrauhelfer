import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import FormControl from '@mui/material/FormControl';
import Input from '@mui/material/Input';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import DialogTitle from '@mui/material/DialogTitle';
import {FormEvent, ChangeEvent, useState} from "react";
import { SelectChangeEvent, FormGroup } from "@mui/material";
import {GebindeModel} from "../models/GebindeModel";


type ModalProps = {
    isOpen: boolean,
    onClose: () => void,
    selectItems?: GebindeModel[]
}
export default function Modal(props: ModalProps) {
    const [numGebinde, setNumGebinde] = useState(0);
    const [nameGebinde, setNameGebinde] = useState('');
    function handleNumGebinde(event: ChangeEvent<HTMLInputElement>) {
        setNumGebinde(() => +event.target.value);
    }
    function handleNameGebinde(event: SelectChangeEvent) {
        setNameGebinde(() => event.target.value);
    }
    function handleSubmit(event: FormEvent) {
        event.preventDefault();
        const postUrl = `http://localhost:8080/gebinde/fill?name=${nameGebinde}&number=${numGebinde}`
        console.log(postUrl);
    }
    return (
        <>
            <Dialog fullWidth onBackdropClick={props.onClose} open={props.isOpen}>
                <DialogContent>
                    <DialogTitle>Gebinde befüllen</DialogTitle>
                        <form onSubmit={handleSubmit}>
                            <FormControl fullWidth>
                                <FormGroup>
                                    <Select
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
                                        <Input onChange={handleNumGebinde} placeholder='Anzahl zu füllender Gebinde' sx={{mt:2}} type={'number'} id="num-gebinde" name='num-gebinde' />
                                    </FormGroup>
                                </FormControl>
                                    <Button sx={{mt: 4}} variant="outlined" onClick={props.onClose}>
                                        Abbrechen
                                    </Button>
                                    <Button type={'submit'} sx={{mt: 4}} variant="outlined">Abschicken</Button>
                        </form>
                </DialogContent>
            </Dialog>
        </>
    )
}