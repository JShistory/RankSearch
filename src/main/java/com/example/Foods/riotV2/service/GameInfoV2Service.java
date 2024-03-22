package com.example.Foods.riotV2.service;


import com.example.Foods.riotV2.domain.GameInfoV2;
import com.example.Foods.riotV2.domain.SummonerV2;
import com.example.Foods.riotV2.dto.GameInfoSaveRequestDTO;
import com.example.Foods.riotV2.dto.GameUserSaveRequestDTO;
import com.example.Foods.riotV2.repository.GameInfoV2Repository;
import com.example.Foods.riotV2.utils.HttpConnect;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GameInfoV2Service {
    @Value("${riot.api.key}")
    private String riotApiKey;
    @Value("${riot.api.matchesUrl}")
    private String matchUrl;

    private final GameInfoV2Repository gameInfoV2Repository;
    private final GameUserV2Service gameUserV2Service;

    public Long save(GameInfoSaveRequestDTO requestDTO){
        return gameInfoV2Repository.save(requestDTO.toEntity()).getId();
    }

    public Long save(GameInfoV2 gameInfo){
        return gameInfoV2Repository.save(gameInfo).getId();
    }
    public List<GameInfoSaveRequestDTO> loadMatchData(String puuid, int start, int count) {
        String apiUrl = matchUrl + puuid + "/ids?" + "start=" + start + "&count=" + count + "&api_key=" + riotApiKey;


        ObjectMapper objectMapper = new ObjectMapper();
        List<String> gameInfo = new ArrayList<>();
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(apiUrl);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            gameInfo = objectMapper.readValue(entity.getContent(), List.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<GameInfoSaveRequestDTO> requestDTO = new ArrayList<>();
        for (String data : gameInfo) {
            requestDTO.add(new GameInfoSaveRequestDTO(data));
        }

        return requestDTO;
    }

    public void setGameSummoner(List<GameInfoSaveRequestDTO> requestDTO, SummonerV2 summoner){
        for (GameInfoSaveRequestDTO gameInfoDTO : requestDTO) {
            GameInfoV2 gameInfoEntity = gameInfoDTO.toEntity();
            summoner.addGameInfo(gameInfoEntity);
            //각 게임당 참가자들의 정보
            List<GameUserSaveRequestDTO> gameUserDTOList = gameUserV2Service.loadGameUserData(gameInfoDTO.getMatchId());
            for (GameUserSaveRequestDTO gameUserDTO : gameUserDTOList) {
                gameInfoEntity.addGameUser(gameUserDTO.toEntity());
            }
        }
    }
}
