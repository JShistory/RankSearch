package com.example.Foods.riotApi.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LeagueEntryDTO {

    private String leagueId;
    private String summonerId;
    private String summonerName;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    //    private boolean hotStreak;
//    private boolean veteran;
//    private boolean freshBlood;
//    private boolean inactive;
//    private boolean miniSeries;
    @Builder
    public LeagueEntryDTO(String leagueId, String queueType, String tier, String rank, int leaguePoints, int wins,
                          int losses) {
        this.leagueId = leagueId;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
    }
}
