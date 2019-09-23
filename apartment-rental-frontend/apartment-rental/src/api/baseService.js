import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080/",
  responseType: "json",
  headers: sessionStorage.getItem("TOKEN")
    ? { Authorization: "Bearer " + sessionStorage.getItem("TOKEN") }
    : {}
});
