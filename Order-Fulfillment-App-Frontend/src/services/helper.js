import axios from "axios";


 export const BASE_URL = "http://localhost:8081";
//export const BASE_URL = "https://apis.lcwdblogs.online/api/v1";

export const myAxios = axios.create({
  baseURL: BASE_URL,
}); 