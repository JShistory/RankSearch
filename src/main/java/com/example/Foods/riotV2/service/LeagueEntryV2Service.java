package com.example.Foods.riotV2.service;

import com.example.Foods.riotV2.domain.LeagueEntryV2;
import com.example.Foods.riotV2.dto.LeagueEntryResponseDTO;
import com.example.Foods.riotV2.dto.LeagueEntrySaveRequestDTO;
import com.example.Foods.riotV2.repository.LeagueEntryV2Repository;
import com.example.Foods.riotV2.utils.HttpConnect;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class LeagueEntryV2Service {
    private final LeagueEntryV2Repository leagueEntryV2Repository;
    private final HttpConnect httpConnect;

    @Value("${riot.api.rankUrl}")
    private String rankUrl;
    @Value("${riot.api.key}")
    private String riotApiKey;

    @Transactional
    public Long save(LeagueEntrySaveRequestDTO requestDTO) {
        return leagueEntryV2Repository.save(requestDTO.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public LeagueEntryResponseDTO findById(Long id){
        LeagueEntryV2 leagueEntry = leagueEntryV2Repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾으시는 랭크 정보가 없습니다."));

        return new LeagueEntryResponseDTO(leagueEntry);

    }

    //리그(솔로랭크, 자유랭크)정보 데이터 추출
    public HashMap<String, LeagueEntrySaveRequestDTO> loadLeagueEntry(String encryptedId) {
        String apiUrl = rankUrl + encryptedId + "?api_key=" + riotApiKey;
        String result = httpConnect.connectUrl(apiUrl);
        int jsonIndex = 0;
        LeagueEntrySaveRequestDTO soloLeagueEntity = null;
        LeagueEntrySaveRequestDTO flexLeagueEntity = null;
        HashMap<String, LeagueEntrySaveRequestDTO> leagueEntryMap = new HashMap<>();
        try {
            String leagueId = null;
            String summonerId = null;
            String summonerName = null;
            String queueType = null;
            String tier = null;
            String rank = null;
            int leaguePoints = 0;
            int wins = 0;
            int losses = 0;
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(result);
            if (result.contains("RANKED_SOLO_5x5")) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(jsonIndex);
                jsonIndex++;
                leagueId = (String) jsonObject.get("leagueId");
                summonerId = (String) jsonObject.get("summonerId");
                queueType = "솔로랭크";
                tier = (String) jsonObject.get("tier");
                rank = (String) jsonObject.get("rank");
                leaguePoints = Integer.valueOf(jsonObject.get("leaguePoints").toString());
                wins = Integer.valueOf(jsonObject.get("wins").toString());
                losses = Integer.valueOf(jsonObject.get("losses").toString());
                soloLeagueEntity = LeagueEntrySaveRequestDTO.builder()
                        .leagueId(leagueId)
                        .queueType(queueType)
                        .tier(tier)
                        .rank(rank)
                        .leaguePoints(leaguePoints)
                        .wins(wins)
                        .losses(losses)
                        .build();

            }
            if (result.contains("RANKED_FLEX_SR")) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(jsonIndex);
                leagueId = (String) jsonObject.get("leagueId");
                summonerId = (String) jsonObject.get("summonerId");
                queueType = "자유랭크";
                tier = (String) jsonObject.get("tier");
                rank = (String) jsonObject.get("rank");
                leaguePoints = Integer.valueOf(jsonObject.get("leaguePoints").toString());
                wins = Integer.valueOf(jsonObject.get("wins").toString());
                losses = Integer.valueOf(jsonObject.get("losses").toString());
                flexLeagueEntity = LeagueEntrySaveRequestDTO.builder()
                        .leagueId(leagueId)
                        .queueType(queueType)
                        .tier(tier)
                        .rank(rank)
                        .leaguePoints(leaguePoints)
                        .wins(wins)
                        .losses(losses)
                        .build();
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (flexLeagueEntity != null) {
            leagueEntryMap.put("자유랭크", flexLeagueEntity);
        }
        if (soloLeagueEntity != null) {
            leagueEntryMap.put("솔로랭크", soloLeagueEntity);
        }

        return leagueEntryMap;
    }


}
