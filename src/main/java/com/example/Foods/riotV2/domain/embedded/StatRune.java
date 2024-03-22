package com.example.Foods.riotV2.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class StatRune {
    private int statRuneId1;
    private int statRuneId2;
    private int statRuneId3;

    @Builder
    public StatRune(int statRuneId1, int statRuneId2, int statRuneId3) {
        this.statRuneId1 = statRuneId1;
        this.statRuneId2 = statRuneId2;
        this.statRuneId3 = statRuneId3;
    }

    public StatRune() {

    }
}
