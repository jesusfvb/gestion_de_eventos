import { makeStyles, withStyles } from "@material-ui/core";
import {
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
  Typography,
  Grid,
  Tooltip,
  IconButton,
  Menu,
  MenuItem,
  TextField,
} from "@material-ui/core";
import axios from "axios";
import React, { useEffect, useRef } from "react";
import { Sesion } from "../../App";
import BarraDeNavegacion from "../../componentes/BarraDeNavegacion";
import PlaylistAddIcon from "@material-ui/icons/PlaylistAdd";
import { useState } from "react";
import SendIcon from "@material-ui/icons/Send";

export default function Evento() {
  const handleClickOpen = (e) => {};

  return (
    <Grid container alignItems="center" justify="center">
      <Grid container item>
        <BarraDeNavegacion />
      </Grid>
      <Grid container item>
        <Tabla handleClickOpen={handleClickOpen} setUpdate={handleClickOpen} />
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
  tableCell: {
    padding: "16px",
    paddingTop: "10px",
    paddingBottom: "10px",
  },
  titleToolbar: {
    flex: "1 1 100%",
  },
}));

function Tabla(props) {
  const classes = useStylesTabla();
  const sesion = React.useContext(Sesion);
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("calories");
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(9);
  const [rows, setRows] = React.useState([]);
  const headCells = [
    {
      id: "nombre",
      numeric: false,
      disablePadding: false,
      label: "Nombre",
    },
    {
      id: "convocatoria",
      numeric: false,
      disablePadding: false,
      label: "Convocatoria",
    },
    { id: "inicio", numeric: false, disablePadding: false, label: "Inicio" },
    {
      id: "fin",
      numeric: false,
      disablePadding: false,
      label: "Fin",
    },
    {
      id: "edicion",
      numeric: false,
      disablePadding: false,
      label: "Edición",
    },
  ];
  const emptyRows =
    rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);

  const getDatos = () => {
    axios
      .get(sesion.server + "/evento")
      .then((respuesta) => {
        setRows(respuesta.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };
  useEffect(getDatos, []);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

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
        <Toolbar>
          <Typography
            className={classes.titleToolbar}
            variant="h6"
            id="tableTitle"
            component="div"
          >
            Eventos
          </Typography>
          <SolicitarEvento />
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
                  return (
                    <TableRow hover tabIndex={-1} key={row.id}>
                      <TableCell padding="default" align="left">
                        {row.nombre}
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.convocatoria}
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.inicio}
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.fin}
                      </TableCell>
                      <TableCell padding="default" align="left">
                        {row.edicion}
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

function SolicitarEvento() {
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
    let inputs = menuItems.current.getElementsByTagName("input");
    let body = new FormData();
    body.append("nombreEvento", inputs.nombre.value);
    body.append("clasificacion", inputs.clasificacion.value);
    body.append("area", inputs.area.value);
    axios
      .post(sesion.server + "/evento/solicitud", body)
      .then((response) => {
        if (response.data) {
          setAnchorEl(null);
        }
      })
      .catch((error) => {
        console.error(error);
      });
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
      <Tooltip title="Solicitar">
        <IconButton aria-label="Solicitar" onClick={handleClick}>
          <PlaylistAddIcon />
        </IconButton>
      </Tooltip>
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
                Solicitar Evento
              </Typography>
              <Tooltip title="Enviar">
                <IconButton aria-label="Enviar" onClick={handleClickEnviar}>
                  <SendIcon />
                </IconButton>
              </Tooltip>
            </Grid>
          </MenuItem>
          <MenuItem>
            <TextField
              id="nombre"
              label="Nombre"
              type="text"
              variant="outlined"
            />
          </MenuItem>
          <MenuItem>
            <TextField
              id="clasificacion"
              label="Clasificación"
              type="text"
              variant="outlined"
            />
          </MenuItem>
          <MenuItem>
            <TextField id="area" label="Area" type="text" variant="outlined" />
          </MenuItem>
        </div>
      </StyledMenu>
    </div>
  );
}
