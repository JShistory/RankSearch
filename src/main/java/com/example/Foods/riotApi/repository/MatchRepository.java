package com.example.Foods.riotApi.repository;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.MatchData;
import com.example.Foods.riotApi.entity.MetaData;
import com.example.Foods.riotApi.entity.Summoner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchData, Long> {
    List<MatchData> findBySummoner(Summoner summoner);
    MatchData findByGameInfo(GameInfo gameInfo);
}
