import React from "react";
import {
  BrowserRouter,
  Redirect,
  Route,
  Switch,
} from "react-router-dom/cjs/react-router-dom.min";
import Usuario from "../paginas/administrador/Usuario";
import ConvocatoriaAdmin from "../paginas/administrador/Convocatoria";
import Solicitudes from "../paginas/coordinador/Solicitudes";
import Error404 from "../paginas/Error404";
import Principal from "../paginas/Principal";
import Evento from "../paginas/usuario/Evento";
import ConvocatoriaUser from "../paginas/usuario/Convocatoria";

export default function Rutas() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/home" component={Principal} />
        <Route exact path="/evento" component={Evento} />
        <Route exact path="/convocatoria" component={ConvocatoriaUser} />
        <Route exact path="/registrar/evento" component={Solicitudes} />
        <Route exact path="/administrar/usuario" component={Usuario} />
        <Route exact path="/administrar/convocatoria" component={ConvocatoriaAdmin} />
        <Redirect exact to="/convocatoria" from="/" />
        <Route component={Error404} />
      </Switch>
    </BrowserRouter>
  );
}
