package com.example.Foods.riotV2.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class SubRune {
    private int subRuneId;
    private int subRuneId1;
    private int subRuneId2;
    @Builder
    public SubRune(int subRuneId, int subRuneId1, int subRuneId2) {
        this.subRuneId = subRuneId;
        this.subRuneId1 = subRuneId1;
        this.subRuneId2 = subRuneId2;
    }

    public SubRune() {

    }
}
