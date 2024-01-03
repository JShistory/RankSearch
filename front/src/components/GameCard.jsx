import React from "react";

const GameCard = ({ gameInfo, summonerName }) => {
  const { participants, gameMode, gameDuration, win } = gameInfo;

  // 자신의 닉네임을 찾기 위한 함수
  const findSummoner = () => {
    return participants.find(
      (participant) => participant.summonerName === summonerName
    );
  };

  // 자신의 참여 정보를 가져옴
  const mySummoner = findSummoner();
  return (
    <div>
      {mySummoner && (
        <div>
          내 정보:
          <div>킬: {mySummoner.kills}</div>
          <div>데스: {mySummoner.deaths}</div>
          <div>어시스트: {mySummoner.assists}</div>
        </div>
      )}
    </div>
  );
};

export default GameCard;
