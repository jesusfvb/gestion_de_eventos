import { makeStyles, Typography } from "@material-ui/core";
import {
  DataGrid,
  GridCellParams,
  GridColumns,
  GridRowData,
  GridToolbarContainer,
  GridToolbarFilterButton,
  MuiEvent,
} from "@mui/x-data-grid";
import axios from "axios";
import { MouseEvent, useEffect, useState } from "react";
import { useHistory } from "react-router";
import MyAppBar from "../components/MyAppBar";

const useStyles = makeStyles((theme) => ({
  containerDataGrid: {
    height: "calc(99vh - 64px)",
    width: "100%",
  },
  title: {
    flex: 1,
  },
  cell: {
    cursor: "pointer",
  },
}));

const Convocatorias = () => {
  const classes = useStyles();
  const history = useHistory();
  const [rows, setRows] = useState<Array<GridRowData>>([]);
  const columns: GridColumns = [
    {
      field: "text",
      headerName: "Convocatoria",
      minWidth: 163,
      flex: 1,
      cellClassName: classes.cell,
    },
  ];

  function getData() {
    axios
      .get("/convocatoria")
      .then((response) => setRows(response.data))
      .catch((error) => console.error(error));
  }
  useEffect(getData, []);

  function handleClick(param: GridCellParams, event: MuiEvent<MouseEvent>) {
    event.preventDefault();
    history.push(`/eventos/convocatoria/${param.id}`);
  }

  const MyToolbar = () => {
    return (
      <GridToolbarContainer>
        <Typography className={classes.title} variant="h5">
          Convocatorias
        </Typography>
        <GridToolbarFilterButton />
      </GridToolbarContainer>
    );
  };

  return (
    <MyAppBar>
      <div className={classes.containerDataGrid}>
        <DataGrid
          columns={columns}
          rows={rows}
          autoPageSize
          pagination
          disableSelectionOnClick
          components={{
            Toolbar: MyToolbar,
          }}
          onCellClick={handleClick}
        />
      </div>
    </MyAppBar>
  );
};

export default Convocatorias;
