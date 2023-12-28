package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.repository.GameInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameInfoService {

    private final GameInfoRepository gameInfoRepository;

    @Transactional
    public Long saveGameInfo(GameInfo gameInfo){
        GameInfo save = gameInfoRepository.save(gameInfo);
        return save.getId();
    }

    public GameInfo findByGame(Long gameId){
        return gameInfoRepository.findByGameIdIs(gameId);
    }
}
