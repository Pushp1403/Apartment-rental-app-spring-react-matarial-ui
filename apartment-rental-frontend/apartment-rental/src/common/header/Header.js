import React from "react";
import { Link as RouterLink } from "react-router-dom";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/styles";
import { AppBar, Toolbar, IconButton } from "@material-ui/core";
import InputIcon from "@material-ui/icons/Input";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import authService from "./../../api/authenticationService";

const useStyles = makeStyles(theme => ({
  root: {
    boxShadow: "none"
  },
  flexGrow: {
    flexGrow: 1
  },
  logo: {
    height: 36
  },
  signOutButton: {
    marginLeft: 1
  }
}));

const Topbar = props => {
  const { user } = props;
  const role = user.authorities[0].role;

  const classes = useStyles();

  const handleLogout = () => {
    authService.logout();
    props.history.push("/");
  };

  function ButtonGroup({ role }) {
    if (role === "ROLE_ADMIN") {
      return (
        <>
          {" "}
          <Button color="default" variant="contained">
            <RouterLink to={"/landing"}>Apartment</RouterLink>
          </Button>
          <div style={{ width: 4 }} />{" "}
          <Button color="default" variant="contained">
            <RouterLink to={"/users"}>Users</RouterLink>
          </Button>
        </>
      );
    } else {
      return null;
    }
  }

  return (
    <AppBar position="fixed">
      <Toolbar>
        <RouterLink to="/home">
          <img
            alt="Logo"
            src="/images/logos/xyz.png"
            className={classes.logo}
          />
        </RouterLink>
        <Typography variant="h6" color="inherit">
          Apartment Rental
        </Typography>
        <div className={classes.flexGrow} />{" "}
        <ButtonGroup role={role}></ButtonGroup>
        <IconButton
          className={classes.signOutButton}
          color="inherit"
          onClick={handleLogout}
        >
          <InputIcon />
        </IconButton>
      </Toolbar>
    </AppBar>
  );
};

Topbar.propTypes = {
  className: PropTypes.string,
  onSidebarOpen: PropTypes.func
};

export default Topbar;
