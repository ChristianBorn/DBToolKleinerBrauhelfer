import {GebindeModel} from "../models/GebindeModel";
import axios from 'axios'
import {GebindePageData} from "../models/types";
import {Kapazitaet} from "../models/Kapazitaet";

export default async function GebindeLoader(): Promise<GebindePageData> {
    const retrievedGebinde = await axios.get("/gebinde")
        .then((response) => response.data)
        .catch((error) => console.error("Error while getting Gebinde:" + error))
        .then((data) => {
            return data.map((gebindeEntry: GebindeModel) => {
                gebindeEntry.fassungsvermoegen = gebindeEntry.fassungsvermoegen + " L";
                return gebindeEntry;
            }) as GebindeModel[];
        })
    const retrievedKapazitaeten = await axios.get("/gebinde/kapazität/grouped")
        .then((response) => response.data)
        .catch((error) => console.error("Error while getting Kapazitäten:" + error))
        .then((data) => {
            return data.map((kapa: Kapazitaet) => {
                kapa["Summierte Kapazität"] = kapa["Summierte Kapazität"] + " L";
                return kapa;
            }) as Kapazitaet[];
        })
    return {gebinde: retrievedGebinde, kapazitaeten: retrievedKapazitaeten}
}