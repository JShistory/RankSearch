package com.example.Foods.riotV2.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SummonerApiRequestDTO {
    private String accountId;
    private int profileIconId;
    private Long revisionDate;
    private Long summonerLevel;
    private String entrypedId;

    @Builder
    public SummonerApiRequestDTO(String accountId, int profileIconId, Long revisionDate, Long summonerLevel, String entrypedId){
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
        this.entrypedId = entrypedId;
    }


}
