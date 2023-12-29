package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "summoner",cascade = CascadeType.ALL)
    private List<LeagueEntry> leagueEntries = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "summoner",cascade = CascadeType.ALL)
    private List<MatchData> matchData = new ArrayList<>();

    public void putLeagueData(LeagueEntry leagueEntry){
        this.leagueEntries.add(leagueEntry);
    }

    public void putGameData(MatchData matchData){
        this.matchData.add(matchData);
    }


}
