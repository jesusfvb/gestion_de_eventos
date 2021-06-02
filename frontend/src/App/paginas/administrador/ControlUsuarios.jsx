import React, { useContext, useEffect, useState } from "react";
import DeleteIcon from "@material-ui/icons/Delete";
import FilterListIcon from "@material-ui/icons/FilterList";
import AddOutlinedIcon from "@material-ui/icons/AddOutlined";
import CloseIcon from "@material-ui/icons/Close";
import UpdateIcon from "@material-ui/icons/Update";
import SaveIcon from "@material-ui/icons/Save";
import clsx from "clsx";
import BarraDeNavegacion from "../../componentes/BarraDeNavegacion";
import Grid from "@material-ui/core/Grid";
import { lighten, makeStyles } from "@material-ui/core/styles";
import {
  AppBar,
  Avatar,
  Button,
  Checkbox,
  Dialog,
  IconButton,
  Paper,
  Slide,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
  TableSortLabel,
  TextField,
  Toolbar,
  Tooltip,
  Typography,
} from "@material-ui/core";
import { Sesion } from "../../App";
import { useRef } from "react";

const axios = require("axios").default;

export default function ControlUsuario() {
  const [open, setOpen] = React.useState(false);
  const [update, setUpdate] = useState(null);

  const handleClickOpen = (e) => {
    e.preventDefault();
    setOpen(true);
  };

  const handleClose = () => {
    setUpdate(null);
    setOpen(false);
  };

  return (
    <Grid container alignItems="center" justify="center">
      <Grid container item>
        <BarraDeNavegacion />
      </Grid>
      <Grid container item>
        {open ? (
          <Formulario open={open} handleClose={handleClose} update={update} />
        ) : (
          <Tabla handleClickOpen={handleClickOpen} setUpdate={setUpdate} />
        )}
      </Grid>
    </Grid>
  );
}

const useStylesTabla = makeStyles((theme) => ({
  root: {
    width: "98%",
    padding: "1%",
    paddingBottom: "0px",
  },
  paper: {
    width: "100%",
  },
  table: {
    minWidth: 750,
  },
  visuallyHidden: {
    border: 0,
    clip: "rect(0 0 0 0)",
    height: 1,
    margin: -1,
    overflow: "hidden",
    padding: 0,
    position: "absolute",
    top: 20,
    width: 1,
  },
  rootToolbar: {
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(1),
  },
  highlightToolbar:
    theme.palette.type === "light"
      ? {
          color: theme.palette.secondary.main,
          backgroundColor: lighten(theme.palette.secondary.light, 0.85),
        }
      : {
          color: theme.palette.text.primary,
          backgroundColor: theme.palette.secondary.dark,
        },
  titleToolbar: {
    flex: "1 1 100%",
  },
  avatar: {
    "&:hover": {
      transform: "scale(3)",
      zIndex: 1,
    },
  },
}));

