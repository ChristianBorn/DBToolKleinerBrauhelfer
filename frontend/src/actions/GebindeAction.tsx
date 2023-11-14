import {ActionFunctionArgs} from "react-router-dom";
import axios from 'axios'
import {GebindeFormData} from "../models/types";

export default async function GebindeAction(data: ActionFunctionArgs) {
    //Todo: Switch um URL zu bestimmen
    const formData = await data.request.formData();
    const postData = Object.fromEntries(formData);
    let requestUrl;
    switch (postData["actiontype"]){
        case "fill": requestUrl = "gebinde/fill"; break;
        case "empty": requestUrl = "gebinde/empty"; break;
        default: return {messageText: "Angeforderte Aktion ist unbekannt", status: "error"};
    }
    const requestBody: GebindeFormData = {
        name: postData["name-gebinde"].toString(),
        number: +postData["num-gebinde"]
    }
    return await axios
            .put(requestUrl, requestBody)
            .then((response) => {
                return {messageText: response.data, status: "success"};
            })
            .catch((reason) => {
                return {messageText: reason.response.data, status: "error"};
            });
}