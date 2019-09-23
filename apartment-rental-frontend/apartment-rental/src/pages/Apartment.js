import React, { useEffect } from "react";
import Header from "../common/header/Header";
import MainContent from "../common/body/MainContent";
import { connect } from "react-redux";
import * as apartmentActions from "../redux/actions/apartmentActions";
import * as userActions from "../redux/actions/userAction";
import authService from "../api/authenticationService";

function Landing(props) {
  const { apartments, user, retrieveAllApartments, getuser } = props;
  useEffect(
    props => {
      retrieveAllApartments().catch(error => {
        console.log(error);
      });
      getuser(authService.getLoggedInUserName()).catch(error => {
        console.log(error);
      });
    },
    [retrieveAllApartments, getuser]
  );

  const Loader = ({ apartments, user }) => {
    if (user && apartments) {
      return (
        <div>
          <Header user={user} {...props} />
          <MainContent apartments={apartments} />
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
    apartments: state.apartmentReducer.apartments,
    user: state.userReducer.user
  };
}

const mapDispatchToProps = {
  retrieveAllApartments: apartmentActions.retrieveAllApartments,
  getuser: userActions.getUserDetails
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Landing);
