import * as constants from "../constants";
import authService from "../../api/authenticationService";
import userService from "../../api/userService";

export function authenticateUserSuccess(user) {
  return { type: constants.AUTHENTICATE_USER_SUCCESS, user };
}

export function registerNewUserSuccess(user) {
  return { type: constants.REGISTER_NEW_USER, user };
}

export function loadUserSuccess(users) {
  return { type: constants.LOAD_USER_SUCCESS, users };
}

export function userLoadSuccess(user) {
  return { type: constants.USER_LOAD_SUCCESS, user };
}

export function userDetailsUpdateSuccess(user) {
  return { type: constants.USER_DETAILS_UPDATE, user };
}

export function registerNewUser(user) {
  return async function(dispatch, getState) {
    return userService
      .registerUser(user)
      .then(res => {
        dispatch(registerNewUserSuccess({}));
      })
      .catch(error => {
        throw error;
      });
  };
}
export function authenticateuser(user) {
  return async function(dispatch, getState) {
    try {
      const response = await authService.executeJwtAuthenticationService(user);
      authService.registerSuccessfulLogin(user.username, response.data.token);
    } catch (error) {
      throw error;
    }
  };
}

export function getUserDetails(username) {
  return async function(dispatch, getState) {
    try {
      const res = await userService.retrieveUserDetails(username);
      dispatch(userLoadSuccess(res.data));
    } catch (error) {
      throw error;
    }
  };
}

export function retrieveAllUsers() {
  return async function(dispatch, getState) {
    try {
      const res = await userService.listAllUsers();
      dispatch(loadUserSuccess(res.data));
    } catch (error) {
      throw error;
    }
  };
}

export function updateUser(user) {
  return async function(dispatch, getState) {
    try {
      const res = await userService.saveUserDetails(user);
      dispatch(userDetailsUpdateSuccess(res.data));
    } catch (error) {
      throw error;
    }
  };
}
