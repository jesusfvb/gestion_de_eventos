import React from "react";
import MyAppBar from "../../component/MyAppBar";
import clsx from 'clsx';
import {lighten, makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import Checkbox from '@material-ui/core/Checkbox';
import IconButton from '@material-ui/core/IconButton';
import Tooltip from '@material-ui/core/Tooltip';
import DeleteIcon from '@material-ui/icons/Delete';
import FilterListIcon from '@material-ui/icons/FilterList';
import axios from "axios";
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import AppBar from '@material-ui/core/AppBar';
import CloseIcon from '@material-ui/icons/Close';
import Slide from '@material-ui/core/Slide';
import AddIcon from '@material-ui/icons/Add';
import {
    DialogActions,
    DialogContent,
    DialogTitle,
    Grid,
    List,
    ListItem,
    ListItemIcon,
    ListItemText,
    TextField
} from "@material-ui/core";
import CachedIcon from '@material-ui/icons/Cached';
import AccessibilityNewIcon from '@material-ui/icons/AccessibilityNew';

export default function User() {
    return (
        <MyAppBar>
            <MyTable/>
        </MyAppBar>
    )
}

const useStylesMyTable = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    paper: {
        width: '100%',
        marginBottom: theme.spacing(2),
    },
    table: {
        minWidth: 750,
    },
    visuallyHidden: {
        border: 0,
        clip: 'rect(0 0 0 0)',
        height: 1,
        margin: -1,
        overflow: 'hidden',
        padding: 0,
        position: 'absolute',
        top: 20,
        width: 1,
    },
    rootToolbar: {
        paddingLeft: theme.spacing(2),
        paddingRight: theme.spacing(1),
    },
    highlight:
        theme.palette.type === 'light'
            ? {
                color: theme.palette.secondary.main,
                backgroundColor: lighten(theme.palette.secondary.light, 0.85),
            }
            : {
                color: theme.palette.text.primary,
                backgroundColor: theme.palette.secondary.dark,
            },
    title: {
        flex: '1 1 100%',
    },
}));

