import timeHelper from "@/util/time-hleper";
import React from "react";
import styled from "styled-components";

const GameCard = ({ gameInfo, summonerName }) => {
  const { participants, gameCreation, gameDuration, gameMode } = gameInfo;
  const date = timeHelper(gameCreation);

  const minute = Math.floor(gameDuration / 60);
  const second = gameDuration % 60;
  // 자신의 닉네임을 찾기 위한 함수
  const findSummoner = () => {
    return participants.find(
      (participant) => participant.summonerName === summonerName
    );
  };

  // console.log(gameInfo.participants.length);

  // 자신의 참여 정보를 가져옴
  const mySummoner = findSummoner();
  return (
    <S.Wrapper>
      {mySummoner && (
        <div>
          내 정보:
          <div>game mode: {gameMode}</div>
          <div>{`${minute}분 ${second}초`}</div>
          <div>gameEnd: {date}</div>
          <div>킬: {mySummoner.kills}</div>
          <div>데스: {mySummoner.deaths}</div>
          <div>어시스트: {mySummoner.assists}</div>
        </div>
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
