package com.example.Foods.riotV2.serviceV2;

import com.example.Foods.riotV2.dtoV2.ApiV2RequestDTO;
import com.example.Foods.riotV2.dtoV2.SummonerV2RequestDTO;
import com.example.Foods.riotV2.dtoV2.SummonerV2ResponseDTO;
import com.example.Foods.riotV2.entityV2.SummonerV2;
import com.example.Foods.riotV2.repositoryV2.SummonerV2Repository;
import com.example.Foods.riotV2.utilsV2.HttpConnect;
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
    public Long save(SummonerV2RequestDTO requestDTO){
        return summonerV2Repository.save(requestDTO.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public SummonerV2ResponseDTO findById(Long id){
        SummonerV2 entity = summonerV2Repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 소환사는 없습니다."));
        return new SummonerV2ResponseDTO(entity);
    }

    public SummonerV2RequestDTO loadUserWithNameAndTag(String name, String tag) {
        String apiUrl = summonerAndTagUrl + name + "/" + tag + "?api_key=" + riotApiKey;
        String result = httpConnect.connectUrl(apiUrl);
        ApiV2RequestDTO requestDTO = null;

        String gameName = null;
        String puuid = null;
        String tagLine = null;
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            gameName = (String) jsonObject.get("gameName");
            puuid = (String) jsonObject.get("puuid");
            tagLine = (String) jsonObject.get("tagLine");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        requestDTO = loadUserWithPuuid(puuid);
        return SummonerV2RequestDTO.builder()
                .summonerLevel(requestDTO.getSummonerLevel())
                .revisionDate(requestDTO.getRevisionDate())
                .profileIconId(requestDTO.getProfileIconId())
                .accountId(requestDTO.getAccountId())
                .name(gameName)
                .puuid(puuid)
                .tag(tagLine)
                .findName(gameName.toLowerCase().replaceAll(" ",""))
                .build();
    }

    public ApiV2RequestDTO loadUserWithPuuid(String puuid){
        String apiUrl = summonerPuuidUrl + puuid + "?api_key=" + riotApiKey;
        String result = httpConnect.connectUrl(apiUrl);

        String accountId;
        int profileIconId;
        long revisionDate;
        long summonerLevel;
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            accountId = (String) jsonObject.get("accountId");
            profileIconId = Integer.valueOf(jsonObject.get("profileIconId").toString());
            revisionDate = (long) jsonObject.get("revisionDate");
            summonerLevel = (long) jsonObject.get("summonerLevel");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return ApiV2RequestDTO.builder()
                .summonerLevel(summonerLevel)
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .build();
    }
}
