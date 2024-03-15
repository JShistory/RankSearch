package com.example.Foods.riotV2.service;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.Participant;
import com.example.Foods.riotApi.entity.SpellType;
import com.example.Foods.riotV2.dto.GameInfoSaveRequestDTO;
import com.example.Foods.riotV2.dto.GameUserSaveRequestDTO;
import com.example.Foods.riotV2.repository.GameUserV2Repository;
import com.example.Foods.riotV2.utils.HttpConnect;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GameUserV2Service {
    @Value("${riot.api.key}")
    private String riotApiKey;
    @Value("${riot.api.matchDataUrl}")
    private String matchUrl;

    private final GameUserV2Repository gameUserV2Repository;
    private final HttpConnect httpConnect;

    public List<GameUserSaveRequestDTO> loadGameUserData(String matchId) {
        String apiUrl = matchUrl + matchId + "?&api_key=" + riotApiKey;
        String result = httpConnect.connectUrl(apiUrl);
        List<GameUserSaveRequestDTO> saveRequestDTO = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject info = (JSONObject) jsonObject.get("info");
            JSONArray jsonArray = (JSONArray) info.get("participants");

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject participantsData = (JSONObject) jsonArray.get(i);
                int assists = Integer.valueOf(participantsData.get("assists").toString());
                int kills = Integer.valueOf(participantsData.get("kills").toString());
                int deaths = Integer.valueOf(participantsData.get("deaths").toString());
                int champLevel = Integer.valueOf(participantsData.get("champLevel").toString());
                int championId = Integer.valueOf(participantsData.get("championId").toString());
                int teamId = Integer.valueOf(participantsData.get("teamId").toString());
                int qCount = Integer.valueOf(participantsData.get("spell1Casts").toString());
                int wCount = Integer.valueOf(participantsData.get("spell2Casts").toString());
                int eCount = Integer.valueOf(participantsData.get("spell3Casts").toString());
                int rCount = Integer.valueOf(participantsData.get("spell4Casts").toString());
                int dCount = Integer.valueOf(participantsData.get("summoner1Casts").toString());
                int fCount = Integer.valueOf(participantsData.get("summoner2Casts").toString());
                int dSpell = Integer.valueOf(participantsData.get("summoner1Id").toString());
                int fSpell = Integer.valueOf(participantsData.get("summoner2Id").toString());
                int item0 = Integer.valueOf(participantsData.get("item0").toString());
                int item1 = Integer.valueOf(participantsData.get("item1").toString());
                int item2 = Integer.valueOf(participantsData.get("item2").toString());
                int item3 = Integer.valueOf(participantsData.get("item3").toString());
                int item4 = Integer.valueOf(participantsData.get("item4").toString());
                int item5 = Integer.valueOf(participantsData.get("item5").toString());
                int item6 = Integer.valueOf(participantsData.get("item6").toString());

                int goldEarned = Integer.valueOf(participantsData.get("goldEarned").toString());
                int wardsKilled = Integer.valueOf(participantsData.get("wardsKilled").toString());
                int wardsPlaced = Integer.valueOf(participantsData.get("wardsPlaced").toString());
                int totalMinionsKilled = Integer.valueOf(participantsData.get("totalMinionsKilled").toString());
                int visionScore = Integer.valueOf(participantsData.get("visionScore").toString());
                boolean win = (boolean) participantsData.get("win");
                String summonerName = participantsData.get("summonerName").toString();
                String championName = participantsData.get("championName").toString();
                SpellType dSpellType;
                SpellType fSpellType;
                try {
                    dSpellType = SpellType.fromNumericValue(dSpell);
                    fSpellType = SpellType.fromNumericValue(fSpell);
                } catch (IllegalArgumentException e) {
                    dSpellType = SpellType.Null;
                    fSpellType = SpellType.Null;
                }

                JSONObject rune = (JSONObject) participantsData.get("perks");
                JSONObject statRune = (JSONObject) rune.get("statPerks");
                JSONArray runeList = (JSONArray) rune.get("styles");
                JSONObject mainRune = (JSONObject) runeList.get(0);
                JSONArray mainRuneList = (JSONArray) mainRune.get("selections");
                JSONObject data1 = (JSONObject) mainRuneList.get(0);
                JSONObject data2 = (JSONObject) mainRuneList.get(1);
                JSONObject data3 = (JSONObject) mainRuneList.get(2);
                JSONObject data4 = (JSONObject) mainRuneList.get(3);
                int mainRuneId1 = Integer.valueOf(data1.get("perk").toString());
                int mainRuneId2 = Integer.valueOf(data2.get("perk").toString());
                int mainRuneId3 = Integer.valueOf(data3.get("perk").toString());
                int mainRuneId4 = Integer.valueOf(data4.get("perk").toString());
                int statRuneId1 = Integer.valueOf(statRune.get("defense").toString());
                int statRuneId2 = Integer.valueOf(statRune.get("flex").toString());
                int statRuneId3 = Integer.valueOf(statRune.get("offense").toString());

                JSONObject subRune = (JSONObject) runeList.get(1);
                JSONArray subRuneList = (JSONArray) subRune.get("selections");
                JSONObject data5 = (JSONObject) subRuneList.get(0);
                JSONObject data6 = (JSONObject) subRuneList.get(1);
                int subRuneId1 = Integer.valueOf(data5.get("perk").toString());
                int subRuneId2 = Integer.valueOf(data6.get("perk").toString());

                int mainRuneId = Integer.valueOf(mainRune.get("style").toString());
                int subRuneId = Integer.valueOf(subRune.get("style").toString());

                GameUserSaveRequestDTO requestDTO = GameUserSaveRequestDTO.builder()
                        .item0(item0)
                        .item1(item1)
                        .item2(item2)
                        .item3(item3)
                        .item4(item4)
                        .item5(item5)
                        .item6(item6)
                        .assists(assists)
                        .kills(kills)
                        .deaths(deaths)
                        .championId(championId)
                        .champLevel(champLevel)
                        .championName(championName)
                        .goldEarned(goldEarned)
                        .dSpell(dSpellType)
                        .fSpell(fSpellType)
                        .win(win)
                        .teamId(teamId)
                        .goldSpent(goldEarned)
                        .summonerName(summonerName)
                        .visionScore(visionScore)
                        .totalMinionsKilled(totalMinionsKilled)
                        .wardsKilled(wardsKilled)
                        .wardsPlaced(wardsPlaced)
                        .mainRuneId(mainRuneId)
                        .subRuneId(subRuneId)
                        .mainRuneId1(mainRuneId1)
                        .mainRuneId2(mainRuneId2)
                        .mainRuneId3(mainRuneId3)
                        .mainRuneId4(mainRuneId4)
                        .subRuneId1(subRuneId1)
                        .subRuneId2(subRuneId2)
                        .statRuneId1(statRuneId1)
                        .statRuneId2(statRuneId2)
                        .statRuneId3(statRuneId3)
                        .build();

                saveRequestDTO.add(requestDTO);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return saveRequestDTO;
    }

//    @Transactional
//    public List<GameUserSaveRequestDTO> saveAllV1(List<GameUserSaveRequestDTO> requestDTO, GameInfo gameInfo){
//        List<Participant> newData = new ArrayList<>();
//        for(Participant data : participant){
//            data.setGameInfo(gameInfo);
//            newData.add(data);
//        }
//        List<Participant> participantList = participantRepository.saveAll(newData);
//        return participantList;
//    }

}
