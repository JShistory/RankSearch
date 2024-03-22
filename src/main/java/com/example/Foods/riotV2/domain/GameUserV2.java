package com.example.Foods.riotV2.domain;

import com.example.Foods.riotApi.entity.SpellType;
import com.example.Foods.riotV2.domain.embedded.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class GameUserV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int teamId;
    private String gameMode;
    @Embedded
    private KDA kda;
    @Embedded
    private Champion champion;
    @Embedded
    private Gold gold;
    @Embedded
    private Item item;
    @Embedded
    private MainRune mainRune;
    @Embedded
    private StatRune statRune;
    @Embedded
    private SubRune subRune;
    @Enumerated(EnumType.STRING)
    private SpellType dSpell;
    @Enumerated(EnumType.STRING)
    private SpellType fSpell;
    private String summonerName;
    private boolean win;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAMEINFOV2_ID")
    private GameInfoV2 gameInfoV2;

    public GameUserV2() {

    }

    public void setGameInfo(GameInfoV2 gameInfo) {
        this.gameInfoV2 = gameInfo;
    }

    @Builder
    public GameUserV2(int teamId, String gameMode, KDA kda, Champion champion, Gold gold, Item item, MainRune mainRune, StatRune statRune, SubRune subRune, SpellType dSpell, SpellType fSpell, String summonerName, boolean win, GameInfoV2 gameInfoV2) {
        this.teamId = teamId;
        this.gameMode = gameMode;
        this.kda = kda;
        this.champion = champion;
        this.gold = gold;
        this.item = item;
        this.mainRune = mainRune;
        this.statRune = statRune;
        this.subRune = subRune;
        this.dSpell = dSpell;
        this.fSpell = fSpell;
        this.summonerName = summonerName;
        this.win = win;
        this.gameInfoV2 = gameInfoV2;
    }
}
