import Image from "next/image";
import React from "react";
import styled from "styled-components";

const UserInfo = ({
  profileIcon,
  summonerName,
  summonerLevel,
  tag,
  prevId,
}) => {
  return (
    <S.UserInfo>
      {/* user profile */}
      <Image src={profileIcon} width={120} height={120} alt="profile" />
      <S.UserInfoContainer>
        <S.UserInfoName>
          <S.SummonerName>{summonerName}</S.SummonerName>
          <S.SummonerTag>#{tag}</S.SummonerTag>
        </S.UserInfoName>
        <S.UserInfoBox>
          <S.PrevId>Prev.{prevId}</S.PrevId>
          <S.SummonerLevel>Level: {summonerLevel}</S.SummonerLevel>
          <S.RenewalButton>전적 갱신</S.RenewalButton>
        </S.UserInfoBox>
      </S.UserInfoContainer>
    </S.UserInfo>
  );
};

export default UserInfo;

const S = {};

S.UserInfo = styled.div`
  width: 100%;
  height: 200px;
  background-color: pink;
  padding: 20px;
  display: flex;
  gap: 10px;
`;

S.SummonerName = styled.span`
  font-size: 24px;
  font-weight: bold;
`;

S.SummonerTag = styled.span`
  font-size: 24px;
  color: #9e9eb1;
`;

S.SummonerLevel = styled.span`
  font-size: 18px;
`;

S.UserInfoBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

S.UserInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 120px;
`;

S.UserInfoName = styled.div`
  display: flex;
  flex: 1;
`;

S.PrevId = styled.span`
  font-size: 16px;
  color: #9e9eb1;
`;

S.RenewalButton = styled.button`
  width: 100px;
  height: 30px;
  border-radius: 6px;
  cursor: pointer;
`;
