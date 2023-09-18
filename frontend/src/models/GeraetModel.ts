import {AusruestungModel} from "./AusruestungModel";

export type GeraetModel = {
    id: number,
    bezeichnung: string,
    ausruestungAnlagenId: AusruestungModel
}