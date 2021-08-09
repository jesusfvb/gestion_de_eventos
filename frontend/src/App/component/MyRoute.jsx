import React from "react";
// noinspection ES6CheckImport
import {BrowserRouter, Switch, Route} from "react-router-dom";
import User from "../pages/administration/User";
import {Session} from "../App";

export default function MyRoute() {
    const session = React.useContext(Session)

    return (
        <BrowserRouter>
            <Switch>
                <Route exact path="/" render={User}/>
                <Route />
            </Switch>
        </BrowserRouter>
    )
}
