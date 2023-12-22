package com.example.Foods.riotApi.service;


import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.entity.LeagueEntryDTO;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.repository.LeagueEntryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeagueEntryService {
    private final LeagueEntryRepository leagueEntryRepository;


    @Value("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/")
    private String rankUrl;

    @Value("${riot.api.key}")
    private String riotApiKey;

    @Transactional
    public Long saveRank(Summoner summoner, LeagueEntryDTO leagueEntryDto) {
        LeagueEntry entry = leagueEntryRepository.findByLeagueId(leagueEntryDto.getLeagueId());
        if(entry != null){
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

    public LeagueEntry findById(Long id){
        return leagueEntryRepository.findById(id).get();
    }

    public List<LeagueEntry> findBySummoner(Summoner summoner){
        return leagueEntryRepository.findBySummoner(summoner);
    }

    public List<LeagueEntry> findAll(){
        return leagueEntryRepository.findAll();
    }
}
