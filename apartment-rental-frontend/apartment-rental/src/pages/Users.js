import React, { useEffect } from "react";
import Header from "../common/header/Header";
import { connect } from "react-redux";
import UserView from "./UserView";
import * as userActions from "../redux/actions/userAction";
import authService from "../api/authenticationService";
import { toast } from "react-toastify";

function Users(props) {
  const { user, users, getuser, retrieveAllUsers } = props;
  useEffect(
    props => {
      getuser(authService.getLoggedInUserName()).catch(error => {
        toast(error.message);
      });
      retrieveAllUsers().catch(error => {
        toast(error.message);
      });
    },
    [getuser, retrieveAllUsers]
  );
  const Loader = ({ user, users }) => {
    console.log(user);
    console.log(users);
    if (user && users) {
      return (
        <div>
          <Header user={user} {...props} />
          <UserView users={users} {...props} />
        </div>
      );
    } else {
      return <div>loading</div>;
    }
  };

  return <Loader {...props}></Loader>;
}

function mapStateToProps(state) {
  return {
    user: state.userReducer.user,
    users: state.userReducer.users
  };
}

const mapDispatchToProps = {
  getuser: userActions.getUserDetails,
  retrieveAllUsers: userActions.retrieveAllUsers
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Users);
