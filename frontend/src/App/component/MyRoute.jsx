import React from "react";
// noinspection ES6CheckImport
import {BrowserRouter, Switch, Route, Redirect} from "react-router-dom";
import User from "../pages/administration/User";
import {Session} from "../App";

export default function MyRoute() {
    const session = React.useContext(Session)
    return (
        <BrowserRouter>
            <Switch>
                {(session.roles.some(role => role.authority === "USER")) ?
                    <Route exact path="/administration/users" render={User}/>
                    : null}
                <Redirect to="/administration/users" from="/"/>
            </Switch>
        </BrowserRouter>
    )
}
