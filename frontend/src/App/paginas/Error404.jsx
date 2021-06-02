import { Grid, makeStyles, Typography } from "@material-ui/core";
import React from "react";

const useStyles = makeStyles((theme) => ({
  root: {
    marginTop: "30vh",
  },
}));

export default function Error404() {
  const classes = useStyles();

  return (
    <Grid className={classes.root} container justify="center">
      <Typography variant="h1">Error 404</Typography>
    </Grid>
  );
}
