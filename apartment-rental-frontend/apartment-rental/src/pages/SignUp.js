import React, { useState } from "react";
import SignUpContainer from "./../components/SignUpContainer";
import { toast } from "react-toastify";
import * as userActions from "./../redux/actions/userAction";
import { connect } from "react-redux";

function SignUp(props) {
  const [user, setUser] = useState({
    username: "",
    secretKey: "",
    firstName: "",
    lastName: "",
    authorities: [{ role: "ROLE_CLIENT" }]
  });

  const handleChange = event => {
    setUser({ ...user, [event.target.name]: event.target.value });
  };

  const handleSubmit = event => {
    event.preventDefault();
    if (user.secretKey !== user.confirmSecretKey) {
      toast("Password Doesn't match");
      return;
    }
    props
      .registerUser(user)
      .then(res => {
        toast("Registration Successfull. Please Continue to Login.");
        props.history.push("/");
      })
      .catch(error => {
        toast("Registration Failed");
      });
  };

  return (
    <SignUpContainer
      user={user}
      submitHandler={handleSubmit}
      onChangeHandler={handleChange}
    />
  );
}

function mapStateToProps(state) {
  return {
    user: state.user
  };
}

const mapDispatchToProps = {
  registerUser: userActions.registerNewUser
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignUp);
