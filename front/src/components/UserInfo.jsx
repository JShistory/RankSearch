import { BASE_URL } from "@/const/api";
import axios from "axios";
import Image from "next/image";
import { useRouter } from "next/navigation";

import React from "react";
import { useState } from "react";
import styled from "styled-components";
import RankTier from "./RankTier";

const UserInfo = ({
  profileIcon,
  summonerName,
  summonerLevel,
  tag,
  prevId,
  rankEntry,
}) => {
  const router = useRouter();

  const { soloRankEntry, freeRankEntry } = rankEntry;
  const {
    tier: soloRankTier,
    rank: soloRank,
    leaguePoints: soloLeaguePoint = 0,
    wins: soloRankWin = 0,
    losses: soloRankLosses = 0,
  } = soloRankEntry;

  const {
    tier: freeRankTier,
    rank: freeRank,
    leaguePoints: freeLeaguePoint = 0,
    wins: freeRankWin = 0,
    losses: freeRankLosses = 0,
  } = freeRankEntry;

  const totalSoloRankGames = soloRankWin + soloRankLosses;

  const totalFreeRankGames = freeRankWin + freeRankLosses;

  const [isRenewing, setIsRenewing] = useState(false);

  const handleRenewal = async () => {
    if (isRenewing) {
      return;
    }

    try {
      const res = await axios.post(
        `${BASE_URL}/summoner?input=${summonerName}`
      );

      console.log(res.data.message);

      setTimeout(() => {
        setIsRenewing(false);
      }, 60000);

      router.push(router.asPath);
    } catch (error) {
      console.error(error.message);
      setIsRenewing(false);
    }
  };

  return (
    <S.UserInfo>
      {/* user profile */}
      <Image
        src={profileIcon}
        width={120}
        height={120}
        alt="profile"
        priority={1}
      />
      <S.UserInfoContainer>
        <S.UserInfoName>
          <S.SummonerName>{summonerName}</S.SummonerName>
          <S.SummonerTag>#{tag}</S.SummonerTag>
        </S.UserInfoName>
        <S.UserInfoBox>
          <S.PrevId>Prev.{prevId}</S.PrevId>
          <S.SummonerLevel>Level: {summonerLevel}</S.SummonerLevel>
          <S.RenewalButton onClick={handleRenewal} disabled={isRenewing}>
            전적 갱신
          </S.RenewalButton>
        </S.UserInfoBox>
      </S.UserInfoContainer>
      <S.RankInfo>
        <RankTier
          rank={soloRank}
          tier={soloRankTier}
          point={soloLeaguePoint}
          win={soloRankWin}
          loss={soloRankLosses}
          game={totalSoloRankGames}
          type="솔로"
        />
        <RankTier
          rank={freeRank}
          tier={freeRankTier}
          point={freeLeaguePoint}
          win={freeRankWin}
          loss={freeRankLosses}
          game={totalFreeRankGames}
          type="자유"
        />
      </S.RankInfo>
    </S.UserInfo>
  );
};

export default UserInfo;

const S = {};

S.UserInfo = styled.div`
  width: 100%;
  height: 200px;
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
  background-color: ${({ disabled }) => (disabled ? "#ccc" : "#fff")};
  border: 1px solid #000;
`;

S.RankInfo = styled.div`
  display: flex;
  gap: 5px;
`;
