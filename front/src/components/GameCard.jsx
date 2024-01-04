import timeHelper from "@/util/time-hleper";
import React from "react";
import styled from "styled-components";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import { IconButton } from "@mui/material";
import {
  CHAMPION_IMAGE_URL,
  ITEM_IMAGE_URL,
  SPELL_IMAGE_ID,
} from "@/const/api";
import Image from "next/image";
import { spellName } from "@/const/spell";

const GameCard = ({ gameInfo, summonerName }) => {
  const { participants, gameCreation, gameDuration, gameMode } = gameInfo;
  const lastPlayDate = timeHelper(gameCreation);

  const minute = Math.floor(gameDuration / 60);
  const second = gameDuration % 60;

  // 자신의 닉네임을 찾기 위한 함수
  const findSummoner = () => {
    return participants.find(
      (participant) => participant.summonerName === summonerName
    );
  };

  const winners = participants.filter(
    (participant) => participant.win === true
  );

  const losers = participants.filter(
    (participant) => participant.win === false
  );

  // 자신의 참여 정보를 가져옴
  const mySummoner = findSummoner();

  const {
    championName,
    kills,
    deaths,
    assists,
    champLevel,
    dspell,
    fspell,
    item0,
    item1,
    item2,
    item3,
    item4,
    item5,
    item6,
    win,
  } = mySummoner;

  const myTeam = participants.filter((participant) => participant.win === win);

  const totalKills = myTeam.reduce((sum, team) => sum + team.kills, 0);
  console.log(totalKills);

  const killInvolvement = ((kills + assists) * 100) / totalKills;
  console.log(Math.round(killInvolvement));

  const kdaGrade = (kills + assists) / deaths;

  const myChampion = CHAMPION_IMAGE_URL(championName);
  const myDspell = SPELL_IMAGE_ID(spellName[dspell]);
  const myFspell = SPELL_IMAGE_ID(spellName[fspell]);
  const myItem0 = ITEM_IMAGE_URL(item0);
  const myItem1 = ITEM_IMAGE_URL(item1);
  const myItem2 = ITEM_IMAGE_URL(item2);
  const myItem3 = ITEM_IMAGE_URL(item3);
  const myItem4 = ITEM_IMAGE_URL(item4);
  const myItem5 = ITEM_IMAGE_URL(item5);
  const myItem6 = ITEM_IMAGE_URL(item6);

  return (
    <S.Wrapper>
      {mySummoner && (
        <S.Container>
          <S.Content>
            <S.GameMode>
              <S.QueueType win={win}>{gameMode}</S.QueueType>
              <div> {lastPlayDate}</div>
              <S.Win>{win ? "승리" : "패배"}</S.Win>
              <div>{`${minute}분 ${second}초`}</div>
            </S.GameMode>
            <S.ChampSpell>
              <S.Champ>
                <Image src={myChampion} width={60} height={60} alt="champion" />
                <S.ChampLevel>{champLevel}</S.ChampLevel>
              </S.Champ>
              <div>
                <Image src={myDspell} width={36} height={36} alt="d spell" />
                <Image src={myFspell} width={36} height={36} alt="f spell" />
              </div>
            </S.ChampSpell>
            <div>
              <div>
                <div>{`${kills} / ${deaths} / ${assists}`}</div>
                <div>{`${kdaGrade.toFixed(2)} 평점`}</div>
              </div>
            </div>
          </S.Content>
          <div>
            <IconButton>
              <KeyboardArrowDownIcon />
            </IconButton>
          </div>
        </S.Container>
      )}
    </S.Wrapper>
  );
};

export default GameCard;

const S = {};

S.Wrapper = styled.div`
  width: 100%;
  height: 100px;
  background-color: yellow;
  border: 1px solid #000;
`;

S.Container = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
`;

S.Content = styled.div`
  width: 100%;
  height: 100%;
  padding: 10px;
  display: flex;
  gap: 10px;
`;

S.GameMode = styled.div`
  width: 100px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  font-size: 14px;
  gap: 10px;
  color: #9e9eb1;
  background-color: white;
`;

S.QueueType = styled.div`
  font-weight: 700;
  color: ${({ win }) => (win ? "blue" : "red")};
`;

S.Win = styled.div`
  font-weight: 700;
`;

S.ChampSpell = styled.div`
  width: 100px;
  height: 100%;
  display: flex;
  gap: 6px;
`;

S.Champ = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

S.ChampLevel = styled.span`
  width: 100%;
  height: 100%;
  border: 1px solid #000;
  font-size: 14px;
  color: #fff;
  background: #000;
  text-align: center;
`;
