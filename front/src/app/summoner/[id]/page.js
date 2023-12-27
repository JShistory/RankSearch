"use client";

import { axiosInstance } from "@/apis/axiosInstace";
import { useEffect } from "react";
import styled from "styled-components";

const SummonerPage = () => {
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
