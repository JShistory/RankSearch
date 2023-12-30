import { axiosInstance } from "@/apis/axiosInstace";

export const fetchData = async (id) => {
  const res = await axiosInstance.get(`/summoner?input=${id}`);
  return res.data;
};
