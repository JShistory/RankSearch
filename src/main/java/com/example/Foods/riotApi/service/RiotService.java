package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.GameInfoDto;
import com.example.Foods.riotApi.entity.MatchDTO;
import com.example.Foods.riotApi.entity.Summoner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Foods.riotApi.repository.RiotRepository;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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

    @Value("${riot.api.matchesUrl}")
    private String matchesUrl;

    @Value("${riot.api.matchDataUrl}")
    private String matchDataUrl;

    public void saveUser(Summoner summoner) {
        riotRepository.save(summoner);
    }

    public Summoner findByName(String summonerName) {
        return riotRepository.findByName(summonerName);
    }

    public Summoner loadUser(String summonerName) {

        Summoner result;
        //유저 찾아보고 있으면 반환
        Summoner user = findByName(summonerName);
        if (user != null) {
            return user;
        }
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(summonerUrl + summonerName + "?api_key=" + riotApiKey);

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            HttpEntity entity = response.getEntity();
            result = objectMapper.readValue(entity.getContent(), Summoner.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //유저 db에 저장
        List<String> gameInfo = loadGameId(result.getPuuid(), 0, 30);
        result.setGameInfo(gameInfo);
        saveUser(result);

        return result;
    }

    public List<String> loadGameId(String puuid, int start, int count) {
        List<String> result;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(
                    matchesUrl + puuid + "/ids?start=" + start + "&count=" + count + "&api_key=" + riotApiKey);

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            HttpEntity entity = response.getEntity();
            result = objectMapper.readValue(entity.getContent(), List.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    //api 호출로 게임 데이터 가져옴
    public MatchDTO loadGameInfo(String matchId) {
        MatchDTO result;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(matchDataUrl + matchId + "?api_key=" + riotApiKey);

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            HttpEntity entity = response.getEntity();
            result = objectMapper.readValue(entity.getContent(), MatchDTO.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return result;
    }

    public Date convertUnixTimeToUTC(Long unixTimeStamp) {
        Date date = new Date();
        date.setTime(unixTimeStamp);
        return date;
    }

    public String diffCurrentTimeAndParam(Date date) {
        Date now = new Date();
        System.out.println(date.getTime());
        System.out.println(now.getTime());

        long timeDiff = now.getTime() - date.getTime();
        long seconds = timeDiff / 1000;

        long minutes = 0;
        long hours = 0;
        long day = 0;

        if(seconds >= 86400){
            day = seconds / 86400;
            seconds -= (86400 * day);
        }

        if(seconds >= 3600){
            hours = seconds / 3600;
            seconds -= (3600 * hours);
        }

        if(seconds >= 60){
            minutes = seconds / 60;
            seconds -= (60 * minutes);
        }

        String str = day + "일 " + hours + "시간 " + minutes + "분 " + seconds + "초";
        return str;
    }

}
