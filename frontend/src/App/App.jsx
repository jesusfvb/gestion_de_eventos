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
    axios.defaults.baseURL = "http://localhost:8080";
    const classes = useStyles()
    const [authenticated, setAuthenticated] = React.useState(undefined)

    const verifySession = () => {
        let jwt = sessionStorage.getItem("jwt")
        if (!(jwt === undefined || jwt === null) && decode(jwt).exp > new Date().getMilliseconds()) {
            axios.defaults.headers.common = {Accept: "application/json, text/plain, */*", Authorization: jwt}
            setAuthenticated(jwt)
        } else {
            axios.defaults.headers.common = {Accept: "application/json, text/plain, */*"}
            setAuthenticated(null)
        }
    }
    React.useEffect(verifySession, []);

    const logIn = (even) => {
        even.preventDefault()
        let body = new FormData()
        body.append("username", even.target.username.value)
        body.append("password", even.target.password.value)
        axios
            .post("/user/logIn", body)
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


    if (authenticated === undefined) {
        return null;
    } else if (typeof authenticated === "string") {
        const jwtDecode = decode(authenticated)
        return (
            <Session.Provider
                value={{
                    logOut: logOut,
                    username: jwtDecode.sub,
                    roles: jwtDecode.roles,
                }}>
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
                                    Gestión de Eventos
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
