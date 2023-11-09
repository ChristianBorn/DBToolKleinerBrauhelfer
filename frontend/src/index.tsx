import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Start from "./views/Start";
import Ausruestung from "./views/Ausruestung";
import Gebinde from "./views/Gebinde";
import AusruestungLoader from "./loaders/AusruestungLoader";


const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);

const router = createBrowserRouter([
    {
        path: '/',
        element: <App />,
        children: [
            {
                path: 'start',
                element: <Start/>
            },
            {
                path: 'ausruestung',
                element: <Ausruestung/>,
                loader: AusruestungLoader,
            },
            {
                path: 'gebinde',
                element: <Gebinde/>
            },
        ]
    }
])

root.render(
    <React.StrictMode>
        <RouterProvider router={router}/>
        {/*<App/>*/}
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
