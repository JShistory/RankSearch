import { axiosInstance } from "@/apis/axiosInstace";
import axios from "axios";

export const fetchData = async (id) => {
  const res = await axiosInstance.get(`/summoner?input=${id}`);
  return res.data;
};

// export const fetchRuneData = async () => {
//   const res = await axios.get(
//     "https://ddragon.canisback.com/latest/data/ko_KR/runesReforged.json"
//   );
//   return res.data;
// };
