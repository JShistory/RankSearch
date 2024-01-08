import React from "react";
import styled from "styled-components";
import CloseIcon from "@mui/icons-material/Close";
import { IconButton } from "@mui/material";

const GameDetail = ({ setGameDetailOpen }) => {
  const toggleOpenClose = () => {
    setGameDetailOpen(false);
  };

  return (
    <S.Wrapper>
      <S.Title>
        <span>게임 정보</span>
        <IconButton onClick={toggleOpenClose}>
          <CloseIcon />
        </IconButton>
      </S.Title>
      <S.Table>
        <thead>
          <tr>
            <S.Th>티어</S.Th>
            <S.Th>챔피언 & 소환사</S.Th>
            <S.Th>SR</S.Th>
            <S.Th>Lv</S.Th>
            <S.Th style={{ width: "250px" }}>아이템</S.Th>
            <S.Th>Gold</S.Th>
            <S.Th>CS</S.Th>
          </tr>
        </thead>
        <tbody></tbody>
      </S.Table>
    </S.Wrapper>
  );
};

export default GameDetail;

const S = {};

S.Wrapper = styled.div`
  width: 100%;
  height: 500px;
  background-color: orange;
  border: 1px solid gray;
  margin: 0;
  z-index: 9;
  text-align: center;
  display: block;
  position: absolute;
  padding-top: 10px;
`;

S.Title = styled.div`
  display: flex;
  justify-content: space-between;

  span {
    flex: 1;
  }
`;

S.Table = styled.table`
  width: 100%;
`;

S.Th = styled.th`
  background-color: #9e9eb1;
  border: 1px solid #fff;
  font-size: 12px;
  padding: 4px;
`;
