package com.example.Foods.riotV2.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class Champion {
    private String championName;
    private int champLevel;
    private int championId;
    @Builder
    public Champion(String championName, int champLevel, int championId) {
        this.championName = championName;
        this.champLevel = champLevel;
        this.championId = championId;
    }

    public Champion() {

    }
}
