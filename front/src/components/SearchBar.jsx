"use client";

import React, { useRef } from "react";
import styled from "styled-components";
import SearchIcon from "@mui/icons-material/Search";
import { IconButton } from "@mui/material";

import { useRouter } from "next/navigation";

const SearchBar = () => {
  const ref = useRef(null);
  const router = useRouter();

  const handleSearch = async (e) => {
    e.preventDefault();
    let keyword = ref.current.value.trim();
    keyword = keyword.replace(/#/g, "-");
    router.push(`/summoner/${keyword}`);
    ref.current.value = "";
  };

  return (
    <S.Search onSubmit={handleSearch}>
      <S.Input type={"text"} placeholder="플레이어 이름 + #KR1" ref={ref} />
      <IconButton type="button" onClick={handleSearch}>
        <SearchIcon />
      </IconButton>
    </S.Search>
  );
};

export default SearchBar;

const S = {};

S.Search = styled.form`
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
