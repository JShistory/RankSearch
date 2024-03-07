package com.example.Foods.riotV2.service;

import com.example.Foods.riotV2.dto.GameInfoSaveRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameInfoV2ServiceTest {
    @Autowired
    GameInfoV2Service gameInfoV2Service;

    @Test
    public void GameInfo_API_검증(){
        String puuid = "VWPFSu1yZo-kZqkbbKGSh67CZIcPzAtizSLmRxZhApWUg5e7_hFvvTFNPh4AHioEmKMDor0VXSQn0g";
        int start = 0;
        int count = 10;
        List<GameInfoSaveRequestDTO> requestDTO = gameInfoV2Service.loadMatchData(puuid, start, count);

        assertEquals(requestDTO.size(), 10);


        for(GameInfoSaveRequestDTO data : requestDTO){
            System.out.println(data.getMatchId());
        }
    }
}