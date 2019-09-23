import React, { useState } from "react";
import SigninContainer from "../components/SigninContainer";
import { toast } from "react-toastify";
import { connect } from "react-redux";
import * as userAction from "./../redux/actions/userAction";
import * as apartmentActions from "./../redux/actions/apartmentActions";

function SignIn(props) {
  const [user, setUser] = useState({
    username: "",
    password: ""
  });

  const handleChange = event => {
    setUser({ ...user, [event.target.name]: event.target.value });
  };

  const handleSubmit = event => {
    event.preventDefault();
    props
      .authenticateuser(user)
      .then(res => {
        props.history.push("/Landing");
      })
      .catch(error => {
        toast("Invalid Credential !");
      });
  };

  return (
    <SigninContainer
      user={user}
      changeHandler={handleChange}
      submitHandler={handleSubmit}
    />
  );
}

function mapStateToProps(state) {
  return {
    user: state.user
  };
}

const mapDispatchToProps = {
  authenticateuser: userAction.authenticateuser,
  retrieveAllApartments: apartmentActions.retrieveAllApartments
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignIn);
