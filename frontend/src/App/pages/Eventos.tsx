import {makeStyles, Typography} from "@material-ui/core";
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
import {MouseEvent, useEffect, useState} from "react";
import {useParams} from "react-router";
import MyAppBar from "../components/MyAppBar";

const useStyles = makeStyles(() => ({
    containerDataGrid: {
        height: "calc(99vh - 64px)",
        width: "100%",
    },
    title: {
        flex: 1,
    },
}));

type TypeParams = { id: string };

const Eventos = () => {
    const params = useParams<TypeParams>();
    const classes = useStyles();
    const [rows, setRows] = useState<Array<GridRowData>>([]);
    const columns: GridColumns = [
        {
            field: "name",
            headerName: "Nombre",
            minWidth: 163,
            flex: 1,
        },
    ];

    function getData() {
        axios
            .get(`/event?idConvocatoria=${Number.parseInt(params.id)}&state=APROBADO`)
            .then((response) => setRows(response.data))
            .catch((error) => console.error(error));
    }

    useEffect(getData, [params.id]);

    function handleClick(param: GridCellParams, event: MuiEvent<MouseEvent>) {
        event.preventDefault();
    }

    const MyToolbar = () => {
        return (
            <GridToolbarContainer>
                <Typography className={classes.title} variant="h5">
                    Eventos
                </Typography>
                <GridToolbarFilterButton/>
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

export default Eventos;
