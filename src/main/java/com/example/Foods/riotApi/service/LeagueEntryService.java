package com.example.Foods.riotApi.service;


import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.dto.LeagueEntryDTO;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.repository.LeagueEntryRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LeagueEntryService {
    private final LeagueEntryRepository leagueEntryRepository;

    @Transactional
    public Long saveRank(Summoner summoner, LeagueEntryDTO leagueEntryDto) {
        LeagueEntry entry = leagueEntryRepository.findByLeagueId(leagueEntryDto.getLeagueId());
        if (entry != null) {
            return entry.getId();
        }

        LeagueEntry leagueEntry = LeagueEntry.builder()
                .leaguePoints(leagueEntryDto.getLeaguePoints())
                .rank(leagueEntryDto.getRank())
                .tier(leagueEntryDto.getTier())
                .losses(leagueEntryDto.getLosses())
                .leagueId(leagueEntryDto.getLeagueId())
                .queueType(leagueEntryDto.getQueueType())
                .wins(leagueEntryDto.getWins())
                .build();
        leagueEntry.setSummoner(summoner);
        leagueEntryRepository.save(leagueEntry);
        return leagueEntry.getId();
    }
    @Transactional(readOnly = true)
    public LeagueEntry findById(Long id) {
        return leagueEntryRepository.findById(id).get();
    }
    @Transactional(readOnly = true)
    public List<LeagueEntry> findBySummoner(Summoner summoner) {
        return leagueEntryRepository.findBySummoner(summoner);
    }
    @Transactional(readOnly = true)
    public List<LeagueEntry> findAll() {
        return leagueEntryRepository.findAll();
    }

}
