"use client";

import Image from "next/image";
import React from "react";
import styled from "styled-components";

const TierImageComponent = ({ rank }) => {
  const rankImage = rank ? `${rank}.png` : "Unranked.png";

  return (
    <S.RankWrapper>
      <S.RankImage>
        <Image src={`/img/${rankImage}`} width={80} height={80} alt="rank" />
      </S.RankImage>
      <S.RankContainer>
        <h2>솔로 랭크</h2>
        <S.RankInfo>
          <span>등급: SILVER III</span>
          <span>리그 포인트: 0</span>
          <span>승급전: -</span>
        </S.RankInfo>
        <span>31전 9승 22패</span>
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
