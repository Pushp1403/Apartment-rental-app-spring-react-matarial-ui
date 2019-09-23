import React from "react";
import Grid from "@material-ui/core/Grid";
import { Link } from "react-router-dom";

export default function SignInSignUpLinks() {
  return (
    <Grid container>
      <Grid item xs></Grid>
      <Grid item>
        <Link variant="body2" to="/signup">
          {"Don't have an account? Sign Up"}
        </Link>
      </Grid>
    </Grid>
  );
}
