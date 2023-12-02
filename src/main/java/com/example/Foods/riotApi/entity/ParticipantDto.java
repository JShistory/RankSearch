package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantDto {
    private int assists;
    private int baronKills;
    private int bountyLevel;
    private int champExperience;
    private int champLevel;
    private int championId;
    private String championName;
    private int championTransform;
    private int deaths;
    private int kills;

}
