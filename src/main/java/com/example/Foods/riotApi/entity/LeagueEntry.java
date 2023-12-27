package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;


@Data
@Entity
public class LeagueEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String leagueId;
    private String queueType;
    private String tier;
    @Column(name = "`rank`")
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;

//    @JsonIgnore
//    private boolean miniSeries;

    @ManyToOne
    @JoinColumn(name = "summoner_dataId")
    @JsonBackReference
    private Summoner summoner;

    @Builder
    public LeagueEntry(String leagueId, String queueType, String tier, String rank, int leaguePoints, int wins, int losses) {
        this.leagueId = leagueId;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
    }

    public LeagueEntry() {

    }
}
