import {AusruestungModel} from "./AusruestungModel";

export type GeraetModel = {
    [index: string]: string | number | AusruestungModel,
    id: number,
    bezeichnung: string,
    ausruestungAnlagenId: AusruestungModel
}