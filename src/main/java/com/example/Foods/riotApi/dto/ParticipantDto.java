package com.example.Foods.riotApi.dto;

import com.example.Foods.riotApi.entity.SpellType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;


//룬, 스펠, 아이템, 레벨, 킬, 데스, 어시, 구매한 와드 갯수, 골드, cs
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ParticipantDto {
    private int assists;
    private String championName;
    private int deaths;
    private int kills;
    private int champLevel;
    private int championId;
    private int teamId;
    private int qCount;
    private int wCount;
    private int eCount;
    private int rCount;
    private int dSpell;
    private int fSpell;
    //아이템
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private String summonerName;
    //골드 획득량
    private int goldEarned;
    //골드 소비량
    private int goldSpent;
    private int wardsKilled;
    private int wardsPlaced;
    private boolean win;
    private int totalMinionsKilled;
    private int visionScore;
    private SpellType spell;



}
