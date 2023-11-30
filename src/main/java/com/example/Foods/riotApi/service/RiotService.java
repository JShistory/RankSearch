package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.Summoner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Foods.riotApi.entity.SummonerDTO;
import com.example.Foods.riotApi.repository.RiotRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.io.IOException;
@Service
@RequiredArgsConstructor
public class RiotService {

    private final RiotRepository riotRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${riot.api.key}")
    private String riotApiKey;
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
        Summoner user = findByName(summonerName);
        if(user != null){
            return user;
        }
        String serverUrl = "https://kr.api.riotgames.com";

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + riotApiKey);

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

        saveUser(result);
        return result;
    }

}
