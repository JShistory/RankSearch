package com.example.Foods.riotV2.dtoV2;

import com.example.Foods.riotV2.entityV2.SummonerV2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiV2RequestDTO {
    private String accountId;
    private int profileIconId;
    private Long revisionDate;
    private Long summonerLevel;

    @Builder
    public ApiV2RequestDTO(String accountId, int profileIconId, Long revisionDate, Long summonerLevel){
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }


}
