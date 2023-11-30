package com.example.Foods.riotApi.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Summoner {
    @Id
    private String accountId;
    private int profileIconId;
    private Long revisionDate;
    private String name;
    private String id;
    private String puuid;
    private Long summonerLevel;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> gameInfo;


}
