package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.GameInfoDto;
import com.example.Foods.riotApi.entity.Match;
import com.example.Foods.riotApi.entity.MatchDTO;
import com.example.Foods.riotApi.entity.MetaData;
import com.example.Foods.riotApi.entity.MetaDataDTO;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.repository.MatchRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    @Transactional
    public Long saveMatch(Summoner summoner, GameInfo gameInfo, MetaData metaData) {
        Match match = Match.builder()
                .gameInfo(gameInfo)
                .metaData(metaData)
                .summoner(summoner)
                .build();

        Match save = matchRepository.save(match);
        return save.getId();
    }

    public Match findById(Long id){
        return matchRepository.findById(id).get();
    }

    public List<Match> findBySummoner(Summoner summoner){
        return matchRepository.findBySummoner(summoner);
    }
}
