package com.example.Foods.riotV2.dto;

import com.example.Foods.riotV2.domain.LeagueEntryV2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LeagueEntryResponseDTO {
    private Long id;
    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    @Builder
    public LeagueEntryResponseDTO(LeagueEntryV2 entity) {
        this.id = entity.getId();
        this.leagueId = entity.getLeagueId();
        this.queueType = entity.getQueueType();
        this.tier = entity.getTier();
        this.rank = entity.getRank();
        this.leaguePoints = entity.getLeaguePoints();
        this.wins = entity.getWins();
        this.losses = entity.getLosses();
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
