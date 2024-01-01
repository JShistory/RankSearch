"use client";

import TierImageComponent from "@/components/TierImageComponent";
import UserInfo from "@/components/UserInfo";
import { PROFILE_ICON_URL } from "@/const/api";
import { useSummonuerQuery } from "@/hooks/useSummonerQuery";
import { useParams } from "next/navigation";

import styled from "styled-components";

const SummonerPage = () => {
  const params = useParams();
  const id = params.id;

  const { data, isLoading, error } = useSummonuerQuery(id);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  const summonerName = data?.result[0]?.name;
  const tag = data?.result[0]?.tag;
  const profileIconId = data?.result[0]?.profileIconId;
  const summonerLevel = data?.result[0]?.summonerLevel;
  const prevId = data?.result[0]?.prevId;

  const soloRank = data?.result[0].leagueEntries[0]?.tier;
  const freeRank = data?.result[0].leagueEntries[1]?.tier;

  const soloTier = data?.result[0].leagueEntries[0]?.rank;
  const freeTier = data?.result[0].leagueEntries[1]?.rank;

  const soloLeaguePoint = data?.result[0].leagueEntries[0]?.leaguePoints;
  const freeLeaguePoint = data?.result[0].leagueEntries[1]?.leaguePoints;

  const soloRankWin = data?.result[0].leagueEntries[0]?.wins || 0;
  const soloRankLosses = data?.result[0].leagueEntries[0]?.losses || 0;
  const freeRankWin = data?.result[0].leagueEntries[1]?.wins || 0;
  const freeRankLosses = data?.result[0].leagueEntries[1]?.losses || 0;

  const totalSoloRankGames = soloRankWin + soloRankLosses;

  // console.log(totalSoloRankGames);

  const totalFreeRankGames = freeRankWin + freeRankLosses;
  // console.log(totalFreeRankGames);

  const profileIcon = PROFILE_ICON_URL(profileIconId);

  return (
    <S.Wrapper>
      <S.Container>
        <UserInfo
          profileIcon={profileIcon}
          summonerName={summonerName}
          summonerLevel={summonerLevel}
          tag={tag}
          prevId={prevId}
        />
        <S.RankBox>
          <TierImageComponent
            rank={soloRank}
            tier={soloTier}
            point={soloLeaguePoint}
            win={soloRankWin}
            loss={soloRankLosses}
            game={totalSoloRankGames}
            type="솔로"
          />
          <TierImageComponent
            rank={freeRank}
            tier={freeTier}
            point={freeLeaguePoint}
            win={freeRankWin}
            loss={freeRankLosses}
            game={totalFreeRankGames}
            type="자유"
          />
          {/* tier card */}
        </S.RankBox>
        <div>{/* match */}</div>
      </S.Container>
    </S.Wrapper>
  );
};

export default SummonerPage;

const S = {};
S.Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
`;

S.Container = styled.div`
  width: 1000px;
  height: 100%;
  background-color: orange;
`;

S.RankBox = styled.div`
  width: 100%;
  height: 300px;
  display: flex;
  justify-content: space-around;
  background-color: yellow;
  padding: 20px;
`;
