"use client";

import styled from "styled-components";
import SearchBar from "@/components/SearchBar";

export default function Home() {
  return (
    <S.Wrapper>
      <S.Container>
        <S.Logo>JJ.GG</S.Logo>
        <S.SearchBar>
          <SearchBar />
        </S.SearchBar>
      </S.Container>
    </S.Wrapper>
  );
}

const S = {};

S.Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
`;

S.Container = styled.div`
  width: 1100px;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;

  gap: 10px;
`;

S.Logo = styled.div`
  font-size: 80px;
  font-weight: bold;
  margin: 20px;
  height: 400px;
  width: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

S.SearchBar = styled.div``;

S.Search = styled.div`
  width: 600px;
  height: 50px;
  border: 1px solid #000;
  display: flex;
  border-radius: 20px;
  background-color: #fff;
  justify-content: space-between;
  padding: 0 10px;
`;

S.Input = styled.input`
  border: none;
  outline: none;
  width: 100%;
  font-size: 16px;
  border-radius: 20px;
`;
