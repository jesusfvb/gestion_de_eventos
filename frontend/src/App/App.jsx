import React, { useEffect, useState } from "react";
import Rutas from "./componentes/Rutas.jsx";
import Inicio from "./paginas/Inicio.jsx";

export const Sesion = React.createContext({});

export default function App() {
  const [autenticado, setAutenticado] = useState(undefined);

  useEffect(() => {
    let sesion = sessionStorage.getItem("sesion");
    setAutenticado(sesion === "true" ? true : false);
  }, []);

  const iniciarSesion = (usuario, contrasenna) => {
    sessionStorage.setItem("sesion", true);
    setAutenticado(true);
  };

  const finalizarSesion = () => {
    sessionStorage.setItem("sesion", false);
    setAutenticado(false);
  };

  return (
    <Sesion.Provider
      value={{
        entrar: iniciarSesion,
        finalizar: finalizarSesion,
        server:
          window.location.protocol + "//" + window.location.hostname + ":8080",
      }}
    >
      {autenticado === undefined ? null : autenticado ? <Rutas /> : <Inicio />}
    </Sesion.Provider>
  );
}
