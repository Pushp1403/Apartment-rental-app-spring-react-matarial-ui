import * as constants from "../constants";

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
      return {};
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
    case constants.USER_DETAILS_SAVED:
      return Object.assign(
        {},
        { user: state.user, users: [...state.users, action.user] }
      );
    default:
      return state;
  }
}
