package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "summoner_dataId")
    @JsonBackReference
    private Summoner summoner;
    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "metaData_id")
    private MetaData metaData;
    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "gameInfo_id")
    private GameInfo gameInfo;

    @Builder
    public Match(Summoner summoner, GameInfo gameInfo, MetaData metaData){
        this.summoner = summoner;
        this.metaData = metaData;
        this.gameInfo = gameInfo;
    }

    public Match(){

    }
    public void putMetaData(MetaData metaData){
        this.metaData = metaData;
    }
    public void putGameInfo(GameInfo gameInfo){
        this.gameInfo = gameInfo;
    }
}
