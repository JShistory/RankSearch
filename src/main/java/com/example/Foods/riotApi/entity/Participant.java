package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int assists;
    private int deaths;
    private int kills;

    private String championName;
    private int champLevel;
    private int championId;

    private int teamId;
    private int qCount;
    private int wCount;
    private int eCount;
    private int rCount;
    @Enumerated(EnumType.STRING)
    private SpellType dSpell;
    @Enumerated(EnumType.STRING)
    private SpellType fSpell;
    private int dCount;
    private int fCount;
    //아이템
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private String summonerName;
    //골드 획득량
    private int goldEarned;
    //골드 소비량
    private int goldSpent;
    private int wardsKilled;
    private int wardsPlaced;
    private boolean win;
    private int totalMinionsKilled;
    private int visionScore;

    private int statRuneId1;
    private int statRuneId2;
    private int statRuneId3;

    private int mainRuneId;
    private int mainRuneId1;
    private int mainRuneId2;
    private int mainRuneId3;
    private int mainRuneId4;

    private int subRuneId;
    private int subRuneId1;
    private int subRuneId2;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gameInfo_id")
    @JsonBackReference
    private GameInfo gameInfo;

    public Participant() {

    }
}
