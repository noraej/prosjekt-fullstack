import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080/api/v1",
  });

const token = localStorage.getItem("token");

instance.defaults.headers.common["Authorization"] = token;

instance.defaults.validateStatus = (status: number) => {
  return status >= 200 && status < 300;
};