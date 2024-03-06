package com.example.Foods.riotV2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class LeagueEntryV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String leagueId;
    private String queueType;
    private String tier;
    @Column(name = "`rank`")
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    @ManyToOne(fetch = FetchType.LAZY)
    private SummonerV2 summonerV2;

    @Builder
    public LeagueEntryV2(String leagueId, String queueType, String tier, String rank, int leaguePoints, int wins, int losses) {
        this.leagueId = leagueId;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
    }

    public LeagueEntryV2() {

    }

    public void setSummonerV2(SummonerV2 summonerV2){
        this.summonerV2 = summonerV2;
    }
}
