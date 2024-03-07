package com.example.Foods.riotV2.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.Builder;

@Embeddable
public class KDA {
    private int assists;
    private int deaths;
    private int kills;

    @Builder
    public KDA(int assists, int deaths, int kills) {
        this.assists = assists;
        this.deaths = deaths;
        this.kills = kills;
    }

    public KDA() {

    }
}
