import React, {useState, useCallback, useEffect} from 'react';
import axios from "axios";

import Title from "../dashboard/Title";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import {GeraetModel} from "../models/GeraetModel";
import {AusruestungModel} from "../models/AusruestungModel";

function Ausruestung() {
    const [geraete, setGeraete] = useState<GeraetModel[]>()
    const [ausruestung, setAusruestung] = useState<AusruestungModel[]>()
    const getGerate = useCallback(() => {
        axios.get("/geraete")
            .then((response) => response.data)
            .catch((error) => console.error("Error while getting Geraete:" + error))
            .then(setGeraete)
    }, [])
    const getAusruestung = useCallback(() => {
        axios.get("/ausruestung")
            .then((response) => response.data)
            .catch((error) => console.error("Error while getting Ausrüstung:" + error))
            .then(setAusruestung)
    }, [])
    useEffect(() => {
        getGerate()
        getAusruestung()
    }, [])
    return (
        <React.Fragment>
            <Title>Ausrüstung</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Name</TableCell>
                        <TableCell>Typ</TableCell>
                        <TableCell>Durschnittliche Sudhausausbeute</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {ausruestung ? ausruestung.map((ausruestung) => (
                        <TableRow key={ausruestung.id}>
                            <TableCell>{ausruestung.id}</TableCell>
                            <TableCell>{ausruestung.name}</TableCell>
                            <TableCell>{ausruestung.typ}</TableCell>
                            <TableCell>{ausruestung.sudhausausbeute}</TableCell>
                        </TableRow>
                    )) : "Keine Ausrüstung gefunden"}
                </TableBody>
            </Table>
            <Title>Geräte</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Bezeichnung</TableCell>
                        <TableCell >Teil von Anlage</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {geraete ? geraete.map((geraet) => (
                        <TableRow key={geraet.id}>
                            <TableCell>{geraet.id}</TableCell>
                            <TableCell>{geraet.bezeichnung}</TableCell>
                            <TableCell>{geraet.ausruestungAnlagenId.name}</TableCell>
                        </TableRow>
                    )) : "Keine Geraete gefunden"}
                </TableBody>
            </Table>

        </React.Fragment>

    );
}

export default Ausruestung;