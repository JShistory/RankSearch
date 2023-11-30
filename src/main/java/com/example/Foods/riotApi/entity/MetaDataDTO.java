package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDataDTO {
    private String dataVersion;
    private String matchId;
    private List<String> participants;
}
