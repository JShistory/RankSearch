package com.example.Foods.riotApi.dto;

import com.example.Foods.riotApi.entity.Participant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Builder;
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
    private List<Participant> participants;

//    private List<ParticipantDto> participants;
//    private List<>

    //    private int assists;
//    private int baronKills;
//    private int bountyLevel;
//    private int champExperience;
//    private int champLevel;
//    private int championId;
//    private String championName;
    @Builder
    public GameInfoDto(Long gameCreation, Long gameDuration, Long gameEndTimestamp, Long gameId, String gameMode,
                    String gameName, Long gameStartTimestamp, String gameType
            , String gameVersion, int mapId, List<Participant> participants) {
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameEndTimestamp = gameEndTimestamp;
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.gameStartTimestamp = gameStartTimestamp;
        this.gameType = gameType;
        this.gameVersion = gameVersion;
        this.mapId = mapId;
        this.participants = participants;
    }

    public GameInfoDto() {

    }
}
