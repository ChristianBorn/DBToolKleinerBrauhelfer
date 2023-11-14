import {ActionFunctionArgs} from "react-router-dom";
import axios from 'axios'
import {GebindeFormData} from "../models/types";

export default async function GebindeAction(data: ActionFunctionArgs) {
    const formData = await data.request.formData();
    const postData = Object.fromEntries(formData);
    const putUrl = `gebinde/fill`;
    const requestBody: GebindeFormData = {
        name: postData["name-gebinde"].toString(),
        number: +postData["num-gebinde"]
    }
    return await axios
            .put(putUrl, requestBody)
            .then((response) => {
                return {messageText: response.data, status: "success"};
            })
            .catch((reason) => {
                return {messageText: reason.response.data, status: "error"};
            });
}