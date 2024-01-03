import timeHelper from "@/util/time-hleper";
import React from "react";

const GameCard = ({ gameInfo, summonerName }) => {
  const {
    participants,
    gameStartTimestamp,
    gameEndTimestamp,
    gameCreation,
    gameDuration,
  } = gameInfo;
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
    <div>
      {mySummoner && (
        <div>
          내 정보:
          <div>{`${minute}분 ${second}초`}</div>
          <div>gameEnd: {date}</div>
          <div>킬: {mySummoner.kills}</div>
          <div>데스: {mySummoner.deaths}</div>
          <div>어시스트: {mySummoner.assists}</div>
        </div>
      )}
    </div>
  );
};

export default GameCard;
