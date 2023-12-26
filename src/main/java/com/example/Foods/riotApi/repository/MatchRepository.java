package com.example.Foods.riotApi.repository;

import com.example.Foods.riotApi.entity.Match;
import com.example.Foods.riotApi.entity.Summoner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findBySummoner(Summoner summoner);
}
