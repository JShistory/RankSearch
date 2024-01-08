import React from "react";
import styled from "styled-components";
import CloseIcon from "@mui/icons-material/Close";
import { IconButton } from "@mui/material";
import Image from "next/image";
import {
  CHAMPION_IMAGE_URL,
  ITEM_IMAGE_URL,
  RUNE_IMAGE,
  SPELL_IMAGE_ID,
} from "@/const/api";
import { SPELL_NAMES } from "@/const/spell";
import ItemImage from "./ItemImage";
import { findMainRuneIcon, findSubRuneIcon } from "@/hooks/findRuneIcon";

const GameDetail = ({ setGameDetailOpen, winners, losers }) => {
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
            <S.Th>챔피언 & 소환사</S.Th>
            <S.Th>SR</S.Th>
            <S.Th>Lv</S.Th>
            <S.Th>KDA</S.Th>
            <S.Th style={{ width: "250px" }}>아이템</S.Th>
            <S.Th>Gold</S.Th>
            <S.Th>CS</S.Th>
          </tr>
        </thead>
        <tbody>
          {winners.map((winner, index) => (
            <S.Tr key={index} win={winner.win}>
              <S.Td>
                <Image
                  src={CHAMPION_IMAGE_URL(winner.championName)}
                  width={15}
                  height={15}
                  alt="champion name"
                />
                {winner.summonerName}
              </S.Td>
              <S.Td>
                <Image
                  src={SPELL_IMAGE_ID(SPELL_NAMES[winner.dspell])}
                  width={15}
                  height={15}
                  alt="dspell"
                />
                <Image
                  src={SPELL_IMAGE_ID(SPELL_NAMES[winner.fspell])}
                  width={15}
                  height={15}
                  alt="fspell"
                />
                <Image
                  src={RUNE_IMAGE(findMainRuneIcon(winner.mainRuneId1))}
                  width={15}
                  height={15}
                  alt="main rune"
                />
                <Image
                  src={RUNE_IMAGE(findSubRuneIcon(winner.subRuneId))}
                  width={15}
                  height={15}
                  alt="main rune"
                />
              </S.Td>
              <S.Td>{winner.champLevel}</S.Td>
              <S.Td></S.Td>
              <S.Td></S.Td>
              <S.Td>{winner.goldEarned} G</S.Td>
              <S.Td>{winner.totalMinionsKilled}</S.Td>
            </S.Tr>
          ))}

          {losers.map((loser, index) => (
            <S.Tr key={index} win={loser.win}>
              <S.Td>
                <Image
                  src={CHAMPION_IMAGE_URL(loser.championName)}
                  width={15}
                  height={15}
                  alt="champion name"
                />
                {loser.summonerName}
              </S.Td>
              <S.Td>
                <Image
                  src={SPELL_IMAGE_ID(SPELL_NAMES[loser.dspell])}
                  width={15}
                  height={15}
                  alt="dspell"
                />
                <Image
                  src={SPELL_IMAGE_ID(SPELL_NAMES[loser.fspell])}
                  width={15}
                  height={15}
                  alt="fspell"
                />
                <Image
                  src={RUNE_IMAGE(findMainRuneIcon(loser.mainRuneId1))}
                  width={15}
                  height={15}
                  alt="main rune"
                />
                <Image
                  src={RUNE_IMAGE(findSubRuneIcon(loser.subRuneId))}
                  width={15}
                  height={15}
                  alt="main rune"
                />
              </S.Td>
              <S.Td>{loser.champLevel}</S.Td>
              <S.Td></S.Td>
              <S.Td></S.Td>
              <S.Td>{loser.goldEarned} G</S.Td>
              <S.Td>{loser.totalMinionsKilled}</S.Td>
            </S.Tr>
          ))}
        </tbody>
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
  height: 20px;
  display: flex;
  justify-content: space-between;

  span {
    flex: 1;
  }
`;

S.Table = styled.table`
  width: 100%;
  margin-top: 10px;
`;

S.Th = styled.th`
  background-color: #9e9eb1;
  border: 1px solid #fff;
  font-size: 12px;
  padding: 4px;
`;

S.Tr = styled.tr`
  td {
    background-color: ${({ win }) => (win ? "#DDDDFF" : "#FFDDFF")};
  }
`;

S.Td = styled.td`
  padding: 4px;
  text-align: left;
  border: 1px solid #fff;
  padding: 10px;
  font-size: 12px;
`;
