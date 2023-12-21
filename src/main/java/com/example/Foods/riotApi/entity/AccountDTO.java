package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AccountDTO {
    private String puuid;
    private String gameName;
    private String tagLine;

}
