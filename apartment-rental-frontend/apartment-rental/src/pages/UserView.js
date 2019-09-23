import React, { useState } from "react";
import UserGrid from "../components/user/UserGrid";
import Grid from "@material-ui/core/Grid";
import { Paper } from "@material-ui/core";
import UserCard from "../components/user/UserCard";
import { toast } from "react-toastify";
import * as userActions from "./../redux/actions/userAction";
import { connect } from "react-redux";

function UserView(props) {
  const { users, updateUser, registerNewUser } = props;
  const newUser = {
    username: "",
    firstName: "",
    lastName: "",
    secretKey: "",
    newUser: true,
    authorities: [{ role: "ROLE_CLIENT" }]
  };
  const [user, setUser] = useState({ ...newUser });

  const handleChange = event => {
    setUser({ ...user, [event.target.name]: event.target.value });
  };

  const roleChangeHandler = event => {
    setUser({ ...user, authorities: [{ role: event.target.value }] });
  };

  const handleSubmit = event => {
    event.preventDefault();
    if (user.newUser) {
      delete user.newUser;
      registerNewUser(user)
        .then(() => {
          toast("User Created");
        })
        .catch(error => {
          toast(error.message);
        });
    } else {
      delete user.newUser;
      updateUser(user)
        .then(() => {
          toast("user details Saved");
        })
        .catch(error => {
          toast(error.message);
        });
    }
  };

  const onEditClick = user => {
    user.newUser = false;
    setUser({ ...user });
  };

  const resetForm = event => {
    setUser({
      username: "",
      firstName: "",
      lastName: "",
      secretKey: "",
      newUser: true,
      authorities: [{ role: "ROLE_CLIENT" }]
    });
  };

  const deleteUser = event => {
    toast("deleting");
  };

  return (
    <>
      <Paper style={{ paddingTop: 100 }}>
        <Grid container spacing={1}>
          <Grid item xs={3}>
            <UserGrid
              users={users}
              onEdit={onEditClick}
              resetForm={resetForm}
            ></UserGrid>
          </Grid>
          <Grid item xs={9}>
            <UserCard
              user={user}
              onChangeHandler={handleChange}
              onSubmitHandler={handleSubmit}
              deleteUser={deleteUser}
              roleChangeHandler={roleChangeHandler}
            ></UserCard>
          </Grid>
        </Grid>
      </Paper>
    </>
  );
}

function mapStateToProps(state) {
  return {
    users: state.userReducer.users
  };
}
const mapDispatchToProps = {
  updateUser: userActions.updateUser,
  registerNewUser: userActions.registerNewUser
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserView);