function Tabla(props) {
  const classes = useStylesTabla();
  const sesion = React.useContext(Sesion);
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("calories");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(9);
  const [rows, setRows] = React.useState([]);
  const headCells = [
    {
      id: "",
      numeric: false,
      disablePadding: true,
      label: "Foto",
    },
    {
      id: "nombre",
      numeric: false,
      disablePadding: true,
      label: "Nombre(s)",
    },
    {
      id: "apellido",
      numeric: false,
      disablePadding: true,
      label: "Apellido(s)",
    },
    { id: "usuario", numeric: false, disablePadding: true, label: "Usuario" },
    {
      id: "carnetIdentidad",
      numeric: false,
      disablePadding: true,
      label: "C.I.",
    },
    { id: "roles", numeric: false, disablePadding: true, label: "Rol(es)" },
  ];
  const emptyRows =
    rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);

  const getDatos = () => {
    axios
      .get(sesion.server + "/usuario")
      .then((respuesta) => {
        console.log(respuesta.data);
        setRows(respuesta.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };
  useEffect(getDatos, []);

  const handleClickDelete = () => {
    axios
      .delete(sesion.server + "/usuario/" + selected)
      .then(() => {
        getDatos();
        setSelected([]);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const handleClickUpdate = (e) => {
    e.preventDefault();
    props.setUpdate(rows.find((row) => row.id === selected[0]));
    props.handleClickOpen(e);
  };

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelecteds = rows.map((n) => n.id);
      setSelected(newSelecteds);
      return;
    }
    setSelected([]);
  };

  const handleClick = (event, name) => {
    const selectedIndex = selected.indexOf(name);
    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, name);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1)
      );
    }

    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const isSelected = (name) => selected.indexOf(name) !== -1;

  const createSortHandler = (property) => (event) => {
    handleRequestSort(event, property);
  };

  function getComparator(order, orderBy) {
    function descendingComparator(a, b, orderBy) {
      if (b[orderBy] < a[orderBy]) {
        return -1;
      }
      if (b[orderBy] > a[orderBy]) {
        return 1;
      }
      return 0;
    }

    return order === "desc"
      ? (a, b) => descendingComparator(a, b, orderBy)
      : (a, b) => -descendingComparator(a, b, orderBy);
  }

  function stableSort(array, comparator) {
    const stabilizedThis = array.map((el, index) => [el, index]);
    stabilizedThis.sort((a, b) => {
      const order = comparator(a[0], b[0]);
      if (order !== 0) return order;
      return a[1] - b[1];
    });
    return stabilizedThis.map((el) => el[0]);
  }

  return (
    <div className={classes.root}>
      <Paper className={classes.paper}>
        <Toolbar
          className={clsx(classes.rootToolbar, {
            [classes.highlightToolbar]: selected.length > 0,
          })}
        >
          {selected.length > 0 ? (
            <Typography
              className={classes.titleToolbar}
              color="inherit"
              variant="subtitle1"
              component="div"
            >
              {selected.length} seleccionado(s)
            </Typography>
          ) : (
            <Typography
              className={classes.titleToolbar}
              variant="h6"
              id="tableTitle"
              component="div"
            >
              Usuarios
            </Typography>
          )}

          {selected.length > 0 ? (
            <>
              {selected.length === 1 ? (
                <Tooltip title="Modificar">
                  <IconButton aria-label="delete" onClick={handleClickUpdate}>
                    <UpdateIcon />
                  </IconButton>
                </Tooltip>
              ) : null}
              <Tooltip title="Borrar">
                <IconButton aria-label="delete" onClick={handleClickDelete}>
                  <DeleteIcon />
                </IconButton>
              </Tooltip>
            </>
          ) : (
            <>
              <Tooltip title="Filtrar">
                <IconButton aria-label="filter list">
                  <FilterListIcon />
                </IconButton>
              </Tooltip>
              <Tooltip title="Añadir">
                <IconButton
                  aria-label="filter list"
                  onClick={props.handleClickOpen}
                >
                  <AddOutlinedIcon />
                </IconButton>
              </Tooltip>
            </>
          )}
        </Toolbar>
        <TableContainer>
          <Table
            className={classes.table}
            aria-labelledby="tableTitle"
            size="medium"
            aria-label="enhanced table"
          >
            <TableHead>
              <TableRow>
                <TableCell padding="checkbox">
                  <Checkbox
                    indeterminate={
                      selected.length > 0 && selected.length < rows.length
                    }
                    checked={rows.length > 0 && selected.length === rows.length}
                    onChange={handleSelectAllClick}
                    inputProps={{ "aria-label": "select all desserts" }}
                  />
                </TableCell>
                {headCells.map((headCell, index) => (
                  <TableCell
                    key={headCell.id}
                    align={headCell.numeric ? "right" : "left"}
                    padding={headCell.disablePadding ? "none" : "default"}
                    sortDirection={orderBy === headCell.id ? order : false}
                  >
                    <TableSortLabel
                      active={orderBy === headCell.id}
                      direction={orderBy === headCell.id ? order : "asc"}
                      onClick={createSortHandler(headCell.id)}
                    >
                      {headCell.label}
                      {orderBy === headCell.id ? (
                        <span className={classes.visuallyHidden}>
                          {order === "desc"
                            ? "sorted descending"
                            : "sorted ascending"}
                        </span>
                      ) : null}
                    </TableSortLabel>
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
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
                      key={row.id}
                      selected={isItemSelected}
                    >
                      <TableCell padding="checkbox">
                        <Checkbox
                          checked={isItemSelected}
                          inputProps={{ "aria-labelledby": labelId }}
                        />
                      </TableCell>
                      <TableCell
                        component="th"
                        id={labelId}
                        scope="row"
                        style={{ width: "1px" }}
                        padding="none"
                      >
                        <Avatar
                          src={row.foto !== null ? row.foto.url : null}
                          sizes="0.3"
                          className={classes.avatar}
                        />
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.nombre}
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.apellido}
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.usuario}
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.carnetIdentidad}
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.roles.map((rol, key) => {
                          if (key === 0) {
                            return rol[0];
                          }
                          return " ," + rol[0];
                        })}
                      </TableCell>
                    </TableRow>
                  );
                })}
              {emptyRows > 0 && (
                <TableRow style={{ height: 53 * emptyRows }}>
                  <TableCell colSpan={6} />
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[5, 7, 9, 15, 25, 35, 50, 100]}
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

const useStylesFormulario = makeStyles((theme) => ({
  appBar: {
    position: "relative",
  },
  title: {
    marginLeft: theme.spacing(2),
    flex: 1,
  },
  foto: {
    width: theme.spacing(25),
    height: theme.spacing(25),
    marginBottom: theme.spacing(1),
    cursor: "pointer",
  },
  formulario: {
    marginTop: theme.spacing(10),
  },
  inputs: {
    marginBottom: theme.spacing(2),
    width: `calc(${theme.spacing(40)}px + 9vw)`,
  },
}));

function Formulario(props) {
  const classes = useStylesFormulario();
  const open = props.open;
  const handleClose = props.handleClose;
  const sesion = useContext(Sesion);
  const avatar = useRef();

  const mostrarFoto = (e) => {
    if (e.target.files.length !== 0) {
      const lector = new FileReader();
      lector.readAsDataURL(e.target.files[0]);
      lector.onload = (e) => {
        avatar.current.innerHTML = `<img src="${e.target.result}" style="width:350px"/>`;
      };
    }
  };

  const handleClickSave = (e) => {
    e.preventDefault();
    const inputs = document
      .getElementById("formulario")
      .getElementsByTagName("input");

    const data = new FormData();
    data.append(
      "usuario",
      JSON.stringify({
        nombre: inputs.nombre.value,
        apellido: inputs.apellido.value,
        usuario: inputs.usuario.value,
        carnetIdentidad: Number.parseInt(inputs.ci.value),
      })
    );

    if (inputs.foto.files.length !== 0) {
      data.append("foto", inputs.foto.files[0], "");
    }
    if (props.update !== null) {
      data.append("id", props.update.id);
      axios
        .put(sesion.server + "/usuario", data)
        .then((respuesta) => {
          handleClose();
        })
        .catch((error) => {
          console.error(error);
        });
    } else {
      axios
        .post(sesion.server + "/usuario", data)
        .then((respuesta) => {
          handleClose();
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };

  const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
  });

  return (
    <div>
      <Dialog
        fullScreen
        open={open}
        onClose={handleClose}
        TransitionComponent={Transition}
      >
        <AppBar className={classes.appBar} color="secondary">
          <Toolbar>
            <IconButton
              edge="start"
              color="inherit"
              onClick={handleClose}
              aria-label="close"
            >
              <CloseIcon />
            </IconButton>
            <Typography variant="h6" className={classes.title}>
              Usuario
            </Typography>
            <Button autoFocus color="inherit" onClick={handleClickSave}>
              <SaveIcon />
            </Button>
          </Toolbar>
        </AppBar>
        <form className={classes.formulario} id="formulario">
          <Grid container alignItems="center" direction="column">
            <Grid item>
              <label htmlFor="foto">
                <Avatar
                  className={classes.foto}
                  ref={avatar}
                  src={props.update !== null ? props.update.foto.url : null}
                />
              </label>
              <input
                type="file"
                id="foto"
                style={{ display: "none" }}
                accept="image/*"
                multiple={false}
                onChange={mostrarFoto}
              />
            </Grid>
            <Grid item>
              <TextField
                className={classes.inputs}
                label="Nombre(s)"
                id="nombre"
                type="text"
                defaultValue={
                  props.update !== null ? props.update.nombre : null
                }
              />
            </Grid>
            <Grid item>
              <TextField
                className={classes.inputs}
                label="Apellido(s)"
                id="apellido"
                type="text"
                defaultValue={
                  props.update !== null ? props.update.apellido : null
                }
              />
            </Grid>
            <Grid item>
              <TextField
                className={classes.inputs}
                label="Usuario"
                id="usuario"
                type="text"
                defaultValue={
                  props.update !== null ? props.update.usuario : null
                }
              />
            </Grid>
            <Grid item>
              <TextField
                className={classes.inputs}
                label="C.I."
                id="ci"
                type="number"
                defaultValue={
                  props.update !== null ? props.update.carnetIdentidad : 0
                }
              />
            </Grid>
          </Grid>
        </form>
      </Dialog>
    </div>
  );
}
