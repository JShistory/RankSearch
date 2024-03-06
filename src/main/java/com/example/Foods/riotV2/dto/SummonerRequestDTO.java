package com.example.Foods.riotV2.dto;

import com.example.Foods.riotV2.domain.SummonerV2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SummonerRequestDTO {
    private String accountId;
    private String encryptedId;
    private int profileIconId;
    private Long revisionDate;
    private String name;
    private String puuid;
    private String tag;
    private String findName;
    private Long summonerLevel;

    @Builder
    public SummonerRequestDTO(String name, String puuid, String encryptedId, String tag, String findName, String accountId, int profileIconId, Long revisionDate, Long summonerLevel) {
        this.name = name;
        this.puuid = puuid;
        this.tag = tag;
        this.findName = findName;
        this.encryptedId = encryptedId;
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }
    @Builder
    public SummonerRequestDTO(SummonerV2 entity){
        this.name = entity.getName();
        this.puuid = entity.getPuuid();
        this.tag = entity.getTag();
        this.findName = entity.getFindName();
        this.accountId = entity.getAccountId();
        this.profileIconId = entity.getProfileIconId();
        this.revisionDate = entity.getRevisionDate();
        this.summonerLevel = entity.getSummonerLevel();
    }

    public SummonerV2 toEntity() {
        return SummonerV2.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .encryptedId(encryptedId)
                .name(name)
                .puuid(puuid)
                .tag(tag)
                .findName(findName)
                .summonerLevel(summonerLevel)
                .build();
    }
}
