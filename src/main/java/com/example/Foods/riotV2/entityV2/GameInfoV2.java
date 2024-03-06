package com.example.Foods.riotV2.entityV2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class GameInfoV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

}
