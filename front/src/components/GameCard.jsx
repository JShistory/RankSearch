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
import { spellName } from "@/const/image";

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

  // 자신의 참여 정보를 가져옴
  const mySummoner = findSummoner();

  const {
    championName,
    kills,
    deaths,
    assists,
    championLevel,
    dspell,
    fspell,
    item0,
    item1,
    item2,
    item3,
    item4,
    item5,
    item6,
  } = mySummoner;

  const myChampion = CHAMPION_IMAGE_URL(championName);
  const myDspell = SPELL_IMAGE_ID(spellName[dspell]);
  const myFspell = SPELL_IMAGE_ID(fspell);
  const myItem0 = ITEM_IMAGE_URL(item0);
  const myItem1 = ITEM_IMAGE_URL(item1);

  return (
    <S.Wrapper>
      {mySummoner && (
        <S.Container>
          <S.Content>
            <S.GameMode>
              <S.QueueType win={mySummoner.win}>{gameMode}</S.QueueType>
              <div> {lastPlayDate}</div>
              <S.Win>{mySummoner.win ? "승리" : "패배"}</S.Win>
              <div>{`${minute}분 ${second}초`}</div>
            </S.GameMode>
            <div>
              <Image src={myChampion} width={50} height={50} alt="champion" />
              <div>
                <Image src={myDspell} width={30} height={30} alt="d spell" />
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
