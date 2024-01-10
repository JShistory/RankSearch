"use client";

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
