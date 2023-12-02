package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameInfoDto {

    private Long gameCreation;
    private Long gameDuration;
    private Long gameEndTimestamp;
    private Long gameId;
    private String gameMode;
    private String gameName;
    private Long gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private List<ParticipantDto> participants;

//    private int assists;
//    private int baronKills;
//    private int bountyLevel;
//    private int champExperience;
//    private int champLevel;
//    private int championId;
//    private String championName;
}
