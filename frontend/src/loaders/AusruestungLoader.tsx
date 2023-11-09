import axios from "axios";
import {AusruestungPageData} from "../models/types";
import {GeraetModel} from "../models/GeraetModel";
import {AusruestungModel} from "../models/AusruestungModel";

export default async function AusruestungLoader(): Promise<AusruestungPageData> {
    const responseGerate = await axios.get("/geraete")
        .then((response) => response.data)
        .catch((error) => console.error("Error while getting Geraete:" + error))
        .then(((data) => data as GeraetModel[]));

    const responseAusruestung = await axios.get("/ausruestung")
        .then((response) => response.data)
        .catch((error) => console.error("Error while getting Ausrüstung:" + error))
        .then((data) => data as AusruestungModel[])

    return {geraete: responseGerate, ausruestung: responseAusruestung};
}