package com.example.Foods.riotApi.repository;

import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.entity.Summoner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueEntryRepository extends JpaRepository<LeagueEntry, Long> {
    List<LeagueEntry> findBySummoner(Summoner summoner);
    LeagueEntry findByLeagueId(String leagueId);
}
