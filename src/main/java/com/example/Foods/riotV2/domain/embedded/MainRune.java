package com.example.Foods.riotV2.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class MainRune {

    private int mainRuneId;
    private int mainRuneId1;
    private int mainRuneId2;
    private int mainRuneId3;
    private int mainRuneId4;

    @Builder
    public MainRune(int mainRuneId, int mainRuneId1, int mainRuneId2, int mainRuneId3, int mainRuneId4) {
        this.mainRuneId = mainRuneId;
        this.mainRuneId1 = mainRuneId1;
        this.mainRuneId2 = mainRuneId2;
        this.mainRuneId3 = mainRuneId3;
        this.mainRuneId4 = mainRuneId4;
    }

    public MainRune() {

    }
}
