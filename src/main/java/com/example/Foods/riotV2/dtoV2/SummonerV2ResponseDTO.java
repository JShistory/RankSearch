package com.example.Foods.riotV2.dtoV2;

import com.example.Foods.riotV2.entityV2.SummonerV2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SummonerV2ResponseDTO {
    private Long id;
    private String accountId;
    private int profileIconId;
    private Long revisionDate;
    private String name;
    private String puuid;
    private String tag;
    private String findName;
    private Long summonerLevel;

    @Builder
    public SummonerV2ResponseDTO(SummonerV2 entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.puuid = entity.getPuuid();
        this.tag = entity.getTag();
        this.findName = entity.getFindName();
        this.accountId = entity.getAccountId();
        this.profileIconId = entity.getProfileIconId();
        this.revisionDate = entity.getRevisionDate();
        this.summonerLevel = entity.getSummonerLevel();
    }

    public SummonerV2 toEntity(){
        return SummonerV2.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .puuid(puuid)
                .tag(tag)
                .findName(findName)
                .summonerLevel(summonerLevel)
                .build();
    }
}
