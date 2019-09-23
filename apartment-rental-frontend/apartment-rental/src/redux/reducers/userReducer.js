import * as constants from "../constants";
import authService from "./../../api/authenticationService";

export default function userDetailsReducers(
  state = { user: "", users: "" },
  action
) {
  switch (action.type) {
    case constants.AUTHENTICATE_USER_SUCCESS:
      return Object.assign(
        {},
        {
          user: action.user,
          users: state.users
        }
      );
    case constants.REGISTER_NEW_USER:
      return updateState(state, action);
    case constants.LOAD_USER_SUCCESS:
      return Object.assign(
        {},
        {
          user: { ...state.user },
          users: action.users
        }
      );
    case constants.USER_LOAD_SUCCESS:
      return Object.assign({}, { user: action.user, users: state.users });
    case constants.USER_DETAILS_UPDATE:
      return mergeUsers(state, action);
    default:
      return state;
  }
}

function updateState(state, action) {
  if (authService.getLoggedInUserName()) {
    return Object.assign(
      {},
      { user: { ...state.user }, users: [...state.users, action.user] }
    );
  } else {
    return {};
  }
}

function mergeUsers(state, action) {
  let users = state.users;
  let newState = users.filter(user => user.username !== action.user.username);
  newState.push(action.user);
  return Object.assign(
    {},
    {
      users: newState,
      user: { ...state.user }
    }
  );
}