function MyTable(props) {
    const headCells = [
        {id: 'name', numeric: false, disablePadding: true, label: 'Nombre'},
        {id: 'surname', numeric: true, disablePadding: false, label: 'Apellidos'},
        {id: 'username', numeric: true, disablePadding: false, label: 'Usuario'},
        {id: 'dni', numeric: true, disablePadding: false, label: 'CI'},
        {id: 'roles', numeric: true, disablePadding: false, label: 'Rol'},
    ];

    const classes = useStylesMyTable();
    const [rows, setRows] = React.useState([])
    const [order, setOrder] = React.useState('asc');
    const [orderBy, setOrderBy] = React.useState('calories');
    const [selected, setSelected] = React.useState([]);
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(9);
    const emptyRows = rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);

    const getData = (even) => {
        axios
            .get("/user")
            .then(response => setRows(response.data))
            .catch(error => console.error(error.message))
    }
    React.useEffect(getData, [])

    const deleteUsers = (event) => {
        event.preventDefault()
        const body = new FormData()
        body.append("ids", selected.toString())
        axios
            .delete("/user", {data: body})
            .then(() => {
                setSelected([])
                getData()
            })
            .catch(error => console.error(error))
    }

    const handleClick = (event, id) => {
        const selectedIndex = selected.indexOf(id);
        let newSelected = [];
        if (selectedIndex === -1) {
            newSelected = newSelected.concat(selected, id);
        } else if (selectedIndex === 0) {
            newSelected = newSelected.concat(selected.slice(1));
        } else if (selectedIndex === selected.length - 1) {
            newSelected = newSelected.concat(selected.slice(0, -1));
        } else if (selectedIndex > 0) {
            newSelected = newSelected.concat(
                selected.slice(0, selectedIndex),
                selected.slice(selectedIndex + 1),
            );
        }
        setSelected(newSelected);
    };
    const handleSelectAllClick = (event) => {
        if (event.target.checked) {
            const newSelected = rows.map((n) => n.id);
            setSelected(newSelected);
            return;
        }
        setSelected([]);
    };
    const isSelected = (id) => selected.indexOf(id) !== -1;

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };
    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleRequestSort = (event, property) => {
        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };
    const descendingComparator = (a, b, orderBy) => {
        if (b[orderBy] < a[orderBy]) {
            return -1;
        }
        if (b[orderBy] > a[orderBy]) {
            return 1;
        }
        return 0;
    }
    const getComparator = (order, orderBy) => {
        return order === 'desc'
            ? (a, b) => descendingComparator(a, b, orderBy)
            : (a, b) => -descendingComparator(a, b, orderBy);
    }
    const stableSort = (array, comparator) => {
        const stabilizedThis = array.map((el, index) => [el, index]);
        stabilizedThis.sort((a, b) => {
            const order = comparator(a[0], b[0]);
            if (order !== 0) return order;
            return a[1] - b[1];
        });
        return stabilizedThis.map((el) => el[0]);
    }

    const EnhancedTableToolbar = (props) => {
        const classes = useStylesMyTable();
        const {numSelected} = props;

        return (
            <Toolbar
                className={clsx(classes.rootToolbar, {
                    [classes.highlight]: numSelected > 0,
                })}
            >
                {numSelected > 0 ? (
                    <Typography className={classes.title} color="inherit" variant="subtitle1" component="div">
                        {numSelected} seleccionado(s)
                    </Typography>
                ) : (
                    <Typography className={classes.title} variant="h6" id="tableTitle" component="div">
                        Usuarios
                    </Typography>
                )}

                {numSelected > 0 ? (
                    <>
                        {
                            (numSelected !== 1) ? null :
                                <>
                                    <UpdateUser update={getData} setSelected={setSelected}
                                                user={rows.find(user => user.id === selected[0])}/>
                                    <Role update={getData} setSelected={setSelected}
                                          user={rows.find(user => user.id === selected[0])}/>
                                </>
                        }
                        <Tooltip title="Borrar">
                            <IconButton aria-label="delete" onClick={deleteUsers}>
                                <DeleteIcon/>
                            </IconButton>
                        </Tooltip>

                    </>
                ) : (
                    <>
                        <Tooltip title="Filtrar">
                            <IconButton aria-label="filter list">
                                <FilterListIcon/>
                            </IconButton>
                        </Tooltip>
                        <NewUser update={getData}/>
                    </>
                )}
            </Toolbar>
        );
    };
    const EnhancedTableHead = (props) => {
        const {classes, onSelectAllClick, order, orderBy, numSelected, rowCount, onRequestSort} = props;
        const createSortHandler = (property) => (event) => {
            onRequestSort(event, property);
        };

        return (
            <TableHead>
                <TableRow>
                    <TableCell padding="checkbox">
                        <Checkbox
                            indeterminate={numSelected > 0 && numSelected < rowCount}
                            checked={rowCount > 0 && numSelected === rowCount}
                            onChange={onSelectAllClick}
                            inputProps={{'aria-label': 'select all desserts'}}
                        />
                    </TableCell>
                    {headCells.map((headCell) => (
                        <TableCell
                            key={headCell.id}
                            align={headCell.numeric ? 'right' : 'left'}
                            padding={headCell.disablePadding ? 'none' : 'default'}
                            sortDirection={orderBy === headCell.id ? order : false}
                        >
                            <TableSortLabel
                                active={orderBy === headCell.id}
                                direction={orderBy === headCell.id ? order : 'asc'}
                                onClick={createSortHandler(headCell.id)}
                            >
                                {headCell.label}
                                {orderBy === headCell.id ? (
                                    <span className={classes.visuallyHidden}>
                  {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                </span>
                                ) : null}
                            </TableSortLabel>
                        </TableCell>
                    ))}
                </TableRow>
            </TableHead>
        );
    }
    return (
        <div className={classes.root}>
            <Paper className={classes.paper}>
                <EnhancedTableToolbar numSelected={selected.length}/>
                <TableContainer>
                    <Table
                        className={classes.table}
                        aria-labelledby="tableTitle"
                        size='medium'
                        aria-label="enhanced table"
                    >
                        <EnhancedTableHead
                            classes={classes}
                            numSelected={selected.length}
                            order={order}
                            orderBy={orderBy}
                            onSelectAllClick={handleSelectAllClick}
                            onRequestSort={handleRequestSort}
                            rowCount={rows.length}
                        />
                        <TableBody>
                            {stableSort(rows, getComparator(order, orderBy))
                                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                .map((row, index) => {
                                    const isItemSelected = isSelected(row.id);
                                    const labelId = `enhanced-table-checkbox-${index}`;

                                    return (
                                        <TableRow
                                            hover
                                            onClick={(event) => handleClick(event, row.id)}
                                            role="checkbox"
                                            aria-checked={isItemSelected}
                                            tabIndex={-1}
                                            key={row.name}
                                            selected={isItemSelected}
                                        >
                                            <TableCell padding="checkbox">
                                                <Checkbox
                                                    checked={isItemSelected}
                                                    inputProps={{'aria-labelledby': labelId}}
                                                />
                                            </TableCell>
                                            <TableCell component="th" id={labelId} scope="row" padding="none">
                                                {row.name}
                                            </TableCell>
                                            <TableCell align="right">{row.surname}</TableCell>
                                            <TableCell align="right">{row.username}</TableCell>
                                            <TableCell align="right">{row.dni}</TableCell>
                                            <TableCell
                                                align="right">{row.roles.map(role => role.substr(0, 2)).toString()}</TableCell>
                                        </TableRow>
                                    );
                                })}
                            {emptyRows > 0 && (
                                <TableRow style={{height: 53 * emptyRows}}>
                                    <TableCell colSpan={6}/>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[9, 15, 25]}
                    component="div"
                    count={rows.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                />
            </Paper>
        </div>
    );
}

const useStylesModal = makeStyles((theme) => ({
    appBar: {
        position: 'relative',
    },
    title: {
        marginLeft: theme.spacing(2),
        flex: 1,
    },
    inputs: {
        width: 300
    },
    containerInputs: {
        marginTop: "20vh",
        width: "100%"
    }
}));

function NewUser(props) {
    const classes = useStylesModal();
    const [open, setOpen] = React.useState(false);
    const inputContainer = React.createRef()

    const handleClickOpen = (even) => {
        even.preventDefault()
        setOpen(true);
    };
    const handleClose = (even) => {
        even.preventDefault()
        setOpen(false);
    };
    const Transition = React.forwardRef(function Transition(props, ref) {
        return <Slide direction="up" ref={ref} {...props} />;
    });

    const submit = (even) => {
        const inputs = inputContainer.current.getElementsByTagName("input")
        const body = new FormData()
        body.append("name", inputs.name.value)
        body.append("surname", inputs.surname.value)
        body.append("username", inputs.username.value)
        body.append("dni", inputs.dni.value)
        axios
            .post("/user", body)
            .then(() => props.update())
            .catch(error => console.error(error))
        handleClose(even);
    }

    return (
        <div>
            <Tooltip title="Nuevo Usuario">
                <IconButton aria-label="filter list" onClick={handleClickOpen}>
                    <AddIcon/>
                </IconButton>
            </Tooltip>
            {
                (!open) ? null :
                    <Dialog fullScreen open={open} onClose={handleClose} TransitionComponent={Transition}>
                        <AppBar className={classes.appBar}>
                            <Toolbar>
                                <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
                                    <CloseIcon/>
                                </IconButton>
                                <Typography variant="h6" className={classes.title}>
                                    Nuevo Usuario
                                </Typography>
                                <Button autoFocus color="inherit" onClick={submit}>
                                    save
                                </Button>
                            </Toolbar>
                        </AppBar>
                        <Grid container className={classes.containerInputs} direction={"column"} spacing={2}
                              alignItems={"center"} alignContent={"center"} ref={inputContainer}>
                            <Grid item><TextField className={classes.inputs} id="name" label="Nombre"/></Grid>
                            <Grid item><TextField className={classes.inputs} id="surname" label="Apellido"/></Grid>
                            <Grid item><TextField className={classes.inputs} id="username" label="Usuario"/></Grid>
                            <Grid item><TextField className={classes.inputs} id="dni" label="CI" type="number"/></Grid>
                        </Grid>
                    </Dialog>
            }
        </div>
    );
}

function UpdateUser(props) {
    const classes = useStylesModal();
    const [open, setOpen] = React.useState(false);
    const inputContainer = React.createRef()

    const handleClickOpen = (even) => {
        even.preventDefault()
        setOpen(true);
    };
    const handleClose = (even) => {
        even.preventDefault()
        props.setSelected([])
        setOpen(false);
    };
    const Transition = React.forwardRef(function Transition(props, ref) {
        return <Slide direction="up" ref={ref} {...props} />;
    });

    const submit = (even) => {
        const inputs = inputContainer.current.getElementsByTagName("input")
        const body = new FormData()
        body.append("id", props.user.id)
        body.append("name", inputs.name.value)
        body.append("surname", inputs.surname.value)
        body.append("username", inputs.username.value)
        body.append("dni", inputs.dni.value)
        axios
            .put("/user", body)
            .then(() => props.update())
            .catch(error => console.error(error))
        handleClose(even);
    }

    return (
        <div>
            <Tooltip title="Actualizar Usuario">
                <IconButton aria-label="filter list" onClick={handleClickOpen}>
                    <CachedIcon/>
                </IconButton>
            </Tooltip>
            {
                (!open) ? null :
                    <Dialog fullScreen open={open} onClose={handleClose} TransitionComponent={Transition}>
                        <AppBar className={classes.appBar}>
                            <Toolbar>
                                <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
                                    <CloseIcon/>
                                </IconButton>
                                <Typography variant="h6" className={classes.title}>
                                    Modificar Usuario
                                </Typography>
                                <Button autoFocus color="inherit" onClick={submit}>
                                    save
                                </Button>
                            </Toolbar>
                        </AppBar>
                        <Grid container className={classes.containerInputs} direction={"column"} spacing={2}
                              alignItems={"center"} alignContent={"center"} ref={inputContainer}>
                            <Grid item><TextField className={classes.inputs} id="name" label="Nombre"
                                                  defaultValue={props.user.name}/></Grid>
                            <Grid item><TextField className={classes.inputs} id="surname" label="Apellido"
                                                  defaultValue={props.user.surname}/></Grid>
                            <Grid item><TextField className={classes.inputs} id="username" label="Usuario"
                                                  defaultValue={props.user.username}/></Grid>
                            <Grid item><TextField className={classes.inputs} id="dni" label="CI" type="number"
                                                  defaultValue={props.user.dni}/></Grid>
                        </Grid>
                    </Dialog>
            }
        </div>
    );
}

const useStylesRole = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 360,
        backgroundColor: theme.palette.background.paper,
    },
}));

