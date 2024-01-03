"use client";

import TierImageComponent from "@/components/RankTier";
import Records from "@/components/Records";
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

  const summoner = data?.result[0];

  const { name: summonerName, tag, prevId } = summoner;

  const profileIconId = data?.result[0]?.profileIconId;
  const summonerLevel = data?.result[0]?.summonerLevel;

  const soloRankEntry = data?.result[0]?.leagueEntries[0] || {};
  const freeRankEntry = data?.result[0]?.leagueEntries[1] || {};

  const { matchData } = summoner;

  // const {
  //   tier: soloRankTier,
  //   rank: soloRank,
  //   leaguePoints: soloLeaguePoint = 0,
  //   wins: soloRankWin = 0,
  //   losses: soloRankLosses = 0,
  // } = soloRankEntry;

  // const {
  //   tier: freeRankTier,
  //   rank: freeRank,
  //   leaguePoints: freeLeaguePoint = 0,
  //   wins: freeRankWin = 0,
  //   losses: freeRankLosses = 0,
  // } = freeRankEntry;

  // const totalSoloRankGames = soloRankWin + soloRankLosses;

  // const totalFreeRankGames = freeRankWin + freeRankLosses;
  // console.log(totalSoloRankGames);

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
          rankEntry={{ soloRankEntry, freeRankEntry }}
        />

        {/* <S.RankBox>
          <TierImageComponent
            rank={soloRank}
            tier={soloRankTier}
            point={soloLeaguePoint}
            win={soloRankWin}
            loss={soloRankLosses}
            game={totalSoloRankGames}
            type="솔로"
          />
          <TierImageComponent
            rank={freeRank}
            tier={freeRankTier}
            point={freeLeaguePoint}
            win={freeRankWin}
            loss={freeRankLosses}
            game={totalFreeRankGames}
            type="자유"
          />
        </S.RankBox> */}
        <Records matchData={matchData} summonerName={summonerName} />
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
  background-color: #eee;
`;

// S.RankBox = styled.div`
//   width: 100%;
//   height: 180px;
//   display: flex;
//   justify-content: space-around;
//   background-color: yellow;
//   padding: 20px;
// `;
