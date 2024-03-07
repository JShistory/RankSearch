package com.example.Foods.riotV2.dto;

import com.example.Foods.riotV2.domain.GameInfoV2;
import com.example.Foods.riotV2.domain.SummonerV2;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameInfoResponseDTO {
    private Long id;
    private String matchId;
    private SummonerV2 summonerV2;

    @Builder
    public GameInfoResponseDTO(GameInfoV2 entity){
        this.id = entity.getId();
        this.matchId = entity.getMatchId();
        this.summonerV2 = entity.getSummonerV2();
    }


}