function Role(props) {
    const roles = [
        {id: 1, role: "ADMINISTRATION", label: "Administrador"},
    ]
    const getInitialRoles = () => {
        let initialRoles = []
        props.user.roles.forEach(rol => {
            let rolAux = roles.find(r => r.role === rol)
            if (rolAux !== undefined) {
                initialRoles.push(rolAux.id)
            }
        })
        return initialRoles
    }

    const classes = useStylesRole();
    const [open, setOpen] = React.useState(false);
    const [checked, setChecked] = React.useState(getInitialRoles());

    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        setChecked(newChecked);
    };

    const submit = (even) => {
        even.preventDefault()
        const body = new FormData()
        let rolesName = ["USER"]
        checked.forEach(id => {
            rolesName.push(roles.find(rol => rol.id === id).role)
        })
        body.append("id", props.user.id)
        body.append("roles", rolesName.toString())
        axios
            .put("/user/roles", body)
            .then(() => {
                props.setSelected([])
                props.update()
                handleClose()
            })
            .catch(error => console.error(error))
    }

    return (
        <div>
            <Tooltip title="Roles">
                <IconButton aria-label="filter list" onClick={handleClickOpen}>
                    <AccessibilityNewIcon/>
                </IconButton>
            </Tooltip>
            <Dialog disableBackdropClick disableEscapeKeyDown open={open} onClose={handleClose}>
                <DialogTitle> Roles </DialogTitle>
                <DialogContent>
                    <List className={classes.root}>
                        {roles.map((value) => {
                            const labelId = `checkbox-list-label-${value.id}`;
                            return (
                                <ListItem key={value} role={undefined} dense button onClick={handleToggle(value.id)}>
                                    <ListItemIcon>
                                        <Checkbox
                                            edge="start"
                                            checked={checked.indexOf(value.id) !== -1}
                                            tabIndex={-1}
                                            disableRipple
                                            inputProps={{'aria-labelledby': labelId}}
                                        />
                                    </ListItemIcon>
                                    <ListItemText id={labelId} primary={value.label}/>
                                </ListItem>
                            );
                        })}
                    </List>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={submit} color="primary">
                        Ok
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}