import timeHelper from "@/util/time-hleper";
import React from "react";
import styled from "styled-components";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import { IconButton } from "@mui/material";
import { CHAMPION_IMAGE_URL, RUNE_IMAGE, SPELL_IMAGE_ID } from "@/const/api";

import { SPELL_NAMES } from "@/const/spell";

import { KO, QUEUETYPE } from "@/const/queue";

import { findMainRuneIcon, findSubRuneIcon } from "@/hooks/findRuneIcon";

import GameTime from "./GameTime";
import ChampSpellRune from "./ChampSpellRune";
import Kda from "./Kda";
import Item from "./Item";
import Team from "./Team";
import GameDetail from "./GameDetail";
import { useState } from "react";

const GameCard = ({ gameInfo, summonerName }) => {
  const [gameDetailOpne, setGameDetailOpen] = useState(false);

  const toggleGameDetail = () => {
    setGameDetailOpen(!gameDetailOpne);
  };

  const { participants, gameCreation, gameDuration, queueId } = gameInfo;
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
    totalMinionsKilled,
    mainRuneId1,
    subRuneId,
  } = mySummoner;

  const myTeam = participants.filter((participant) => participant.win === win);

  const totalKills = myTeam.reduce((sum, team) => sum + team.kills, 0);

  const killInvolvement = ((kills + assists) * 100) / totalKills;

  const kdaGrade = (kills + assists) / deaths;

  const gradeCs = totalMinionsKilled / minute;

  const myChampion = CHAMPION_IMAGE_URL(championName);
  const myDspell = SPELL_IMAGE_ID(SPELL_NAMES[dspell]);
  const myFspell = SPELL_IMAGE_ID(SPELL_NAMES[fspell]);

  const itemIds = [item0, item1, item2, item3, item4, item5];

  const queueType = QUEUETYPE[queueId];
  const queue = KO[queueType];

  const mainRuneIcon = findMainRuneIcon(mainRuneId1);
  const subRuneIcon = findSubRuneIcon(subRuneId);
  const mainRuneImage = RUNE_IMAGE(mainRuneIcon);
  const subRuneImage = RUNE_IMAGE(subRuneIcon);

  return (
    <S.Wrapper win={win}>
      {mySummoner && (
        <S.Container>
          <S.Content>
            <GameTime
              win={win}
              queue={queue}
              lastPlayDate={lastPlayDate}
              minute={minute}
              second={second}
            />
            <ChampSpellRune
              myChampion={myChampion}
              champLevel={champLevel}
              myDspell={myDspell}
              myFspell={myFspell}
              mainRuneImage={mainRuneImage}
              subRuneImage={subRuneImage}
            />

            <Kda
              kills={kills}
              deaths={deaths}
              assists={assists}
              kdaGrade={kdaGrade}
              totalMinionsKilled={totalMinionsKilled}
              gradeCs={gradeCs}
              killInvolvement={killInvolvement}
            />

            <Item itemIds={itemIds} item6={item6} />

            <Team winners={winners} losers={losers} />
          </S.Content>
          <S.DropDownBtnBox>
            <IconButton onClick={toggleGameDetail}>
              <KeyboardArrowDownIcon />
            </IconButton>
          </S.DropDownBtnBox>
        </S.Container>
      )}
      {gameDetailOpne && (
        <GameDetail
          setGameDetailOpen={setGameDetailOpen}
          winners={winners}
          losers={losers}
        />
      )}
    </S.Wrapper>
  );
};

export default GameCard;

const S = {};

S.Wrapper = styled.div`
  position: relative;
  width: 100%;
  height: 100px;
  background-color: ${({ win }) => (win ? "#d5e3ff" : "#ffd8d9")};
  border: 0.2px solid #000;
`;

S.Container = styled.div`
  position: relative;
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

S.DropDownBtnBox = styled.div`
  position: absolute;
  right: 5px;
  bottom: 5px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
`;
