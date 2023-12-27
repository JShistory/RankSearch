"use client";

import React, { useRef, useState } from "react";
import styled from "styled-components";
import SearchIcon from "@mui/icons-material/Search";
import { IconButton } from "@mui/material";
import { axiosInstance } from "@/apis/axiosInstace";
import { useRouter } from "next/navigation";

const SearchBar = () => {
  const ref = useRef(null);
  const router = useRouter();

  const handleSearch = async (e) => {
    e.preventDefault();
    const keyword = ref.current.value.trim();
    try {
      router.push(`/summoner/${keyword}`);
      const url = axiosInstance.get(`summoner?input=${keyword}`);

      const res = await url;
      const data = await res.data;
    } catch (error) {
      console.error("Error", error);
    }
  };

  return (
    <S.Search onSubmit={handleSearch}>
      <S.Input type={"text"} placeholder="플레이어 이름 + #KR1" ref={ref} />
      <IconButton>
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
