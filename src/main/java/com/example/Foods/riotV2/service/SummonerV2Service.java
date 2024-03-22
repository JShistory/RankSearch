package com.example.Foods.riotV2.service;

import com.example.Foods.riotV2.domain.SummonerV2;
import com.example.Foods.riotV2.dto.SummonerApiRequestDTO;
import com.example.Foods.riotV2.dto.SummonerResponseDTO;
import com.example.Foods.riotV2.dto.SummonerSaveRequestDTO;
import com.example.Foods.riotV2.repository.SummonerV2Repository;
import com.example.Foods.riotV2.utils.HttpConnect;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SummonerV2Service {
    private final SummonerV2Repository summonerV2Repository;
    private final HttpConnect httpConnect;

    @Value("${riot.api.summonerAndTagUrl}")
    private String summonerAndTagUrl;
    @Value("${riot.api.summonerPuuidUrl}")
    private String summonerPuuidUrl;
    @Value("${riot.api.key}")
    private String riotApiKey;

    @Transactional
    public Long save(SummonerSaveRequestDTO requestDTO){
        return summonerV2Repository.save(requestDTO.toEntity()).getId();
    }
    @Transactional
    public Long save(SummonerV2 summonerV2){
        return summonerV2Repository.save(summonerV2).getId();
    }

    @Transactional(readOnly = true)
    public SummonerResponseDTO findById(Long id){
        SummonerV2 entity = summonerV2Repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 소환사는 없습니다."));
        return new SummonerResponseDTO(entity);
    }
    @Transactional
    public Long delete(Long id){
        SummonerV2 summoner = summonerV2Repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 소환사는 없습니다."));
        summonerV2Repository.delete(summoner);
        return id;
    }

    /**
     * @param name 소환사 이름
     * @param tag 소환사 태그
     * 소환사의 이름과 태그를 통해 Puuid(고유값)을 알아낸 뒤 Puuid를 통해 많은 정보를 가져오기 위함
     * loadUserWithNameAndTag --> loadUserWithPuuid
     */

    public SummonerSaveRequestDTO loadUserWithNameAndTag(String name, String tag) {
        String apiUrl = summonerAndTagUrl + name + "/" + tag + "?api_key=" + riotApiKey;
        String result = httpConnect.connectUrl(apiUrl);


        String gameName = null;
        String puuid = null;
        String tagLine = null;

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            gameName = (String) jsonObject.get("gameName");
            puuid = (String) jsonObject.get("puuid");
            tagLine = (String) jsonObject.get("tagLine");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SummonerApiRequestDTO entity = loadUserWithPuuid(puuid);
        SummonerSaveRequestDTO requestDTO = SummonerSaveRequestDTO.builder()
                .name(gameName)
                .puuid(puuid)
                .tag(tagLine)
                .findName(gameName.toLowerCase().replaceAll(" ",""))
                .accountId(entity.getAccountId())
                .profileIconId(entity.getProfileIconId())
                .revisionDate(entity.getRevisionDate())
                .summonerLevel(entity.getSummonerLevel())
                .encryptedId(entity.getEntrypedId())
                .build();

        return requestDTO;
    }

    public SummonerApiRequestDTO loadUserWithPuuid(String puuid){
        String apiUrl = summonerPuuidUrl + puuid + "?api_key=" + riotApiKey;
        String result = httpConnect.connectUrl(apiUrl);

        String accountId = null;
        int profileIconId;
        long revisionDate = 0L;
        long summonerLevel = 0L;
        String encrypedId = null;

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            accountId = (String) jsonObject.get("accountId");
            profileIconId = Integer.valueOf(jsonObject.get("profileIconId").toString());
            revisionDate = (long) jsonObject.get("revisionDate");
            summonerLevel = (long) jsonObject.get("summonerLevel");
            encrypedId = (String) jsonObject.get("id");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return SummonerApiRequestDTO.builder()
                .summonerLevel(summonerLevel)
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .entrypedId(encrypedId)
                .build();
    }
}
