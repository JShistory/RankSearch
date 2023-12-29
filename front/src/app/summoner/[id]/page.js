"use client";

import { axiosInstance } from "@/apis/axiosInstace";
import { useQuery } from "@tanstack/react-query";
import { useParams } from "next/navigation";

import styled from "styled-components";

const SummonerPage = () => {
  const params = useParams();
  let id = params.id;

  console.log(id);
  const fetchData = async () => {
    const res = await axiosInstance.get(`/summoner?input=${id}`);
    return res.data;
  };

  const { data, isLoading, error } = useQuery({
    queryKey: ["summonerData"],
    queryFn: () => fetchData(id),
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  let summonerName = data?.result[0]?.name;

  const tier = data?.result;
  const tag = data?.result[0]?.tag;
  // console.log(tag);
  console.log(tier);
  console.log(summonerName);

  return (
    <S.Wrapper>
      <S.Container>
        :)
        <div>{/* user profile */}</div>
        <div>{/* tier card */}</div>
        <div>{/* match */}</div>
      </S.Container>
    </S.Wrapper>
  );
};

export default SummonerPage;

const S = {};
S.Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
`;

S.Container = styled.div`
  width: 1000px;
  height: 100%;
  background-color: orange;
`;