import {
    AppBar,
    Button,
    ButtonGroup,
    createStyles, Fade,
    Grid,
    makeStyles, Menu, MenuItem,
    Theme,
    Toolbar,
    Typography,
} from "@material-ui/core";
import {MouseEvent, ReactChild, useContext, useState} from "react";
import {Link} from "react-router-dom";
import {Utiles} from "./MyRoutes";
import {useHistory} from "react-router";


const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        title: {
            flexGrow: 1,
        },
        buttonGroup: {
            flexGrow: 1,
        },
        link: {
            textDecoration: "none",
            color: "black",
        },
    })
);

type MyAppBarProps = { children: ReactChild };
const MyAppBar = (props: MyAppBarProps) => {
    const classes = useStyles();
    const utiles = useContext(Utiles);
    const history = useHistory()
    const [anchorElAdmin, setAnchorElAdmin] = useState<null | Element>(null);

    const handleClickAdmin = (event: MouseEvent) => {
        setAnchorElAdmin(event.currentTarget);
    };
    const handleCloseAdmin = (event: MouseEvent, path: string | null) => {
        event.preventDefault()
        if (path !== null) {
            history.push("/administrar" + path);
        }
        setAnchorElAdmin(null);
    };

    function handleClickSalir(event: MouseEvent) {
        event.preventDefault();
        utiles?.logout();
    }

    function getButtons() {
        let salida = new Array<ReactChild>();
        utiles?.routes.filter(route => route.type === "usuario").forEach((route, key) =>
            salida.push(<Button key={key} component={Link} to={route.path}>{route.label}</Button>)
        )
        if (utiles?.routes.some(route => route.type === "administrador")) {
            salida.push(<Button key={salida.length + 1} onClick={handleClickAdmin}>Administrar</Button>)
        }
        return salida;
    }

    function getMenuAdmin() {
        if (utiles?.routes.some(route => route.type === "administrador")) {
            let menuItem = new Array<ReactChild>()
            utiles?.routes.filter(route => route.type === "administrador").forEach((route, index) =>
                menuItem.push(
                    <MenuItem key={index}
                              onClick={(event: MouseEvent) => handleCloseAdmin(event, route.path)}>{route.label}</MenuItem>
                ))
            return (
                <Menu
                    id="simple-menu"
                    anchorEl={anchorElAdmin}
                    keepMounted
                    open={Boolean(anchorElAdmin)}
                    onClose={(event: MouseEvent) => handleCloseAdmin(event, null)}
                    getContentAnchorEl={null}
                    anchorOrigin={{
                        vertical: 'bottom',
                        horizontal: 'left',
                    }}
                    TransitionComponent={Fade}
                >
                    {menuItem}
                </Menu>
            )
        } else {
            return null;
        }
    }

    return (
        <Grid container direction="column">
            <Grid item>
                <AppBar position="static">
                    <Toolbar>
                        <Typography variant="h6" className={classes.title}>
                            Gestión de Eventos
                        </Typography>
                        <ButtonGroup className={classes.buttonGroup}>
                            {getButtons()}
                        </ButtonGroup>
                        <Button color="inherit" onClick={handleClickSalir}>
                            Salir
                        </Button>
                    </Toolbar>
                </AppBar>
            </Grid>
            <Grid>
                {getMenuAdmin()}
            </Grid>
            <Grid item>{props.children}</Grid>
        </Grid>
    );
};

export default MyAppBar;
