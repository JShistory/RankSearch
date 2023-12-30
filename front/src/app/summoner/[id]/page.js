"use client";

import TierImageComponent from "@/components/TierImageComponent";
import { PROFILE_ICON_URL } from "@/const/api";
import { useSummonuerQuery } from "@/hooks/useSummonerQuery";
import Image from "next/image";
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
  const tier = data?.result[0].leagueEntries[0].tier;
  const tag = data?.result[0]?.tag;
  const profileIconId = data?.result[0]?.profileIconId;

  const profileIcon = PROFILE_ICON_URL(profileIconId);

  console.log(tag);
  console.log(tier);
  console.log(summonerName);

  return (
    <S.Wrapper>
      <S.Container>
        :)
        <Image src={profileIcon} width={50} height={50} alt="profile" />
        <TierImageComponent rank={tier} />
        <div>{summonerName}</div>
        <div>{tag}</div>
        <div>{/* user profile */}</div>
        <div>{/* tier card */}</div>
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
