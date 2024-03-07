package com.example.Foods.riotV2.dto;

import com.example.Foods.riotV2.domain.GameInfoV2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameInfoSaveRequestDTO {
    private String matchId;

    @Builder
    public GameInfoSaveRequestDTO(String matchId){
        this.matchId = matchId;
    }

    public GameInfoV2 toEntity(){
        return GameInfoV2.builder()
                .matchId(matchId)
                .build();
    }
}
