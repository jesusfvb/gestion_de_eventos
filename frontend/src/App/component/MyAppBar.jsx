import React from "react";
import {
    AppBar, Button, ButtonGroup,
    Grid,
    IconButton,
    makeStyles, Menu, MenuItem,
    Toolbar,
    Typography, withStyles,
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
    linksContainer: {
        flexGrow: 1
    },
    links: {
        textDecoration: "none",
        color: "black"
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
                            <div className={classes.linksContainer}>
                                <ButtonGroup variant="text">
                                    {
                                        session.routeUsers.map((value, index) => {
                                            if (!(value.name === null || value.name === undefined)) {
                                                return (
                                                    <Button key={"button-route-user-" + index}>
                                                        <Link className={classes.links}
                                                              to={value.path}>{value.name}</Link>
                                                    </Button>
                                                )
                                            } else {
                                                return null;
                                            }
                                        })
                                    }
                                    {
                                        (session.roles.some(role => role.authority === "ADMINISTRATION")) ?
                                            <MenuAdministrator/> : null
                                    }
                                </ButtonGroup>
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
    const session = React.useContext(Session)
    const classes = useStyles()
    const [anchorEl, setAnchorEl] = React.useState(null);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    const StyledMenu = withStyles({
        paper: {
            border: '1px solid #d3d4d5',
        },
    })((props) => (
        <Menu
            elevation={0}
            getContentAnchorEl={null}
            anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
            }}
            transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
            }}
            {...props}
        />
    ));

    return (
        <div>
            <Button onClick={handleClick}>
                Administrar
            </Button>
            <StyledMenu
                anchorEl={anchorEl}
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                {
                    session.routeAdmin.map((value, index) => (
                        <MenuItem key={"route-button-admin-" + index}>
                            <Link onClick={handleClose} className={classes.links}
                                  to={"/administration" + value.path}>{value.name}</Link>
                        </MenuItem>
                    ))
                }
            </StyledMenu>
        </div>
    );
}
