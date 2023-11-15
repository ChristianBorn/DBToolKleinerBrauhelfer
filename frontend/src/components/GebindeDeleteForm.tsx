import FormControl from '@mui/material/FormControl';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import {useState} from "react";
import {FormGroup, SelectChangeEvent} from "@mui/material";
import {GebindeModel} from "../models/GebindeModel";
import {Form} from "react-router-dom";
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';


type FormProps = {
    selectItems?: GebindeModel[],
    actiontype: string,
    onClose: () => void,

}
export default function GebindeStatusForm(props: FormProps) {
    //Todo: Implement Confirmation window on submit
    const [nameGebinde, setNameGebinde] = useState('');

    function handleNameGebinde(event: SelectChangeEvent) {
        setNameGebinde(event.target.value.trim());
    }

    return (
        <>
            <Grid sx={{ justifyContent: "center", ml: "auto", overflow: "hidden", width: 1}} container spacing={2}>
                <Form style={{display: "flex", flexDirection: "column"}} method="put">
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