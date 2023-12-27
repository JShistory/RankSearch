package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.MatchData;
import com.example.Foods.riotApi.entity.MetaData;
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
        MatchData matchData = MatchData.builder()
                .gameInfo(gameInfo)
                .metaData(metaData)
                .summoner(summoner)
                .build();

        MatchData save = matchRepository.save(matchData);
        return save.getId();
    }

    public MatchData findById(Long id){
        return matchRepository.findById(id).get();
    }

    public List<MatchData> findBySummoner(Summoner summoner){
        return matchRepository.findBySummoner(summoner);
    }
}
