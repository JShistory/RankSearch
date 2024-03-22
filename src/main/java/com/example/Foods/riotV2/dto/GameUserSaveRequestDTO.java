package com.example.Foods.riotV2.dto;

import com.example.Foods.riotApi.entity.SpellType;
import com.example.Foods.riotV2.domain.GameUserV2;
import com.example.Foods.riotV2.domain.embedded.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameUserSaveRequestDTO {
    private int assists;
    private int deaths;
    private int kills;
    private String gameMode;
    private String gameName;
    private String gameType;
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
    public GameUserSaveRequestDTO(int assists, int deaths, int kills, String championName, String gameMode,String gameType, String gameName, int champLevel, int championId, int teamId, SpellType dSpell, SpellType fSpell, int item0, int item1, int item2, int item3, int item4, int item5, int item6, String summonerName, int goldEarned, int goldSpent, int wardsKilled, int wardsPlaced, boolean win, int totalMinionsKilled, int visionScore, int statRuneId1, int statRuneId2, int statRuneId3, int mainRuneId, int mainRuneId1, int mainRuneId2, int mainRuneId3, int mainRuneId4, int subRuneId, int subRuneId1, int subRuneId2) {
        this.assists = assists;
        this.deaths = deaths;
        this.kills = kills;
        this.gameMode = gameMode;
        this.gameType = gameType;
        this.gameName = gameName;
        this.championName = championName;
        this.champLevel = champLevel;
        this.championId = championId;
        this.teamId = teamId;
        this.dSpell = dSpell;
        this.fSpell = fSpell;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.summonerName = summonerName;
        this.goldEarned = goldEarned;
        this.goldSpent = goldSpent;
        this.wardsKilled = wardsKilled;
        this.wardsPlaced = wardsPlaced;
        this.win = win;
        this.totalMinionsKilled = totalMinionsKilled;
        this.visionScore = visionScore;
        this.statRuneId1 = statRuneId1;
        this.statRuneId2 = statRuneId2;
        this.statRuneId3 = statRuneId3;
        this.mainRuneId = mainRuneId;
        this.mainRuneId1 = mainRuneId1;
        this.mainRuneId2 = mainRuneId2;
        this.mainRuneId3 = mainRuneId3;
        this.mainRuneId4 = mainRuneId4;
        this.subRuneId = subRuneId;
        this.subRuneId1 = subRuneId1;
        this.subRuneId2 = subRuneId2;
    }

    public GameUserV2 toEntity() {
        return GameUserV2.builder()
                .dSpell(dSpell)
                .fSpell(fSpell)
                .gameMode(gameMode)
                .gameName(gameName)
                .gameType(gameType)
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

}
