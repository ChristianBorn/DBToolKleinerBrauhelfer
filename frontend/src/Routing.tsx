import React from 'react';
import Start from "./views/Start";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Ausruestung from "./views/Ausruestung";

function Routing() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path={"/start"} element={<Start/>}></Route>
                <Route path={"/ausruestung"} element={<Ausruestung/>}></Route>

            </Routes>
        </BrowserRouter>
    );
}

export default Routing;