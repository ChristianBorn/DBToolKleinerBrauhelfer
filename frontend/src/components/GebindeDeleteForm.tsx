import FormControl from '@mui/material/FormControl';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import {useEffect, useState} from "react";
import {FormGroup, SelectChangeEvent} from "@mui/material";
import {GebindeModel} from "../models/GebindeModel";
import {Form} from "react-router-dom";
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';


type FormProps = {
    selectItems: GebindeModel[],
    actiontype: string,
    handleSubmit: () => void,

}
export default function GebindeDeleteForm(props: FormProps) {
    //Todo: Implement Confirmation window on submit
    const [nameGebinde, setNameGebinde] = useState('');

    function handleNameGebinde(event: SelectChangeEvent) {
        setNameGebinde(event.target.value.trim());
    }
    // Setzt Auswahl im Select beim Submit zurück
    useEffect(() => setNameGebinde(""), [props.selectItems])

    return (
        <>
            <Grid sx={{ justifyContent: "center", ml: "auto", overflow: "hidden", width: 1}} container spacing={2}>
                <Form onSubmit={props.handleSubmit} style={{display: "flex", flexDirection: "column"}} method="delete">
                    <Grid item>
                        <FormControl sx={{width: 1}}>
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
                    </Grid>
                    <FormControl sx={{display:"none"}} hidden disabled={true}>
                        <TextField name="actiontype" value={props.actiontype}/>
                    </FormControl>
                    <Grid sx={ {mr: "auto", ml: "auto"} } item>
                        <Button color={"success"} type={'submit'} sx={{mt: 4}} variant="outlined">Löschen</Button>
                    </Grid>
                </Form>
            </Grid>
        </>
    )
}