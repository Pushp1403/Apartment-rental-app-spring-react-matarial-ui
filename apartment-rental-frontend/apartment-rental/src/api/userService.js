import API from "./baseService";
import * as constants from "./../redux/constants";

class UserService {
  registerUser(user) {
    return API.post(`${constants.USER_API_PATH}/registerUser`, {
      username: user.username,
      firstName: user.firstName,
      lastName: user.lastName,
      secretKey: user.secretKey,
      authorities: user.authorities
    });
  }

  retrieveUserDetails(username) {
    return API.get(`${constants.USER_API_PATH}/userDetails/${username}`);
  }

  listAllUsers() {
    return API.get(`${constants.USER_API_PATH}/listAllUsers`);
  }

  saveUserDetails(user) {
    return API.post(`${constants.USER_API_PATH}/updateUser`, { ...user });
  }
}

export default new UserService();
