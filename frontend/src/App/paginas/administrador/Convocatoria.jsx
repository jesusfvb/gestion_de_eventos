import React, { useEffect, useState } from "react";
import DeleteIcon from "@material-ui/icons/Delete";
import FilterListIcon from "@material-ui/icons/FilterList";
import AddOutlinedIcon from "@material-ui/icons/AddOutlined";
import UpdateIcon from "@material-ui/icons/Update";
import clsx from "clsx";
import BarraDeNavegacion from "../../componentes/BarraDeNavegacion";
import Grid from "@material-ui/core/Grid";
import { lighten, makeStyles } from "@material-ui/core/styles";
import {
  Checkbox,
  IconButton,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
  TableSortLabel,
  Toolbar,
  Tooltip,
  Typography,
  withStyles,
  Menu,
  MenuItem,
} from "@material-ui/core";
import { Sesion } from "../../App";
import { useRef } from "react";
import SendIcon from "@material-ui/icons/Send";

const axios = require("axios").default;

export default function Convocatoria() {
  return (
    <Grid container alignItems="center" justify="center">
      <Grid container item>
        <BarraDeNavegacion />
      </Grid>
      <Grid container item>
        <Tabla />
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
  tableCell: {
    padding: "14px",
    paddingTop: "10px",
    paddingBottom: "10px",
  },
  avatar: {
    "&:hover": {
      transform: "scale(3)",
      zIndex: 1,
    },
  },
}));

function Tabla() {
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
      id: "convocatoria",
      numeric: false,
      disablePadding: false,
      label: "Convocatoria",
    },
  ];
  const emptyRows =
    rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);

  const getDatos = (actualizar = true) => {
    if (!actualizar) {
      setSelected([]);
    } else {
      axios
        .get(sesion.server + "/convocatoria")
        .then((respuesta) => {
          setRows(respuesta.data);
          setSelected([]);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };
  useEffect(getDatos, []);

  const handleClickDelete = () => {
    axios
      .delete(sesion.server + "/convocatoria/" + selected)
      .then(() => {
        getDatos();
        setSelected([]);
      })
      .catch((error) => {
        console.error(error);
      });
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
              Convocatoria
            </Typography>
          )}

          {selected.length > 0 ? (
            <>
              {selected.length === 1 ? (
                <>
                  <Solicitud actualizarDatos={getDatos} update="true" dato={rows.find(r => r.id === selected[0])} />
                </>
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
              <Solicitud actualizarDatos={getDatos} />
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
                    className={
                      headCell.disablePadding ? null : classes.tableCell
                    }
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
                      <TableCell padding="default" align="left">
                        {row.convocatoria}
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

function Solicitud({ actualizarDatos, update = false, dato = null }) {
  const menuItems = useRef();
  const sesion = React.useContext(Sesion);
  const [anchorEl, setAnchorEl] = useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleClickEnviar = () => {
    let inputs = menuItems.current.getElementsByTagName("textarea");
    let body = new FormData();
    if (update) {
      if (inputs.convocatoria.value !== dato.convocatoria) {
        body.append("id", dato.id);
        body.append("convocatoria", inputs.convocatoria.value);
        axios
          .put(sesion.server + "/convocatoria", body)
          .then((response) => {
            if (response.data) {
              setAnchorEl(null);
            }
          })
          .then(actualizarDatos)
          .catch((error) => {
            console.error(error);
          });
      } else {
        setAnchorEl(null);
        actualizarDatos(false);
      }
    } else {
      body.append("convocatoria", inputs.convocatoria.value);
      axios
        .post(sesion.server + "/convocatoria", body)
        .then((response) => {
          if (response.data) {
            setAnchorEl(null);
          }
        })
        .then(actualizarDatos)
        .catch((error) => {
          console.error(error);
        });
    }
  };

  const StyledMenu = withStyles({
    paper: {
      border: "1px solid #d3d4d5",
    },
  })((props) => (
    <Menu
      elevation={0}
      getContentAnchorEl={null}
      anchorOrigin={{
        vertical: "bottom",
        horizontal: "center",
      }}
      transformOrigin={{
        vertical: "top",
        horizontal: "center",
      }}
      {...props}
    />
  ));
  return (
    <div>
      {(update) ?
        <Tooltip title="Modificar">
          <IconButton
            aria-label="modificar"
            onClick={handleClick}
          >
            <UpdateIcon />
          </IconButton>
        </Tooltip>
        :
        <Tooltip title="Añadir">
          <IconButton aria-label="filter list" onClick={handleClick}>
            <AddOutlinedIcon />
          </IconButton>
        </Tooltip>}
      <StyledMenu
        id="customized-menu"
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <div ref={menuItems}>
          <MenuItem>
            <Grid
              container
              direction="row"
              justify="space-between"
              alignItems="center"
            >
              <Typography variant="h6" component="div">
                {(update) ? "Modificar Convocatoria" : "Neva Convocatoria"}
              </Typography>
              <Tooltip title="Enviar">
                <IconButton aria-label="Enviar" onClick={handleClickEnviar}>
                  <SendIcon />
                </IconButton>
              </Tooltip>
            </Grid>
          </MenuItem>
          <MenuItem>
            <textarea
              cols="50"
              rows="5"
              id="convocatoria"
              style={{ resize: "vertical" }}
              defaultValue={(update) ? dato.convocatoria : ""}
            />
          </MenuItem>
        </div>
      </StyledMenu>
    </div>
  );
}
