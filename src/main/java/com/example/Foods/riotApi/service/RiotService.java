package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.Summoner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Foods.riotApi.repository.RiotRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
@Service
@RequiredArgsConstructor
public class RiotService {

    private final RiotRepository riotRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${riot.api.key}")
    private String riotApiKey;

    //riot 유저 검색 url
    @Value("${riot.api.summonerUrl}")
    private String summonerUrl;

    @Value("${riot.api.matchUrl}")
    private String matchUrl;

    @Value("${riot.api.matchesUrl}")
    private String matchesUrl;

    public void saveUser(Summoner summoner){
        riotRepository.save(summoner);
    }

    public Summoner findByName(String summonerName){

        Summoner summoner = riotRepository.findByName(summonerName);
        if(summoner == null){

        }
        return riotRepository.findByName(summonerName);
    }

    public Summoner loadUser(String summonerName){

        Summoner result;
        //유저 찾아보고 있으면 반환
        Summoner user = findByName(summonerName);
        if(user != null){
            return user;
        }
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(summonerUrl + summonerName + "?api_key=" + riotApiKey);

            HttpResponse response = client.execute(request);

            if(response.getStatusLine().getStatusCode() != 200){
                return null;
            }

            HttpEntity entity = response.getEntity();
            result = objectMapper.readValue(entity.getContent(), Summoner.class);

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        //유저 db에 저장
        saveUser(result);

        return result;
    }

    public List<String> loadGameInfo(String puuid,int start, int count){
        List<String> result;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(matchUrl +puuid+"/ids?start="+start+"&count="+count+"&api_key=" + riotApiKey);

            HttpResponse response = client.execute(request);

            if(response.getStatusLine().getStatusCode() != 200){
                return null;
            }

            HttpEntity entity = response.getEntity();
            result = objectMapper.readValue(entity.getContent(), List.class);

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return result;
    }

}
