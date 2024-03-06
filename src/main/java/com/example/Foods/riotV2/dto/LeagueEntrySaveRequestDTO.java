package com.example.Foods.riotV2.dto;

import com.example.Foods.riotV2.domain.LeagueEntryV2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LeagueEntrySaveRequestDTO {
    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    @Builder
    public LeagueEntrySaveRequestDTO(String leagueId, String queueType, String tier, String rank, int leaguePoints, int wins, int losses) {
        this.leagueId = leagueId;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
    }



    public LeagueEntryV2 toEntity(){
        return LeagueEntryV2.builder()
                .leagueId(leagueId)
                .queueType(queueType)
                .tier(tier)
                .rank(rank)
                .leaguePoints(leaguePoints)
                .wins(wins)
                .losses(losses)
                .build();
    }
}
