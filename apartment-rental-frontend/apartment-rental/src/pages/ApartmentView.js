import React from "react";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import ApartmentList from "./../components/apartment/ApartmentList";
import ApartmentFilter from "../components/apartmentFilter/ApartmentFilter";
import ApartmentMap from "./../components/apartment/ApartmentMap";

function ApartmentView(props) {
  const { apartments } = props;

  return (
    <>
      <div>
        <Paper style={{ paddingBottom: 20, paddingLeft: 20 }}>
          <ApartmentFilter apartments={apartments}></ApartmentFilter>
        </Paper>
      </div>

      <div style={{ marginTop: 8 }}>
        <Grid container spacing={1}>
          <Grid item xs={3}>
            <ApartmentList apartments={apartments}></ApartmentList>
          </Grid>
          <Grid item xs={9}>
            <ApartmentMap {...props}></ApartmentMap>
          </Grid>
        </Grid>
      </div>
    </>
  );
}

export default ApartmentView;
