"use client";

import { axiosInstance } from "@/apis/axiosInstace";
import { useQuery } from "@tanstack/react-query";

import styled from "styled-components";

const SummonerPage = () => {
  const fetchData = async () => {
    const res = await axiosInstance.get(`/summoner?input=${name}`);
    return res.data;
  };

  const { data, isLoading, error } = useQuery({
    queryKey: ["summonerData"],
    queryFn: () => fetchData("윤국현"),
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  const summonerName = data?.result[0]?.name;

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
