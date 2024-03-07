package com.example.Foods.riotV2.dto;

import com.example.Foods.riotV2.domain.LeagueEntryV2;
import com.example.Foods.riotV2.domain.SummonerV2;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SummonerResponseDTO {
    private Long id;
    private int profileIconId;
    private String name;
    private String tag;
    private String findName;
    private Long summonerLevel;
    private LeagueEntryResponseDTO soloEntry;
    private LeagueEntryResponseDTO flexEntry;

    @Builder
    public SummonerResponseDTO(SummonerV2 entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.tag = entity.getTag();
        this.findName = entity.getFindName();
        this.profileIconId = entity.getProfileIconId();
        this.summonerLevel = entity.getSummonerLevel();
        checkLeague(entity.getLeague());
    }

    private void checkLeague(List<LeagueEntryV2> leagueEntry){
        for(LeagueEntryV2 data : leagueEntry){
            if(data.getQueueType().equals("솔로랭크")){
                this.soloEntry = new LeagueEntryResponseDTO(data);
            }
            else if(data.getQueueType().equals("자유랭크")){
                this.flexEntry = new LeagueEntryResponseDTO(data);
            }
        }
    }

    public SummonerV2 toEntity(){
        return SummonerV2.builder()
                .profileIconId(profileIconId)
                .name(name)
                .tag(tag)
                .findName(findName)
                .summonerLevel(summonerLevel)
                .build();
    }
}
