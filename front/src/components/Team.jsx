import { CHAMPION_IMAGE_URL } from "@/const/api";
import Image from "next/image";
import React from "react";
import styled from "styled-components";

const Team = ({ winners, losers }) => {
  return (
    <S.Team>
      <S.TeamInfo>
        {winners.map((winner, index) => (
          <S.TeamChampSummoner key={index}>
            <Image
              src={CHAMPION_IMAGE_URL(winner.championName)}
              width={15}
              height={15}
              alt="champ"
            />
            <S.TeamSummonerName>{winner.summonerName}</S.TeamSummonerName>
          </S.TeamChampSummoner>
        ))}
      </S.TeamInfo>
      <S.TeamInfo>
        {losers.map((loser, index) => (
          <S.TeamChampSummoner key={index}>
            <Image
              src={CHAMPION_IMAGE_URL(loser.championName)}
              width={15}
              height={15}
              alt="champ"
            />
            <S.TeamSummonerName>{loser.summonerName}</S.TeamSummonerName>
          </S.TeamChampSummoner>
        ))}
      </S.TeamInfo>
    </S.Team>
  );
};

export default Team;

const S = {};

S.Team = styled.div`
  width: 300px;
  height: 100%;
  display: flex;
`;

S.TeamInfo = styled.div`
  display: flex;
  width: 150px;
  flex-direction: column;
  gap: 2px;
  justify-content: center;
`;

S.TeamChampSummoner = styled.div`
  display: flex;
  align-items: center;
`;

S.TeamSummonerName = styled.span`
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  font-size: 14px;
`;
