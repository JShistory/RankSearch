import { BASE_URL } from "@/const/api";
import { default as axios } from "axios";

export const axiosInstance = axios.create({
  baseURL: BASE_URL,
  timeout: 10000,
});
