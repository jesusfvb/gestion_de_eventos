import React from "react";
import {
    AppBar,
    Grid,
    IconButton,
    makeStyles, Menu, MenuItem,
    Toolbar,
    Typography
} from "@material-ui/core";
import {Session} from "../App";
import {AccountCircle} from "@material-ui/icons";

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    title: {
        flexGrow: 1,
    },
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