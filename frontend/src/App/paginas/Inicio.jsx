import {
  Grid,
  makeStyles,
  TextField,
  Button,
  Typography,
  Paper,
} from "@material-ui/core";
import React, { useContext } from "react";
import { Sesion } from "../App";

const useStyles = makeStyles((teme) => ({
  root: {
    marginTop: "20vh",
  },
  inputs: {
    marginBottom: "15px",
  },
  marcoInputs: {
    width: "60vw",
    maxWidth: "400px",
  },
  tipografia: {
    marginBottom: "20px",
  },
  marco: {
    boxShadow: teme.shadows[5],
    borderRadius: "20px",
    padding: "25px",
  },
}));

export default function Inicio() {
  const sesion = useContext(Sesion);
  const classes = useStyles();

  const entrar = (e) => {
    e.preventDefault();
    sesion.entrar(e.target.nombre, e.target);
  };

  return (
    <Grid
      className={classes.root}
      container
      justify="center"
      alignItems="center"
    >
      <Paper className={classes.marco} elevation={3}>
        <Grid container item justify="center">
          <Typography variant="h4" className={classes.tipografia}>
            Gestión De Eventos
          </Typography>
        </Grid>
        <form>
          <Grid
            container
            item
            direction="column"
            className={classes.marcoInputs}
          >
            <TextField
              className={classes.inputs}
              id="usuario"
              label="Usuario"
            />
            <TextField
              className={classes.inputs}
              id="contraseña"
              type="password"
              label="Contraseña"
            />
          </Grid>
          <Grid container item justify="center">
            <Button flo="true" variant="contained" onClick={entrar}>
              Entrar
            </Button>
          </Grid>
        </form>
      </Paper>
    </Grid>
  );
}
