import timeHelper from "@/util/time-hleper";
import React from "react";
import styled from "styled-components";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import { IconButton } from "@mui/material";
import {
  CHAMPION_IMAGE_URL,
  ITEM_IMAGE_URL,
  RUNE_IMAGE,
  SPELL_IMAGE_ID,
} from "@/const/api";
import Image from "next/image";
import { SPELL_NAMES } from "@/const/spell";
import NonItem from "./NonItem";
import { KO, QUEUETYPE } from "@/const/queue";

import {
  findMainRuneIcon,
  findRuneIcon,
  findSubRuneIcon,
  mainRuneIcon,
} from "@/hooks/findRuneIcon";
import styledEngine from "@mui/styled-engine";
import GameTime from "./GameTime";

const GameCard = ({ gameInfo, summonerName }) => {
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

  console.log(winners);

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
            {/* <S.GameMode>
              <S.QueueType win={win}>{queue}</S.QueueType>
              <div> {lastPlayDate}</div>
              <S.Win>{win ? "승리" : "패배"}</S.Win>
              <div>{`${minute}분 ${second}초`}</div>
            </S.GameMode> */}
            <GameTime
              win={win}
              queue={queue}
              lastPlayDate={lastPlayDate}
              minute={minute}
              second={second}
            />
            <S.ChampSpell>
              <S.Champ>
                <Image src={myChampion} width={60} height={60} alt="champion" />
                <S.ChampLevel>{champLevel}</S.ChampLevel>
              </S.Champ>
              <div>
                <Image src={myDspell} width={36} height={36} alt="d spell" />
                <Image src={myFspell} width={36} height={36} alt="f spell" />
              </div>
              <div>
                <Image
                  src={mainRuneImage}
                  width={36}
                  height={36}
                  alt="d spell"
                />
                <Image
                  src={subRuneImage}
                  width={36}
                  height={36}
                  alt="f spell"
                />
              </div>
            </S.ChampSpell>
            <S.KdaContainer>
              <S.KdaBox>
                <S.Kda>{`${kills} / ${deaths} / ${assists}`}</S.Kda>
                <S.KillRate>{`${kdaGrade.toFixed(2)} 평점`}</S.KillRate>
                <div>{`CS ${totalMinionsKilled}(${gradeCs.toFixed(1)})`}</div>
                <S.killInvolvement>{`킬관여 ${Math.round(
                  killInvolvement
                )}%`}</S.killInvolvement>
              </S.KdaBox>
            </S.KdaContainer>
            <S.ItemContainer>
              {itemIds.map((item, index) => (
                <div key={index}>
                  {item !== 0 ? (
                    <Image
                      src={ITEM_IMAGE_URL(item)}
                      width={36}
                      height={36}
                      alt="item"
                    />
                  ) : (
                    <NonItem />
                  )}
                </div>
              ))}
            </S.ItemContainer>
            <S.Item>
              {item6 ? (
                <Image
                  src={ITEM_IMAGE_URL(item6)}
                  width={36}
                  height={36}
                  alt="item"
                />
              ) : (
                <NonItem />
              )}
            </S.Item>
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
                    <S.TeamSummonerName>
                      {winner.summonerName}
                    </S.TeamSummonerName>
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
                    <S.TeamSummonerName>
                      {loser.summonerName}
                    </S.TeamSummonerName>
                  </S.TeamChampSummoner>
                ))}
              </S.TeamInfo>
            </S.Team>
          </S.Content>
          <S.DropDownBtnBox>
            <IconButton>
              <KeyboardArrowDownIcon />
            </IconButton>
          </S.DropDownBtnBox>
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
  background-color: ${({ win }) => (win ? "#d5e3ff" : "#ffd8d9")};
  border: 0.2px solid #000;
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

// S.GameMode = styled.div`
//   width: 100px;
//   height: 100%;
//   display: flex;
//   flex-direction: column;
//   justify-content: space-around;
//   font-size: 14px;
//   gap: 10px;
//   color: #9e9eb1;
// `;

// S.QueueType = styled.div`
//   font-weight: 700;
//   color: ${({ win }) => (win ? "blue" : "red")};
// `;

// S.Win = styled.div`
//   font-weight: 700;
// `;

S.ChampSpell = styled.div`
  width: 100px;
  height: 100%;
  display: flex;
  gap: 6px;
  width: 150px;
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

S.KdaContainer = styled.div`
  width: 100px;
  height: 100%;
`;

S.KdaBox = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
`;

S.Kda = styled.div`
  font-size: 16px;
  font-weight: bold;
`;

S.KillRate = styled.div`
  font-size: 14px;
  color: #9e9eb1;
`;

S.killInvolvement = styled.div`
  font-size: 14px;
  color: red;
`;

S.ItemContainer = styled.div`
  width: 120px;
  height: 100%;
  display: flex;
  gap: 5px;
  flex-wrap: wrap;
`;

S.Item = styled.div`
  padding-right: 10px;
  border-right: 1px solid #9e9eb1;
`;

S.DropDownBtnBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
`;

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
