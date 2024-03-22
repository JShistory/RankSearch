package com.example.Foods.riotV2.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class Item {
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    @Builder
    public Item(int item0, int item1, int item2, int item3, int item4, int item5, int item6) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
    }

    public Item() {

    }
}
