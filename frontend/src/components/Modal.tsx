import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import {Message} from "../models/types";
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import {PropsWithChildren} from "react";


type ModalProps = {
    isOpen: boolean,
    dialogTitle: string,
    onClose: () => void,
    message?: Message,
    showMessage: boolean

}
export default function Modal(props: PropsWithChildren & ModalProps) {
    return (
        <>
            <Dialog onBackdropClick={props.onClose} open={props.isOpen}>
                <DialogContent sx={ {display: "flex", flexDirection: "column"} }>
                    <DialogTitle sx={{mb: 1}}>{props.dialogTitle}</DialogTitle>
                    { props.message && props.showMessage &&
                    <Alert sx={{mb: 2}} severity={props.message.status}>{props.message.messageText}</Alert>
                    }
                    {props.children}
                    <Button sx={{mt: 2}} color={"error"} variant="outlined" onClick={props.onClose}>
                        Abbrechen
                    </Button>
                </DialogContent>
            </Dialog>
        </>
    )
}