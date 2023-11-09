import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Input from '@mui/material/Input';
import {Container, Grid} from '@mui/material';
import Button from '@mui/material/Button';


type ModalProps = {
    isOpen: boolean,
    onClose: () => void,
}
export default function Modal(props: ModalProps) {
    return (
        <>
            <Dialog onBackdropClick={props.onClose} open={props.isOpen}>
                <DialogContent>
                    <Container>
                        <Grid container spacing={1}>
                            <FormControl>
                                <InputLabel htmlFor="my-input">Lorem</InputLabel>
                                <Input type={'number'} id="my-input" aria-describedby="my-helper-text" />
                                <Button sx={{mt: 4}} variant="outlined" onClick={props.onClose}>
                                    Abbrechen
                                </Button>
                            </FormControl>

                        </Grid>
                    </Container>
                </DialogContent>
            </Dialog>
        </>
    )
}