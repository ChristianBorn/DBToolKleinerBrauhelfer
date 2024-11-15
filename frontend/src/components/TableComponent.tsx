import React from 'react';
import Title from "../dashboard/Title";
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Table from '@mui/material/Table';
import Button from '@mui/material/Button';


type componentProps = {
    title: string
    objectsToDisplay: { [index: string]: any }[],
    hasActionColumn: boolean
}

function TableComponent(props: componentProps) {
    const headers = Object.keys(props.objectsToDisplay[0])
    return (
        <React.Fragment>
            <Title>{props.title}</Title>
            <Table size="medium">
                <TableHead>
                    <TableRow key={"head"}>
                        {headers.map(header => (
                            <TableCell key={header} align="left">
                                {header.toUpperCase()}
                            </TableCell>
                        ))}
                        {props.hasActionColumn &&
                            <TableCell key={"actions"}>
                                Aktionen
                            </TableCell>
                        }
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.objectsToDisplay.map((emp, index) => (
                        <TableRow key={index}>
                            {headers.map(header => (
                                <TableCell key={Math.random()} align="left">
                                    {JSON.stringify(emp[header]).replaceAll("\"", "")}
                                </TableCell>
                            ))}
                            {props.hasActionColumn &&
                                <TableCell>
                                    <Button color={"info"} variant="outlined">
                                        Eintrag ändern
                                    </Button>
                                </TableCell>}
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}

export default TableComponent;