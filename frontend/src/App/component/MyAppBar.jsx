import React from "react";
import {
    AppBar, Button,
    Grid,
    IconButton,
    makeStyles, Menu, MenuItem,
    Toolbar,
    Typography,
} from "@material-ui/core";
import {Session} from "../App";
import {AccountCircle} from "@material-ui/icons";
import {Link} from "react-router-dom"

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    title: {
        flexGrow: 1,
    },
    links: {
        flexGrow: 1
    }
}));

export default function MyAppBar(props) {
    const session = React.useContext(Session)
    const classes = useStyles();
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);

    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <Grid container direction="column">
            <Grid item>
                <div className={classes.root}>
                    <AppBar position="static">
                        <Toolbar>
                            <Typography variant="h6" className={classes.title}>
                                Gestión de Eventos
                            </Typography>
                            <div className={classes.links}>
                                {
                                    (session.roles.some(role => role.authority === "USER")) ?
                                        <MenuAdministrator/>
                                        : null
                                }
                            </div>
                            <div>
                                <IconButton
                                    aria-label="account of current user"
                                    aria-controls="menu-appbar"
                                    aria-haspopup="true"
                                    onClick={handleMenu}
                                    color="inherit"
                                >
                                    <AccountCircle/>
                                </IconButton>
                                <Menu
                                    id="menu-appbar"
                                    anchorEl={anchorEl}
                                    anchorOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    keepMounted
                                    transformOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    open={open}
                                    onClose={handleClose}
                                >
                                    <MenuItem onClick={session.logOut}>Salir</MenuItem>
                                </Menu>
                            </div>
                        </Toolbar>
                    </AppBar>
                </div>
            </Grid>
            <Grid>
                {props.children}
            </Grid>
        </Grid>
    )
}

function MenuAdministrator() {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    return (
        <div>
            <Button onClick={handleClick}>
                Administrador
            </Button>
            <Menu
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                <MenuItem onClick={handleClose}>
                    <Link style={{textDecoration: "none"}} to="/administration/users">Administrar Usuarios</Link>
                </MenuItem>
            </Menu>
        </div>
    );
}
