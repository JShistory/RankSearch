import React from "react";
import styled from "styled-components";
import GameCard from "./GameCard";

const Records = ({ matchData, summonerName }) => {
  return (
    <S.Wrapper>
      {matchData.map((match) => (
        <GameCard
          key={match.id}
          gameInfo={match.gameInfo}
          summonerName={summonerName}
        />
      ))}
    </S.Wrapper>
  );
};

export default Records;

const S = {};

S.Wrapper = styled.div`
  width: 100%;

  background-color: green;
`;
