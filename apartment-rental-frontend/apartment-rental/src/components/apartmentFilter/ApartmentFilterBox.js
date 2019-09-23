import React from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import Search from "@material-ui/icons/Search";

const useStyles = makeStyles(theme => ({
  "@global": {
    body: {
      backgroundColor: theme.palette.common.white
    }
  },
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  form: {
    width: "100%",
    marginTop: theme.spacing(3)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  },
  button: {
    margin: theme.spacing(1)
  }
}));

export default function ApartmentFilterBox(props) {
  const { criteria, onChangeHandler, onSubmitHandler } = props;
  const classes = useStyles();
  return (
    <Container component="main">
      <CssBaseline />
      <div className={classes.paper}>
        <form className={classes.form} onSubmit={onSubmitHandler}>
          <Grid container spacing={1}>
            <Grid item xs={12} sm={2}>
              <TextField
                name="minSize"
                variant="outlined"
                value={criteria.minSize}
                label="Min Size"
                onChange={onChangeHandler}
                type="number"
              />
            </Grid>
            <Grid item xs={12} sm={2}>
              <TextField
                variant="outlined"
                value={criteria.maxSize}
                fullWidth
                label="Max Size"
                name="maxSize"
                onChange={onChangeHandler}
              />
            </Grid>
            <Grid item xs={12} sm={2}>
              <TextField
                variant="outlined"
                value={criteria.minPrice}
                fullWidth
                label="Min Price"
                name="minPrice"
                onChange={onChangeHandler}
              />
            </Grid>
            <Grid item xs={12} sm={2}>
              <TextField
                variant="outlined"
                value={criteria.maxPrice}
                fullWidth
                label="Max Price"
                name="maxPrice"
                onChange={props.onChangeHandler}
              />
            </Grid>
            <Grid item xs={12} sm={2}>
              <TextField
                variant="outlined"
                value={criteria.numberOfRooms}
                fullWidth
                label="Number of rooms"
                name="numberOfRooms"
                onChange={props.onChangeHandler}
              />
            </Grid>
            <Grid item xs={12} sm={2}>
              <Button
                type="submit"
                variant="contained"
                color="primary"
                className={classes.button}
              >
                Search
                <Search />
              </Button>
            </Grid>
          </Grid>
        </form>
      </div>
    </Container>
  );
}
