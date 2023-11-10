import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import FormControl from '@mui/material/FormControl';
import Input from '@mui/material/Input';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import DialogTitle from '@mui/material/DialogTitle';


type ModalProps = {
    isOpen: boolean,
    onClose: () => void,
}
export default function Modal(props: ModalProps) {

    return (
        <>
            <Dialog fullWidth onBackdropClick={props.onClose} open={props.isOpen}>
                <DialogContent>
                    <DialogTitle>Gebinde befüllen</DialogTitle>
                            <FormControl fullWidth>
                                <Select
                                    displayEmpty
                                    value={''}
                                    inputProps={{ 'aria-label': 'Without label' }}
                                >
                                    <MenuItem disabled value="">
                                        <em>Placeholder</em>
                                    </MenuItem>
                                </Select>
                                <Input placeholder='Anzahl zu füllender Gebinde' sx={{mt:2}} type={'number'} id="num-gebinde" name='num-gebinde' />
                                <Button sx={{mt: 4}} variant="outlined" onClick={props.onClose}>
                                    Abbrechen
                                </Button>
                            </FormControl>
                </DialogContent>
            </Dialog>
        </>
    )
}