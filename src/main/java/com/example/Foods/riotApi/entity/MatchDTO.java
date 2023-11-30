package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDTO {
    //metaData가 오류뜸
//    private MetaDataDTO metaData;
    private GameInfoDto info;
}
