import React from "react";
// noinspection ES6CheckImport
import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";
import User from "../pages/administration/User";
import { Session } from "../App";
import Convocatoria from "../pages/Convocatoria";
import ConvocatoriaAdmin from "../pages/administration/Convocatoria";
import Evento from "../pages/Evento";

export default function MyRoute() {
    const session = React.useContext(Session)

    const routeUsers = [
        { path: "/convocatorias", render: Convocatoria, name: "Convocatoria" },
        { path: "/evento/:id", render: Evento, name: null },
    ]

    const routeAdmin = [
        { path: "/users", render: User, name: "Usuarios" },
        { path: "/convocatoria", render: ConvocatoriaAdmin, name: "Convocatorias" },
    ]
    return (
        <Session.Provider value={{
            ...session,
            routeUsers: routeUsers.map(value => ({ path: value.path, name: value.name })),
            routeAdmin: routeAdmin.map(value => ({ path: value.path, name: value.name }))
        }}>
            <BrowserRouter>
                <Switch>
                    <Redirect exact to="/convocatorias" from="/" />
                    {routeUsers.map((value, index) => (
                        <Route key={"route-" + index} exact path={value.path} render={value.render} />
                    ))}
                    {(session.roles.some(role => role.authority === "ADMINISTRATION")) ?
                        routeAdmin.map((value, index) =>
                            <Route key={"route-admin" + index} exact path={"/administration" + value.path}
                                render={value.render} />
                        ) : null}
                </Switch>
            </BrowserRouter>
        </Session.Provider>
    )
}
