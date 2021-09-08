import {ComponentType, createContext, ReactChild} from "react";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import jwtService from "jsonwebtoken";
import Convocatorias from "../pages/Convocatorias";
import Eventos from "../pages/Eventos";
import AdminConvocatorias from "../pages/admin/AdminConvocatorias";

export type routesContext = { path: string; label: string, type: "usuario" | "administrador" };
type TypeContext = { logout: Function; routes: routesContext[] };
export const Utiles = createContext<TypeContext | null>(null);

type MyRoutesProps = { logout: Function };
type MyRouteDefinition = { path: string; component: ComponentType; label: string | null; };
const MyRoutes = (props: MyRoutesProps) => {
    const roles: { authority: string } [] = jwtService.decode(sessionStorage.getItem("jwt") + "", {json: true})?.roles
    const routesUser: MyRouteDefinition[] = [
        {path: "/convocatorias", component: Convocatorias, label: "Convocatorias"},
        {path: "/eventos/convocatoria/:id", component: Eventos, label: null,},
    ];
    const routesAdmin: MyRouteDefinition[] = [
        {path: "/convocatorias", label: "Convocatorias", component: AdminConvocatorias}
    ]

    function getRoutes() {
        let salida = new Array<ReactChild>();
        routesUser.forEach((route, index) =>
            salida.push(<Route key={index} exact path={route.path} component={route.component}/>)
        );
        if (roles?.some(role => role.authority === "ADMINISTRATION")) {
            routesAdmin.forEach((route, index) =>
                salida.push(<Route key={index} exact path={"/administrar" + route.path} component={route.component}/>)
            )
        }
        return salida;
    }

    function transformRoutes() {
        let salida: routesContext[] = [];
        routesUser.forEach((route) => {
            if (route.label !== null) {
                salida.push({path: route.path, label: route.label, type: "usuario"});
            }
        });
        if (roles?.some(role => role.authority === "ADMINISTRATION")) {
            routesAdmin.forEach((route) => {
                if (route.label !== null) {
                    salida.push({path: route.path, label: route.label, type: "administrador"});
                }
            });
        }
        return salida;
    }

    return (
        <Utiles.Provider value={{logout: props.logout, routes: transformRoutes()}}>
            <BrowserRouter>
                <Switch>
                    {getRoutes()}
                    <Redirect exact to="/convocatorias" from="/"/>
                </Switch>
            </BrowserRouter>
        </Utiles.Provider>
    );
};

export default MyRoutes;
