const API_URL = process.env.NEXT_PUBLIC_API_URL;

const RIOT_VERSION = process.env.NEXT_PUBLIC_RIOT_VERSION;

const IMAGE_URL = process.env.NEXT_PUBLIC_IMAGE_URL;

export const BASE_URL = API_URL;

export const PROFILE_ICON_URL = (id) =>
  `${IMAGE_URL}${RIOT_VERSION}/img/profileicon/${id}.png`;

export const CHAMPION_IMAGE_URL = (id) =>
  `${IMAGE_URL}${RIOT_VERSION}/img/champion/${id}.png`;

export const ITEM_IMAGE_URL = (id) =>
  `${IMAGE_URL}${RIOT_VERSION}/img/item/${id}.png`;

export const SPELL_IMAGE_ID = (id) =>
  `${IMAGE_URL}${RIOT_VERSION}/img/spell/${id}.png`;

// https://ddragon.leagueoflegends.com/cdn/13.9.1/img/profileicon/22.png
// https://ddragon.leagueoflegends.com/cdn/12.12.1/img/champion/Aatrox.png
// https://ddragon.leagueoflegends.com/cdn/12.12.1/img/spell/SummonerBarrier.png
// https://ddragon.leagueoflegends.com/cdn/13.9.1/img/item/1103.png
