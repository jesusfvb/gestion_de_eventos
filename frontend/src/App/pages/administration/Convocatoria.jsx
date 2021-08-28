import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import Checkbox from '@material-ui/core/Checkbox';
import Dialog from '@material-ui/core/Dialog';
import Grid from '@material-ui/core/Grid';
import IconButton from '@material-ui/core/IconButton';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Paper from '@material-ui/core/Paper';
import Slide from '@material-ui/core/Slide';
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
import Tooltip from '@material-ui/core/Tooltip';
import Typography from '@material-ui/core/Typography';
import AddIcon from '@material-ui/icons/Add';
import CachedIcon from '@material-ui/icons/Cached';
import CloseIcon from '@material-ui/icons/Close';
import DeleteIcon from '@material-ui/icons/Delete';
import FilterListIcon from '@material-ui/icons/FilterList';
import axios from "axios";
import clsx from 'clsx';
import React from "react";
import MyAppBar from "../../component/MyAppBar";
import AccessibilityIcon from '@material-ui/icons/Accessibility';

export default function Convocatoria() {
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

function MyTable() {
    const headCells = [
        {id: 'text', numeric: false, disablePadding: true, label: 'Convocatoria'},
    ];

    const classes = useStylesMyTable();
    const [rows, setRows] = React.useState([])
    const [order, setOrder] = React.useState('asc');
    const [orderBy, setOrderBy] = React.useState('texto');
    const [selected, setSelected] = React.useState([]);
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(9);
    const emptyRows = rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);

    const getData = () => {
        axios
            .get("/convocatoria")
            .then(response => setRows(response.data))
            .catch(error => console.error(error.message))
    }
    React.useEffect(getData, [])

    const deleteUsers = (event) => {
        event.preventDefault()
        const body = new FormData()
        body.append("ids", selected.toString())
        axios
            .delete("/convocatoria", {data: body})
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
        const {numSelected} = props;

        return (
            <Toolbar
                className={clsx(classes.table.rootToolbar, {
                    [classes.highlight]: numSelected > 0,
                })}
            >
                {numSelected > 0 ? (
                    <Typography className={classes.title} color="inherit" variant="subtitle1" component="div">
                        {numSelected} seleccionado(s)
                    </Typography>
                ) : (
                    <Typography className={classes.title} variant="h6" id="tableTitle" component="div">
                        Convocatorias
                    </Typography>
                )}

                {numSelected > 0 ? (
                    <>
                        {
                            (numSelected !== 1) ? null :
                                <>
                                    <AdjuntarBoos update={getData} setSelected={setSelected}
                                                  convocatoria={rows.find(convocatoria => convocatoria.id === selected[0])}/>
                                    <UpdateConvocatoria update={getData} setSelected={setSelected}
                                                        convocatoria={rows.find(convocatoria => convocatoria.id === selected[0])}/>
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
                        <NewConvocatoria update={getData}/>
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
                            padding='default'
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
                                            key={"table-" + row.name + "-" + index}
                                            selected={isItemSelected}
                                        >
                                            <TableCell padding="checkbox">
                                                <Checkbox
                                                    checked={isItemSelected}
                                                    inputProps={{'aria-labelledby': labelId}}
                                                />
                                            </TableCell>
                                            <TableCell component="th" id={labelId} scope="row" padding="default">
                                                {row.text}
                                            </TableCell>
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
    inputTexArea: {
        resize: "vertical",
    },
    containerInputs: {
        marginTop: "10vh",
        width: "100%"
    }
}));

function NewConvocatoria(props) {
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
        const inputs = inputContainer.current.getElementsByTagName("textarea")
        const body = new FormData()
        body.append("text", inputs.text.value)
        axios
            .post("/convocatoria", body)
            .then(() => props.update())
            .catch(error => console.error(error))
        handleClose(even);
    }

    return (
        <div>
            <Tooltip title="Nueva">
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
                                    Nueva Convocatoria
                                </Typography>
                                <Button autoFocus color="inherit" onClick={submit}>
                                    save
                                </Button>
                            </Toolbar>
                        </AppBar>
                        <Grid container className={classes.containerInputs} direction={"column"} spacing={2}
                              alignItems={"center"} alignContent={"center"} ref={inputContainer}>
                            <Grid item><textarea rows="10" cols="60" placeholder="Convocatoria"
                                                 className={classes.inputTexArea} id="text"/></Grid>
                        </Grid>
                    </Dialog>
            }
        </div>
    );
}

function UpdateConvocatoria(props) {
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
        const inputs = inputContainer.current.getElementsByTagName("textarea")
        const body = new FormData()
        body.append("id", props.convocatoria.id)
        body.append("text", inputs.text.value)
        axios
            .put("/convocatoria", body)
            .then(() => props.update())
            .catch(error => console.error(error))
        handleClose(even);
    }

    return (
        <div>
            <Tooltip title="Actualizar Convocatoria">
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
                                    Modificar Convocatoria
                                </Typography>
                                <Button autoFocus color="inherit" onClick={submit}>
                                    save
                                </Button>
                            </Toolbar>
                        </AppBar>
                        <Grid container className={classes.containerInputs} direction={"column"} spacing={2}
                              alignItems={"center"} alignContent={"center"} ref={inputContainer}>
                            <Grid item>
                                <textarea defaultValue={props.convocatoria.text} rows="10" cols="60"
                                          placeholder="Convocatoria" className={classes.inputTexArea} id="text"/>
                            </Grid>
                        </Grid>
                    </Dialog>
            }
        </div>
    );
}

