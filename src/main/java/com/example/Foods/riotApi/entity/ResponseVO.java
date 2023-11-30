package com.example.Foods.riotApi.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {

    private int responseCode;
    private String responseMsg;
    private Object data;

}