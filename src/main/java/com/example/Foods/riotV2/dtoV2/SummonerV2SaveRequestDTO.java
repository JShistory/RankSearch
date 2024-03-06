package com.example.Foods.riotV2.dtoV2;

import com.example.Foods.riotV2.entityV2.SummonerV2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SummonerV2SaveRequestDTO {
    private String puuid;
    private String gameName;
    private String tag;

    @Builder
    public SummonerV2SaveRequestDTO(String puuid, String gameName, String tag){
        this.puuid = puuid;
        this.gameName = gameName;
        this.tag = tag;
    }

    public SummonerV2 toEntity(){
        return SummonerV2.builder()
                .puuid(puuid)
                .tag(tag)
                .name(gameName)
                .build();
    }
}
