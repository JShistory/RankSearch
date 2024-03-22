package com.example.Foods.riotV2.dto;

import com.example.Foods.riotApi.entity.SpellType;
import com.example.Foods.riotV2.domain.GameUserV2;
import com.example.Foods.riotV2.domain.embedded.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameUserResponseDTO {
    private int assists;
    private int deaths;
    private int kills;

    private String championName;
    private int champLevel;
    private int championId;

    private int teamId;
    @Enumerated(EnumType.STRING)
    private SpellType dSpell;
    @Enumerated(EnumType.STRING)
    private SpellType fSpell;
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

    private int statRuneId1;
    private int statRuneId2;
    private int statRuneId3;

    private int mainRuneId;
    private int mainRuneId1;
    private int mainRuneId2;
    private int mainRuneId3;
    private int mainRuneId4;

    private int subRuneId;
    private int subRuneId1;
    private int subRuneId2;


    @Builder
    public GameUserResponseDTO(GameUserV2 gameUserV2){
        checkKDA(gameUserV2.getKda());
        checkChampion(gameUserV2.getChampion());
        this.teamId = gameUserV2.getTeamId();
        this.dSpell = gameUserV2.getDSpell();
        this.fSpell = gameUserV2.getFSpell();
        checkItem(gameUserV2.getItem());
        checkGold(gameUserV2.getGold());
        checkStatRune(gameUserV2.getStatRune());
        checkMainRune(gameUserV2.getMainRune());
        checkSubRune(gameUserV2.getSubRune());


    }

    public GameUserV2 toEntity() {
        return GameUserV2.builder()
                .dSpell(dSpell)
                .fSpell(fSpell)
                .summonerName(summonerName)
                .mainRune(new MainRune(mainRuneId, mainRuneId1, mainRuneId2, mainRuneId3, mainRuneId4))
                .kda(new KDA(assists, deaths, kills))
                .item(new Item(item0, item1, item2, item3, item4, item5, item6))
                .gold(new Gold(goldEarned, goldSpent, wardsKilled, wardsPlaced, totalMinionsKilled, visionScore))
                .champion(new Champion(championName, champLevel, championId))
                .subRune(new SubRune(subRuneId, subRuneId1, subRuneId2))
                .statRune(new StatRune(statRuneId1, statRuneId2, statRuneId3))
                .win(win)
                .teamId(teamId)
                .build();
    }
    private void checkKDA(KDA kda){
        this.assists = kda.getAssists();
        this.deaths = kda.getDeaths();
        this.kills = kda.getKills();
    }
    private void checkGold(Gold gold){
        this.goldEarned = gold.getGoldEarned();
        this.goldSpent = gold.getGoldSpent();
        this.wardsKilled = gold.getWardsKilled();
        this.wardsPlaced = gold.getWardsPlaced();
        this.visionScore = gold.getVisionScore();
        this.totalMinionsKilled = gold.getTotalMinionsKilled();
    }
    private void checkItem(Item item){
        this.item0 = item.getItem0();
        this.item1 = item.getItem1();
        this.item2 = item.getItem2();
        this.item3 = item.getItem3();
        this.item4 = item.getItem4();
        this.item5 = item.getItem5();
        this.item6 = item.getItem6();
    }
    private void checkMainRune(MainRune mainRune){
        this.mainRuneId = mainRune.getMainRuneId();
        this.mainRuneId1 = mainRune.getMainRuneId1();
        this.mainRuneId2 = mainRune.getMainRuneId2();
        this.mainRuneId3 = mainRune.getMainRuneId3();
        this.mainRuneId4 = mainRune.getMainRuneId4();
    }
    private void checkStatRune(StatRune statRune){
        this.statRuneId1 = statRune.getStatRuneId1();
        this.statRuneId2 = statRune.getStatRuneId2();
        this.statRuneId3 = statRune.getStatRuneId3();
    }
    private void checkSubRune(SubRune subRune){
        this.subRuneId = subRune.getSubRuneId();
        this.subRuneId1 = subRune.getSubRuneId1();
        this.subRuneId2 = subRune.getSubRuneId2();
    }

    private void checkChampion(Champion champion){
        this.championId = champion.getChampionId();
        this.championName = champion.getChampionName();
        this.champLevel = champion.getChampLevel();
    }

}

