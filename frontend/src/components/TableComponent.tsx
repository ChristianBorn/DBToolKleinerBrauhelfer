import React from 'react';
import Title from "../dashboard/Title";
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Table from '@mui/material/Table';

type componentProps = {
    title:string
    objectsToDisplay: {[index:string]: any}[]
}
function TableComponent(props: componentProps) {
    const headers = Object.keys(props.objectsToDisplay[0])
    return (
        <React.Fragment>
            <Title>{props.title}</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        {headers.map(header => (
                            <TableCell align="right">{header.toUpperCase()}</TableCell>
                        ))}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.objectsToDisplay.map((emp, index) => (
                        <TableRow key={index}>
                            {headers.map(header => (
                                <TableCell align="right">{JSON.stringify(emp[header])}</TableCell>
                            ))}
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}

export default TableComponent;