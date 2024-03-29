package com.example.Foods.riotApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDTO {
    private MetaDataDTO metaData;
    private GameInfoDto info;
}
