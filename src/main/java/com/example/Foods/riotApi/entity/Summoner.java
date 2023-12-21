package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Data;

@Entity
@Data
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

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> gameInfo;


}
