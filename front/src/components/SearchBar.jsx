"use client";

import React from "react";
import styled from "styled-components";
import SearchIcon from "@mui/icons-material/Search";
import { IconButton } from "@mui/material";

const SearchBar = () => {
  return (
    <S.Search>
      <S.Input type={"text"} placeholder="플레이어 이름 + #KR1" />
      <IconButton>
        <SearchIcon />
      </IconButton>
    </S.Search>
  );
};

export default SearchBar;

const S = {};

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
