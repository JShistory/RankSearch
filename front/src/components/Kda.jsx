import React from "react";
import styled from "styled-components";

const Kda = ({
  kills,
  deaths,
  assists,
  kdaGrade,
  totalMinionsKilled,
  gradeCs,
  killInvolvement,
}) => {
  return (
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
  );
};

export default Kda;

const S = {};

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
