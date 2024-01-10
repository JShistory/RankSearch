import React from "react";
import styled from "styled-components";
import CloseIcon from "@mui/icons-material/Close";
import { IconButton } from "@mui/material";
import Image from "next/image";
import { CHAMPION_IMAGE_URL, RUNE_IMAGE, SPELL_IMAGE_ID } from "@/const/api";
import { SPELL_NAMES } from "@/const/spell";

import { findMainRuneIcon, findSubRuneIcon } from "@/hooks/findRuneIcon";
import ItemImage from "./ItemImage";

const GameDetail = ({ setGameDetailOpen, winners, losers }) => {
  const toggleOpenClose = () => {
    setGameDetailOpen(false);
  };

  const winnerTotalKills = winners.reduce(
    (sum, winner) => sum + winner.kills,
    0
  );

  const loserTotalKills = losers.reduce((sum, loser) => sum + loser.kills, 0);

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
            <S.Th style={{ width: "180px" }}>챔피언 & 소환사</S.Th>
            <S.Th style={{ width: "100px" }}>SR</S.Th>
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
                  width={20}
                  height={20}
                  alt="champion name"
                />
                {winner.summonerName}
              </S.Td>
              <S.Td>
                <Image
                  src={SPELL_IMAGE_ID(SPELL_NAMES[winner.dspell])}
                  width={18}
                  height={18}
                  alt="dspell"
                />
                <Image
                  src={SPELL_IMAGE_ID(SPELL_NAMES[winner.fspell])}
                  width={18}
                  height={18}
                  alt="fspell"
                />

                <Image
                  src={RUNE_IMAGE(findMainRuneIcon(winner.mainRuneId1))}
                  width={18}
                  height={18}
                  alt="main rune"
                />
                <Image
                  src={RUNE_IMAGE(findSubRuneIcon(winner.subRuneId))}
                  width={18}
                  height={18}
                  alt="main rune"
                />
              </S.Td>
              <S.Td>
                <span>{winner.champLevel}</span>
              </S.Td>
              <S.Td>
                <div>
                  평점
                  {((winner.kills + winner.assists) / winner.deaths).toFixed(2)}
                </div>
                {`${winner.kills}/${winner.deaths}/${
                  winner.assists
                }(${Math.round(
                  ((winner.kills + winner.assists) * 100) / winnerTotalKills
                )}%)`}
              </S.Td>
              <S.Td>
                <div style={{ display: "flex", gap: "4px" }}>
                  <ItemImage item={winner.item0} />
                  <ItemImage item={winner.item1} />
                  <ItemImage item={winner.item2} />
                  <ItemImage item={winner.item3} />
                  <ItemImage item={winner.item4} />
                  <ItemImage item={winner.item5} />
                </div>
              </S.Td>
              <S.Td>{winner.goldEarned} G</S.Td>
              <S.Td>{winner.totalMinionsKilled}</S.Td>
            </S.Tr>
          ))}

          {losers.map((loser, index) => (
            <S.Tr key={index} win={loser.win}>
              <S.Td>
                <Image
                  src={CHAMPION_IMAGE_URL(loser.championName)}
                  width={20}
                  height={20}
                  alt="champion name"
                />
                {loser.summonerName}
              </S.Td>
              <S.Td>
                <Image
                  src={SPELL_IMAGE_ID(SPELL_NAMES[loser.dspell])}
                  width={18}
                  height={18}
                  alt="dspell"
                />
                <Image
                  src={SPELL_IMAGE_ID(SPELL_NAMES[loser.fspell])}
                  width={18}
                  height={18}
                  alt="fspell"
                />
                <Image
                  src={RUNE_IMAGE(findMainRuneIcon(loser.mainRuneId1))}
                  width={18}
                  height={18}
                  alt="main rune"
                />
                <Image
                  src={RUNE_IMAGE(findSubRuneIcon(loser.subRuneId))}
                  width={18}
                  height={18}
                  alt="main rune"
                />
              </S.Td>
              <S.Td>{loser.champLevel}</S.Td>
              <S.Td>
                <div>
                  평점
                  {((loser.kills + loser.assists) / loser.deaths).toFixed(2)}
                </div>
                {`${loser.kills}/${loser.deaths}/${loser.assists}(${Math.round(
                  ((loser.kills + loser.assists) * 100) / loserTotalKills
                )}%)`}
              </S.Td>
              <S.Td>
                <div style={{ display: "flex", gap: "4px" }}>
                  <ItemImage item={loser.item0} />
                  <ItemImage item={loser.item1} />
                  <ItemImage item={loser.item2} />
                  <ItemImage item={loser.item3} />
                  <ItemImage item={loser.item4} />
                  <ItemImage item={loser.item5} />
                </div>
              </S.Td>
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
  background-color: #e9e9b1;
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
