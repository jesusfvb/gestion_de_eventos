import React from "react";
import {
  BrowserRouter,
  Redirect,
  Route,
  Switch,
} from "react-router-dom/cjs/react-router-dom.min";
import ControlUsuario from "../paginas/administrador/ControlUsuarios";
import Error404 from "../paginas/Error404";
import Principal from "../paginas/Principal";

export default function Rutas() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/home" component={Principal} />
        <Route exact path="/gestion/usuario" component={ControlUsuario} />
        <Redirect exact to="/gestion/usuario" from="/" />
        <Route component={Error404} />
      </Switch>
    </BrowserRouter>
  );
}
