import Axios from "axios";

export const API_BASE_URL = 'http://localhost:8082/api/'

export const axios = Axios.create({

 baseURL: API_BASE_URL

})