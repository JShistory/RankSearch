package com.example.Foods.riotApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDataDTO {
    private String dataVersion;
    private String matchId;

    private List<String> participants;


    @Builder
    public MetaDataDTO(String dataVersion, String matchId, List<String> participants){
        this.dataVersion = dataVersion;
        this.matchId = matchId;
        this.participants = participants;
    }

    public MetaDataDTO(){

    }
}
