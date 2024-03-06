package com.example.Foods.riotV2.dtoV2;

import com.example.Foods.riotV2.entityV2.SummonerV2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SummonerV2RequestDTO {
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
    public SummonerV2RequestDTO(String name, String puuid, String encryptedId, String tag, String findName, String accountId, int profileIconId, Long revisionDate, Long summonerLevel) {
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
