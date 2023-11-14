import FormControl from '@mui/material/FormControl';
import Input from '@mui/material/Input';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import {ChangeEvent, useState} from "react";
import {FormGroup, SelectChangeEvent} from "@mui/material";
import {GebindeModel} from "../models/GebindeModel";
import {Form} from "react-router-dom";
import TextField from '@mui/material/TextField';

type FormProps = {

    selectItems?: GebindeModel[],
    onClose: () => void,
    actiontype: string

}
export default function GebindeStatusForm(props: FormProps) {
    const [nameGebinde, setNameGebinde] = useState('');
    const [numGebinde, setNumGebinde] = useState(0);

    function handleNameGebinde(event: SelectChangeEvent) {
        setNameGebinde(event.target.value.trim());
    }

    function handleNumGebinde(event: ChangeEvent<HTMLInputElement>) {
        setNumGebinde(+event.target.value);
    }
    return (
        <>
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
                        <Input value={numGebinde} onChange={handleNumGebinde} required
                               placeholder='Anzahl Gebinde'
                               sx={{mt:2}}
                               type={'number'}
                               id="num-gebinde"
                               name='num-gebinde' />
                    </FormGroup>
                </FormControl>
                <FormControl sx={{display:"none"}} hidden disabled={true}>
                    <TextField name="actiontype" value={props.actiontype}/>
                </FormControl>
                <Button sx={{mt: 4}} variant="outlined" onClick={props.onClose}>
                    Abbrechen
                </Button>
                <Button type={'submit'} sx={{mt: 4}} variant="outlined">Abschicken</Button>
            </Form>
        </>
    )
}