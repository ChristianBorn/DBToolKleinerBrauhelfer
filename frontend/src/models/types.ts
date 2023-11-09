import {AusruestungModel} from "./AusruestungModel";
import {GeraetModel} from "./GeraetModel";

export type AusruestungPageData = {
    ausruestung: AusruestungModel[],
    geraete: GeraetModel[],
}