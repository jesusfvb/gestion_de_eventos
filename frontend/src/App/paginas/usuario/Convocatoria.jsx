import React, { useEffect } from "react";
import FilterListIcon from "@material-ui/icons/FilterList";
import BarraDeNavegacion from "../../componentes/BarraDeNavegacion";
import Grid from "@material-ui/core/Grid";
import { makeStyles } from "@material-ui/core/styles";
import {
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
} from "@material-ui/core";
import { Sesion } from "../../App";
import ArrowForwardIcon from '@material-ui/icons/ArrowForward';

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
    minWidth: 200,
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
  titleToolbar: {
    flex: "1 1 100%",
  },
  tableCell: {
    padding: "14px",
    paddingTop: "10px",
    paddingBottom: "10px",
  },
}));

function Tabla() {
  const classes = useStylesTabla();
  const sesion = React.useContext(Sesion);
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("calories");
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

  const getDatos = () => {
    axios
      .get(sesion.server + "/convocatoria")
      .then((respuesta) => {
        setRows(respuesta.data);

      })
      .catch((error) => {
        console.error(error);
      });
  };
  useEffect(getDatos, []);

  const handleClick = (event, name) => {
    console.log("clic")
  };

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
        <Toolbar
          className={classes.rootToolbar}
        >
          <Typography
            className={classes.titleToolbar}
            variant="h6"
            id="tableTitle"
            component="div"
          >
            Convocatorias
          </Typography>
          <Tooltip title="Filtrar">
            <IconButton aria-label="filter list">
              <FilterListIcon />
            </IconButton>
          </Tooltip>
        </Toolbar>
        <TableContainer>
          <Table
            className={classes.table}
            aria-labelledby="tableTitle"
            size="medium"
            aria-label="enhanced table"
          >
            <TableHead>
              <TableRow >
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
                    <TableRow
                      hover
                      onClick={(event) => handleClick(event, row.id)}
                      role="checkbox"
                      tabIndex={-1}
                      key={row.id}
                      style={{ cursor: "pointer" }}
                    >
                      <TableCell padding="default" align="left">
                        <Grid container
                          direction="row"
                          justify="space-between"
                          alignItems="center"
                        >
                          <Typography>
                            {row.convocatoria}
                          </Typography>
                          <ArrowForwardIcon />
                        </Grid>
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
    </div >
  );
}
