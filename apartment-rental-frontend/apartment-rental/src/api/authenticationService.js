import API from "./baseService";
const AUTHENTICATION_PATH = "/authenticate";
export const USER_NAME_SESSION_ATTRIBUTE_NAME = "authenticatedUser";

class AuthenticationService {
  executeJwtAuthenticationService(user) {
    return API.post(`${AUTHENTICATION_PATH}`, {
      username: user.username,
      password: user.password
    });
  }

  registerSuccessfulLogin(username, token) {
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    sessionStorage.setItem("TOKEN", token);
  }

  createJWTToken() {
    return "Bearer " + sessionStorage.getItem("TOKEN");
  }

  logout() {
    sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem("TOKEN");
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return false;
    return true;
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return "";
    return user;
  }

  setupAxiosInterceptors() {
    API.interceptors.request.use(config => {
      if (this.isUserLoggedIn()) {
        config.headers.authorization = this.createJWTToken();
      }
      return config;
    });
  }
}

export default new AuthenticationService();
