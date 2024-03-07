package com.example.Foods.riotV2.service;

import com.example.Foods.riotV2.dto.GameUserSaveRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameUserV2ServiceTest {
    @Autowired
    GameInfoV2Service gameInfoV2Service;
    @Autowired
    GameUserV2Service gameUserV2Service;

    @Test
    public void game_user_API_검증(){
        String matchId = "KR_6903474967";
        List<GameUserSaveRequestDTO> requestDTO = gameUserV2Service.loadGameUserData(matchId);


        for(GameUserSaveRequestDTO data : requestDTO){
            System.out.println(data.getChampionName());
        }
    }
}