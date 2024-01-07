import React from "react";
import styled from "styled-components";

const GameTime = ({ win, queue, lastPlayDate, minute, second }) => {
  return (
    <S.GameMode>
      <S.QueueType win={win}>{queue}</S.QueueType>
      <div> {lastPlayDate}</div>
      <S.Win>{win ? "승리" : "패배"}</S.Win>
      <div>{`${minute}분 ${second}초`}</div>
    </S.GameMode>
  );
};

export default GameTime;

const S = {};

S.GameMode = styled.div`
  width: 100px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  font-size: 14px;
  gap: 10px;
  color: #9e9eb1;
`;

S.QueueType = styled.div`
  font-weight: 700;
  color: ${({ win }) => (win ? "blue" : "red")};
`;

S.Win = styled.div`
  font-weight: 700;
`;
