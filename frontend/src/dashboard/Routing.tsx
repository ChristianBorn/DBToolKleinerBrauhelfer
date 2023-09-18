import React from 'react';
import Start from "../views/Start";
import {BrowserRouter, Route, Routes} from "react-router-dom";

function Routing() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path={"/start"} element={<Start/>}></Route>

            </Routes>
        </BrowserRouter>
    );
}

export default Routing;