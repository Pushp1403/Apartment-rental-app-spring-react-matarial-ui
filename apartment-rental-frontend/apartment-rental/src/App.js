import React from "react";
import { Route, Switch } from "react-router-dom";
import SignIn from "./pages/SignIn";
import NotFound from "./pages/NotFound";
import Landing from "./pages/Apartment";
import SignUp from "./pages/SignUp";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import PrivateRoute from "./components/PrivateRoute";
import { connect } from "react-redux";
import Users from "./pages/Users";

function App(props) {
  return (
    <>
      <Switch>
        <Route path="/" exact component={SignIn} />
        <PrivateRoute path="/landing" exact component={Landing} />
        <PrivateRoute path="/users" exact component={Users} />
        <Route path="/signup" component={SignUp} />
        <Route component={NotFound} />
      </Switch>
      <ToastContainer />
    </>
  );
}
function mapStateToProps(state) {
  return {
    user: state.user,
    apartments: state.apartments
  };
}
export default connect(mapStateToProps)(App);
