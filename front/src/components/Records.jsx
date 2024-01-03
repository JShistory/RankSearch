import React, { useState } from "react";
import styled from "styled-components";
import GameCard from "./GameCard";

const Records = ({ matchData, summonerName }) => {
  const [showAll, setShowAll] = useState(false);

  const displayCount = 10;

  return (
    <S.Wrapper>
      {matchData
        .slice(0, showAll ? matchData.length : displayCount)
        .map((match) => (
          <GameCard
            key={match.id}
            gameInfo={match.gameInfo}
            summonerName={summonerName}
          />
        ))}
      {!showAll && matchData.length > displayCount && (
        <S.MoreBox>
          <S.MoreButton onClick={() => setShowAll(true)}>더보기</S.MoreButton>
        </S.MoreBox>
      )}
    </S.Wrapper>
  );
};

export default Records;

const S = {};

S.Wrapper = styled.div`
  width: 100%;

  background-color: green;
`;

S.MoreBox = styled.div`
  display: flex;
  justify-content: center;
`;

S.MoreButton = styled.button`
  width: 100px;
  height: 30px;
  font-size: 16px;
  border-radius: 5px;
  cursor: pointer;
`;
