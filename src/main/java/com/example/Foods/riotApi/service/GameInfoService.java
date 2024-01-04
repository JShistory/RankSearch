package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.Participant;
import com.example.Foods.riotApi.repository.GameInfoRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameInfoService {

    private final GameInfoRepository gameInfoRepository;

    @Transactional
    public Long saveGameInfo(GameInfo gameInfo){
        GameInfo save = gameInfoRepository.save(gameInfo);
        return save.getId();
    }
    @Transactional(readOnly = true)
    public GameInfo findByGame(Long gameId){
        return gameInfoRepository.findByGameIdIs(gameId);
    }

    @Transactional
    public void saveAll(List<Participant> participants, GameInfo gameInfo){
        for(Participant data : participants){
            gameInfo.putParticipants(data);
        }

    }
}
