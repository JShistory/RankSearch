"use client";

import React from "react";
import styled from "styled-components";
import SearchBar from "./SearchBar";

const Header = () => {
  return (
    <S.Wrapper>
      <S.Container>
        <S.Logo>JJ.GG</S.Logo>
        <SearchBar />
      </S.Container>
    </S.Wrapper>
  );
};

export default Header;

const S = {};

S.Wrapper = styled.div`
  width: 100%;
  height: 80px;
  background-color: #00b075;
  display: flex;
  justify-content: center;
`;

S.Container = styled.div`
  width: 1000px;
  height: 100%;
  /* background-color: ivory; */
  display: flex;
  align-items: center;
  justify-content: space-around;
`;

S.Logo = styled.div`
  font-size: 50px;
  font-weight: bold;
  margin: 20px;
`;