function AdjuntarBoos(props) {
    const classes = useStylesModal();
    const [open, setOpen] = React.useState(false);
    const [users, setUsers] = React.useState([])
    const [selected, setSelected] = React.useState((props.convocatoria.convocatoriaBoss !== null) ? props.convocatoria.convocatoriaBoss.id : -1)

    const getDatos = (e) => {
        axios
            .get("/user")
            .then(response => {
                setUsers(response.data)
            })
            .catch(error => console.error(error))
    }
    React.useEffect(getDatos, [])

    const handleClickOpen = (even) => {
        even.preventDefault()
        setOpen(true);
    };
    const handleClose = (even) => {
        even.preventDefault()
        props.setSelected([])
        setOpen(false);
    };

    const submit = (even) => {
        even.preventDefault()
        let body = new FormData()
        body.append("id", props.convocatoria.id)
        body.append("idUser", selected)
        axios
            .put("/convocatoria/boss", body)
            .then(() => {
                setOpen(false);
                props.setSelected([])
                props.update()
            })
            .catch(error => console.error(error))
    }

    const onClick = (id, even) => {
        even.preventDefault()
        setSelected(id)
    }

    return (
        <div>
            <Tooltip title="Jefe de Convocatoria">
                <IconButton aria-label="filter list" onClick={handleClickOpen}>
                    <AccessibilityIcon/>
                </IconButton>
            </Tooltip>
            {
                (!open) ? null :
                    <Dialog fullScreen open={open} onClose={handleClose}>
                        <AppBar className={classes.appBar}>
                            <Toolbar>
                                <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
                                    <CloseIcon/>
                                </IconButton>
                                <Typography variant="h6" className={classes.title}>
                                    Jefe de Convocatoria
                                </Typography>
                                <Button autoFocus color="inherit" onClick={submit}>
                                    save
                                </Button>
                            </Toolbar>
                        </AppBar>
                        <Grid container direction="column" justify="center" alignItems="center" >
                            <Grid item style={{marginTop:20}} >
                                <Paper style={{width:"60vw"}}>
                                    <List dense>
                                        {users.map((value) => {
                                            return (
                                                <ListItem key={value.id} button onClick={onClick.bind(this, value.id)}
                                                          selected={value.id === selected}>
                                                    <ListItemText primary={value.name}/>
                                                </ListItem>
                                            );
                                        })}
                                    </List>
                                </Paper>
                            </Grid>
                        </Grid>
                    </Dialog>
            }
        </div>
    );
}
