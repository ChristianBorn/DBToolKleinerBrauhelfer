import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import {useActionData} from "react-router-dom";
import {Message} from "../models/types";
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import {PropsWithChildren} from "react";


type ModalProps = {
    isOpen: boolean,
    dialogTitle: string,
    onClose: () => void,

}
export default function Modal(props: PropsWithChildren & ModalProps) {
    let actionData = useActionData() as Message;

    return (
        <>
            <Dialog onBackdropClick={props.onClose} open={props.isOpen}>
                <DialogContent sx={ {display: "flex", flexDirection: "column"} }>
                    <DialogTitle sx={{mb: 2}}>{props.dialogTitle}</DialogTitle>
                    { actionData &&
                    <Alert severity={actionData.status}>{actionData.messageText}</Alert>
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