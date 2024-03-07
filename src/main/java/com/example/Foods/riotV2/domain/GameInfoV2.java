package com.example.Foods.riotV2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class GameInfoV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAMEINFOV2_ID")
    private Long id;
    private String matchId;
    @ManyToOne(fetch = FetchType.LAZY)
    private SummonerV2 summonerV2;
    @OneToMany(mappedBy = "gameInfoV2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GameUserV2> gameUserV2List = new ArrayList<>();


    public void addGameUser(GameUserV2 gameUser){
        this.gameUserV2List.add(gameUser);
        gameUser.setGameInfo(this);
    }

    public void setSummonerV2(SummonerV2 summonerV2) {
        this.summonerV2 = summonerV2;
    }

    public GameInfoV2() {

    }

    @Builder
    public GameInfoV2(String matchId) {
        this.matchId = matchId;
    }
}
