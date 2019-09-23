import React, { useState } from "react";
import UserGrid from "../components/user/UserGrid";
import Grid from "@material-ui/core/Grid";
import { Paper } from "@material-ui/core";
import UserCard from "../components/user/UserCard";
import { toast } from "react-toastify";
import * as userActions from "./../redux/actions/userAction";
import { connect } from "react-redux";

function UserView(props) {
  const { users, saveUser } = props;
  const newUser = {
    username: "",
    firstName: "",
    lastName: "",
    secretKey: "",
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
    saveUser()
      .then(() => {
        toast("user details Saved");
      })
      .catch(error => {
        toast(error.message);
      });
  };

  const onEditClick = user => {
    setUser({ ...user });
  };

  const resetForm = event => {
    setUser({
      username: "",
      firstName: "",
      lastName: "",
      secretKey: "",
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
            <UserGrid users={users} onEdit={onEditClick}></UserGrid>
          </Grid>
          <Grid item xs={9}>
            <UserCard
              user={user}
              onChangeHandler={handleChange}
              onSubmitHandler={handleSubmit}
              resetForm={resetForm}
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
  saveUser: userActions.saveUserDetails
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserView);
