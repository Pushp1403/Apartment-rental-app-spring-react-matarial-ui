import React from "react";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";

const useStyles = makeStyles(theme => ({
  card: {
    height: "100%",
    display: "flex",
    flexDirection: "column"
  },
  cardMedia: {
    paddingTop: "56.25%" // 16:9
  },
  cardContent: {
    flexGrow: 1
  }
}));

function UserCard(props) {
  const classes = useStyles();
  const {
    user,
    onChangeHandler,
    onSubmitHandler,
    deleteUser,
    roleChangeHandler
  } = props;
  const userRoles = [
    {
      roleName: "Client",
      roleValue: "ROLE_CLIENT"
    },
    {
      roleName: "Realtor",
      roleValue: "ROLE_REALTOR"
    },
    {
      roleName: "Admin",
      roleValue: "ROLE_ADMIN"
    }
  ];
  return (
    <Container component="main" maxWidth="md">
      <CssBaseline />
      <div className={classes.paper}>
        <form className={classes.form} onSubmit={onSubmitHandler}>
          <Grid container spacing={2} xl>
            <Grid item xs={12} sm={6}>
              <TextField
                name="firstName"
                variant="outlined"
                value={user.firstName}
                required
                fullWidth
                label="First Name"
                onChange={onChangeHandler}
                autoFocus
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                value={user.lastName}
                required
                fullWidth
                label="Last Name"
                name="lastName"
                onChange={onChangeHandler}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="User name"
                name="username"
                value={user.username}
                onChange={onChangeHandler}
                disabled={!user.newUser}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="Password"
                name="secretKey"
                value={user.secretKey}
                onChange={onChangeHandler}
                disabled={!user.newUser}
                type="password"
              />
            </Grid>
            <Grid item xs={12} sm={12}>
              <FormControl variant="outlined" style={{ width: "100%" }}>
                <InputLabel htmlFor="rental-status">User Type</InputLabel>
                <Select
                  native
                  value={user.authorities[0].role}
                  onChange={roleChangeHandler}
                  inputProps={{
                    name: "User Tupe",
                    id: "user-type"
                  }}
                >
                  {userRoles.map(role => {
                    return (
                      <option
                        value={role.roleValue}
                        selected={user.authorities[0].role === role.roleValue}
                      >
                        {role.roleName}
                      </option>
                    );
                  })}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} sm={8}>
              <div style={{ flexGrow: 4 }}></div>
            </Grid>
            <Grid item xs={12} sm={2}>
              <Button
                variant="contained"
                color="secondary"
                onClick={deleteUser}
              >
                Delete User
              </Button>
            </Grid>
            <Grid item xs={12} sm={2}>
              <Button
                type="submit"
                variant="contained"
                color="primary"
                style={{ align: "flex-end" }}
              >
                Save User
              </Button>
            </Grid>
          </Grid>
        </form>
      </div>
    </Container>
  );
}

export default UserCard;
