import { Grid, Typography } from "@material-ui/core";
import React from "react";
import BarraDeNavegacion from "../componentes/BarraDeNavegacion";

export default function Principal() {
  return (
    <Grid container>
      <Grid container item>
        <BarraDeNavegacion />
      </Grid>
      <Grid container item justify="center">
        <Typography variant="h1">
          Descripción de La Web y Opciones...{" "}
        </Typography>
      </Grid>
    </Grid>
  );
}
