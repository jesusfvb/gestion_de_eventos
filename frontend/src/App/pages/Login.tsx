import {
    Button,
    Grid,
    makeStyles,
    Paper,
    TextField,
    Typography,
} from "@material-ui/core";
import {grey} from "@material-ui/core/colors";
import {KeyboardEvent, MouseEvent, useRef} from "react";

const useStyles = makeStyles((theme) => ({
    root: {
        width: 350,
        height: 280,
        position: "relative",
        top: "25vh",
        backgroundColor: grey[200],
    },
    input: {
        marginTop: 20,
    },
}));

type TypeProps = { login: Function };

const Login = (props: TypeProps) => {
    const classes = useStyles();
    const inputContainer = useRef<HTMLElement>();

    function handleClick(event: MouseEvent) {
        event.preventDefault();
        let inputs = inputContainer.current?.getElementsByTagName("input");
        props.login(
            inputs?.namedItem("username")?.value,
            inputs?.namedItem("password")?.value
        );
    }

    function handleClickEnter(event: KeyboardEvent) {
        if (event.code === "Enter") {
            event.preventDefault();
            let inputs = inputContainer.current?.getElementsByTagName("input");
            props.login(
                inputs?.namedItem("username")?.value,
                inputs?.namedItem("password")?.value
            );
        }
    }

    return (
        <Grid
            container
            direction="column"
            justifyContent="center"
            alignItems="center"
            onKeyPress={handleClickEnter}
        >
            <Grid item>
                <Paper className={classes.root}>
                    <Grid
                        container
                        direction="column"
                        justifyContent="center"
                        alignItems="center"
                        spacing={5}
                        innerRef={inputContainer}
                    >
                        <Grid>
                            <Typography variant="h4" className={classes.input}>
                                Gestión de Eventos
                            </Typography>
                        </Grid>
                        <Grid>
                            <TextField
                                className={classes.input}
                                label="Usuario"
                                id="username"
                            />
                        </Grid>
                        <Grid>
                            <TextField
                                className={classes.input}
                                label="Contraseña"
                                id="password"
                                type="password"
                            />
                        </Grid>
                        <Grid>
                            <Button
                                className={classes.input}
                                variant="contained"
                                color="primary"
                                onClick={handleClick}
                            >
                                Aceptar
                            </Button>
                        </Grid>
                    </Grid>
                </Paper>
            </Grid>
        </Grid>
    );
};

export default Login;
