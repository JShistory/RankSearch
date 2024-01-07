import Image from "next/image";
import React from "react";
import styled from "styled-components";

const ChampSpellRune = ({
  myChampion,
  champLevel,
  myDspell,
  myFspell,
  mainRuneImage,
  subRuneImage,
}) => {
  return (
    <S.ChampSpell>
      <S.Champ>
        <Image src={myChampion} width={60} height={60} alt="champion" />
        <S.ChampLevel>{champLevel}</S.ChampLevel>
      </S.Champ>
      <div>
        <Image src={myDspell} width={36} height={36} alt="d spell" />
        <Image src={myFspell} width={36} height={36} alt="f spell" />
      </div>
      <div>
        <Image src={mainRuneImage} width={36} height={36} alt="d spell" />
        <Image src={subRuneImage} width={36} height={36} alt="f spell" />
      </div>
    </S.ChampSpell>
  );
};

export default ChampSpellRune;

const S = {};

S.ChampSpell = styled.div`
  width: 100px;
  height: 100%;
  display: flex;
  gap: 6px;
  width: 150px;
`;

S.Champ = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

S.ChampLevel = styled.span`
  width: 100%;
  height: 100%;
  border: 1px solid #000;
  font-size: 14px;
  color: #fff;
  background: #000;
  text-align: center;
`;
