package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Summoner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long dataId;

    private String accountId;
    private int profileIconId;
    private Long revisionDate;
    @Column(name = "name")
    private String name;
    private String id;
    private String puuid;
    private Long summonerLevel;
    @Column(name = "tag")
    private String tag;
    private String prevId;
    private String findName;

    @JsonManagedReference
    @OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LeagueEntry> leagueEntries = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatchData> matchData = new ArrayList<>();

    public void putLeagueData(LeagueEntry leagueEntry){
        this.leagueEntries.add(leagueEntry);
        leagueEntry.setSummoner(this);
    }

    public void putGameData(MatchData matchData){
        this.matchData.add(matchData);
        matchData.setSummoner(this);
    }


}