package com.example.Foods.riotV2.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class Gold {
    private int goldEarned;
    //골드 소비량
    private int goldSpent;
    private int wardsKilled;
    private int wardsPlaced;
    private int totalMinionsKilled;
    private int visionScore;
    @Builder
    public Gold(int goldEarned, int goldSpent, int wardsKilled, int wardsPlaced, int totalMinionsKilled, int visionScore) {
        this.goldEarned = goldEarned;
        this.goldSpent = goldSpent;
        this.wardsKilled = wardsKilled;
        this.wardsPlaced = wardsPlaced;
        this.totalMinionsKilled = totalMinionsKilled;
        this.visionScore = visionScore;
    }

    public Gold() {

    }
}
