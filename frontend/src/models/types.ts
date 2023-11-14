import {AusruestungModel} from "./AusruestungModel";
import {GeraetModel} from "./GeraetModel";
import {GebindeModel} from "./GebindeModel";
import {Kapazitaet} from "./Kapazitaet";

export type AusruestungPageData = {
    ausruestung: AusruestungModel[],
    geraete: GeraetModel[],
}

export type GebindePageData = {
    gebinde: GebindeModel[],
    kapazitaeten: Kapazitaet[],
}

export type GebindeFormData = {
    name: string,
    number: number
}

export type Message = {
    messageText: string,
    data?: Object,
    status: 'success' | 'info' | 'warning' | 'error'
}