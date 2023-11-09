import React from 'react';
import Chart from "../dashboard/Chart";
import Deposits from "../dashboard/Deposits";
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';


function Start() {
    return (
        <>
            {/* Chart */}
            <Grid item xs={12} md={8} lg={9}>
                <Paper
                    sx={{
                        p: 2,
                        display: 'flex',
                        flexDirection: 'column',
                        height: 240,
                    }}
                >
                    <Chart/>
                </Paper>
            </Grid>
            {/* Recent Deposits */}
            <Grid item xs={12} md={4} lg={3}>
                <Paper
                    sx={{
                        p: 2,
                        display: 'flex',
                        flexDirection: 'column',
                        height: 240,
                    }}
                >
                    <Deposits/>
                </Paper>
            </Grid>

        </>
    );
}

export default Start;