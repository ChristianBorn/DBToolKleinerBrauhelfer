import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import {GebindeModel} from "../models/GebindeModel";
import {useActionData} from "react-router-dom";
import {Message} from "../models/types";
import Alert from '@mui/material/Alert';
import GebindeStatusForm from "./GebindeStatusForm";


type ModalProps = {
    isOpen: boolean,
    dialogTitle: string,
    onClose: () => void,
    selectItems?: GebindeModel[],
    actiontype: string
}
export default function Modal(props: ModalProps) {
    let actionData = useActionData() as Message;

    return (
        <>
            <Dialog fullWidth onBackdropClick={props.onClose} open={props.isOpen}>
                <DialogContent>
                    <DialogTitle>{props.dialogTitle}</DialogTitle>
                    { actionData &&
                    <Alert severity={actionData.status}>{actionData.messageText}</Alert>
                    }
                    <GebindeStatusForm actiontype={props.actiontype} onClose={props.onClose} selectItems={props.selectItems}/>
                </DialogContent>
            </Dialog>
        </>
    )
}