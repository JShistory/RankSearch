import { fetchData } from "@/apis/summonerApi";
import { useQuery } from "@tanstack/react-query";

export const useSummonuerQuery = (id) => {
  return useQuery({
    queryKey: ["summonerData"],
    queryFn: () => fetchData(id),
  });
};
