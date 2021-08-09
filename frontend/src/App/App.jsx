import React from "react";
import axios from "axios";
import {decode} from "jsonwebtoken";
import {Button, Grid, makeStyles, Paper, TextField, Typography} from "@material-ui/core";
import MyRute from "./component/MyRoute";

export const Session = React.createContext(null);

const useStyles = makeStyles((theme) => ({

    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
        backgroundColor: theme.palette.background.default,
        marginTop: "calc(50vh - 180px)"
    },
    input: {
        margin: "0px 20px 20px 20px",
        width: "250px"
    }
}));

export default function App() {
    const classes = useStyles()
    const host = "http://localhost:8080"
    const [authenticated, setAuthenticated] = React.useState(null)

    const verifySession = () => {
        const jwt = sessionStorage.getItem("jwt")
        if (!(jwt === undefined || jwt === null) && decode(jwt).exp > new Date().getMilliseconds()) {
            setAuthenticated(true)
        } else {
            setAuthenticated(false)
        }
    }
    React.useEffect(verifySession, []);

    const logIn = (even) => {
        even.preventDefault()
        let body = new FormData()
        body.append("username", even.target.username.value)
        body.append("password", even.target.password.value)
        axios
            .post(host + "/user/logIn", body)
            .then(response => {
                sessionStorage.setItem("jwt", response.data)
                verifySession()
            })
            .catch(error => console.error(error))
    }

    const logOut = (even) => {
        even.preventDefault()
        sessionStorage.clear();
        verifySession()
    }

    const getRoles = () => {
        let jwt = sessionStorage.getItem("jwt")
        if (jwt === undefined || jwt === null) {
            return []
        } else {
            return decode(jwt).roles
        }
    }

    const getUsername = () => {
        let jwt = sessionStorage.getItem("jwt")
        if (jwt === undefined || jwt === null) {
            return null
        } else {
            return decode(jwt).sub
        }
    }

    if (authenticated == null) {
        return null;
    } else if (authenticated) {
        return (
            <Session.Provider value={{logOut: logOut, roles: getRoles(), username: getUsername()}}>
                <MyRute/>
            </Session.Provider>
        )
    } else {
        return (
            <Grid container direction="column" justify="center" alignItems="center">
                <Paper className={classes.paper}>
                    <form onSubmit={logIn}>
                        <Grid container direction="column" justify="center" alignItems="center">
                            <Grid>
                                <Typography variant="h5">
                                    Gestion de Eventos
                                </Typography>
                            </Grid>
                            <Grid>
                                <TextField className={classes.input} id="username" label="Usuario"/>
                            </Grid>
                            <Grid>
                                <TextField className={classes.input} id="password" label="Contraseña"/>
                            </Grid>
                            <Grid>
                                <Button variant="contained" color="primary" type="submit">Aceptar</Button>
                            </Grid>
                        </Grid>
                    </form>
                </Paper>
            </Grid>
        )
    }
}
