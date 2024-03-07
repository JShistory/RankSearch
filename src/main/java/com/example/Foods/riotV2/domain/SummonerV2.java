package com.example.Foods.riotV2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class SummonerV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountId;
    private String encryptedId;
    private int profileIconId;
    private Long revisionDate;
    private String name;
    private String puuid;
    private String tag;
    private String findName;
    private Long summonerLevel;

    @OneToMany(mappedBy = "summonerV2",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LeagueEntryV2> league = new ArrayList<>();

    @OneToMany(mappedBy = "summonerV2",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameInfoV2> gameInfo = new ArrayList<>();

    public void addLeagueEntry(LeagueEntryV2 LeagueEntry){
        this.league.add(LeagueEntry);
        LeagueEntry.setSummonerV2(this);
    }

    public void addGameInfo(GameInfoV2 gameInfoV2){
        this.gameInfo.add(gameInfoV2);
        gameInfoV2.setSummonerV2(this);
    }

    @Builder
    public SummonerV2(String accountId, int profileIconId, Long revisionDate, String name,
                      String puuid, String tag, String findName, Long summonerLevel, String encryptedId){
        this.accountId =accountId;
        this.encryptedId = encryptedId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.puuid = puuid;
        this.tag = tag;
        this.findName = findName;
        this.summonerLevel = summonerLevel;
    }
    public SummonerV2(){

    }
}
