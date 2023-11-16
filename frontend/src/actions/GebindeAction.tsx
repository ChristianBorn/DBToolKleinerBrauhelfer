import {ActionFunctionArgs} from "react-router-dom";
import axios from 'axios'
import {GebindeFormData} from "../models/types";

export default async function GebindeAction(data: ActionFunctionArgs) {
    const formData = await data.request.formData();
    const postData = Object.fromEntries(formData);
    let requestUrl;
    switch (postData["actiontype"]){
        case "fill": requestUrl = "gebinde/fill"; break;
        case "empty": requestUrl = "gebinde/empty"; break;
        case "delete": requestUrl = "gebinde/delete/" + postData["name-gebinde"]; break;
        default: return {messageText: "Angeforderte Aktion ist unbekannt", status: "error"};
    }
    const requestBody: GebindeFormData = {
        name: postData["name-gebinde"].toString(),
        number: +postData["num-gebinde"]
    }
    if (postData["actiontype"].localeCompare("fill") === 0 || postData["actiontype"].localeCompare("empty") === 0) {
        return await axios
            .put(requestUrl, requestBody)
            .then((response) => {
                return {messageText: response.data, status: "success"};
            })
            .catch((reason) => {
                return {messageText: reason.response.data, status: "error"};
            });
    }
    if (postData["actiontype"].localeCompare("delete") === 0) {
        return await axios
            .delete(requestUrl)
            .then((response) => {
                return {messageText: response.data, status: "success"};
            })
            .catch((reason) => {
                return {messageText: reason.response.data, status: "error"};
            });
    }
}