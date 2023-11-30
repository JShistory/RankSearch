package com.example.Foods.riotApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Summoner {
    @Id
    private String accountId;
    private int profileIconId;
    private Long revisionDate;
    private String name;
    private String id;
    private String puuid;
    private Long summonerLevel;
}
