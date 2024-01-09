"use client";

import Image from "next/image";
import React from "react";
import styled from "styled-components";

const RankTier = ({ rank, tier, point, win, loss, game, type }) => {
  const tierImage = tier ? `${tier}.png` : "Unranked.png";

  return (
    <S.RankWrapper>
      <S.RankImage>
        <Image
          src={`/img/${tierImage}`}
          width={80}
          height={80}
          alt="tier"
          priority
        />
      </S.RankImage>
      <S.RankContainer>
        <h2>{type} 랭크</h2>
        <S.RankInfo>
          <span>{tier ? `등급: ${tier} ${rank}` : `등급: 배치`}</span>
          <span>{tier ? `리그 포인트: ${point}` : null}</span>
          {/* <span>승급전: -</span> */}
        </S.RankInfo>
        <span>
          {game}전 {win}승 {loss}패
        </span>
      </S.RankContainer>
    </S.RankWrapper>
  );
};

export default RankTier;

const S = {};

S.RankWrapper = styled.div`
  width: 250px;
  height: 120px;
  border: 1px solid #000;
  display: flex;
  align-items: center;
  background-color: #fff;
`;

S.RankImage = styled.div`
  width: 80px;
  height: 80px;
  display: flex;
  justify-content: center;
  /* background-color: red; */
  margin: 10px;
`;

S.RankContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
  font-size: 14px;
`;

S.RankInfo = styled.div`
  display: flex;
  flex-direction: column;
  font-size: 14px;
`;
