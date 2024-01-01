"use client";

import Image from "next/image";
import React from "react";
import styled from "styled-components";

const TierImageComponent = ({ rank, tier, point, win, loss, game, type }) => {
  const rankImage = rank ? `${rank}.png` : "Unranked.png";
  const leaguePoint = point ? point : 0;

  return (
    <S.RankWrapper>
      <S.RankImage>
        <Image src={`/img/${rankImage}`} width={80} height={80} alt="rank" />
      </S.RankImage>
      <S.RankContainer>
        <h2>{type} 랭크</h2>
        <S.RankInfo>
          <span>{rank ? `등금: ${rank} ${tier}` : `등급: 배치`}</span>
          <span>{rank ? `리그 포인트: ${leaguePoint}` : null}</span>
          {/* <span>승급전: -</span> */}
        </S.RankInfo>
        <span>
          {game}전 {win}승 {loss}패
        </span>
      </S.RankContainer>
    </S.RankWrapper>
  );
};

export default TierImageComponent;

const S = {};

S.RankWrapper = styled.div`
  width: 360px;
  height: 120px;
  border: 1px solid #000;
  display: flex;
  align-items: center;
`;

S.RankImage = styled.div`
  width: 100px;
  height: 100px;
  display: flex;
  justify-content: center;
  /* background-color: red; */
  margin: 10px;
`;

S.RankContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

S.RankInfo = styled.div`
  display: flex;
  flex-direction: column;
`;
