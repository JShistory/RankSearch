package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.AccountDTO;
import com.example.Foods.riotApi.entity.GameInfoDto;
import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.entity.LeagueEntryDTO;
import com.example.Foods.riotApi.entity.MatchDTO;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.repository.LeagueEntryRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Foods.riotApi.repository.RiotRepository;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import jakarta.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


import java.net.URL;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RiotService {

    private final SummonerService summonerService;
    private final LeagueEntryService leagueEntryService;
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

    @Value("https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/")
    private String summonerAndTagUrl;

    @Value("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/")
    private String summonerPuuidUrl;

    @Value("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/")
    private String rankUrl;

    public String[] splitNameAndTag(String data) {
        String summonerName;
        String tag;

        if (data.contains("-")) {
            String[] split = data.split("-");
            summonerName = split[0];
            tag = split[1];
        } else {
            summonerName = data;
            tag = "KR1";
        }
        return new String[]{summonerName, tag};
    }

    public LeagueEntryDTO loadSoloRank(String encryptedSummonerId, Long id) {

        BufferedReader br;
        LeagueEntryDTO leagueEntry;

        Summoner summoner = summonerService.findById(id);
        List<LeagueEntry> findLeagueEntry = leagueEntryService.findBySummoner(summoner);

        if (!findLeagueEntry.isEmpty()) {
            for (LeagueEntry dataLeague : findLeagueEntry) {
                if (dataLeague.getQueueType().contains("SOLO")) {
                    LeagueEntryDTO leagueEntryDTO = LeagueEntryDTO.builder()
                            .leagueId(dataLeague.getLeagueId())
                            .leaguePoints(dataLeague.getLeaguePoints())
                            .wins(dataLeague.getWins())
                            .losses(dataLeague.getLosses())
                            .queueType(dataLeague.getQueueType())
                            .tier(dataLeague.getTier())
                            .rank(dataLeague.getRank())
                            .build();

                    return leagueEntryDTO;
                }
            }

        }

        try {
            String urlStr = rankUrl + encryptedSummonerId + "?api_key=" + riotApiKey;
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            if (result.contains("SOLO")) {
                JSONParser jsonParser = new JSONParser();
                JSONArray jsonArray = (JSONArray) jsonParser.parse(result);
                JSONObject k;
//                System.out.println("jsonArray"+jsonArray.size());
                //1은 자유랭크 0은 솔로랭때
                if (jsonArray.size() == 1 || jsonArray.size() == 2) {
                    k = (JSONObject) jsonArray.get(0);
                } else {
                    return null;
                }

                int wins = Integer.valueOf(k.get("wins").toString());
                int losses = Integer.valueOf(k.get("losses").toString());
                String rank = (String) k.get("rank");
                String tier = (String) k.get("tier");
                String queueType = (String) k.get("queueType");
                int leaguePoints = Integer.valueOf(k.get("leaguePoints").toString());
                String leagueId = (String) k.get("leagueId");
                leagueEntry = LeagueEntryDTO.builder()
                        .wins(wins)
                        .losses(losses)
                        .rank(rank)
                        .tier(tier)
                        .queueType(queueType)
                        .leagueId(leagueId)
                        .leaguePoints(leaguePoints)
                        .build();

            } else {
                return null;
            }


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return leagueEntry;
    }

    public LeagueEntryDTO loadFlexRank(String encryptedSummonerId, Long id) {

        BufferedReader br;
        LeagueEntryDTO leagueEntry;
        Summoner summoner = summonerService.findById(id);
        List<LeagueEntry> findLeagueEntry = leagueEntryService.findBySummoner(summoner);

        if (!findLeagueEntry.isEmpty()) {
            for (LeagueEntry dataLeague : findLeagueEntry) {
                if (dataLeague.getQueueType().contains("FLEX")) {
                    LeagueEntryDTO leagueEntryDTO = LeagueEntryDTO.builder()
                            .leagueId(dataLeague.getLeagueId())
                            .leaguePoints(dataLeague.getLeaguePoints())
                            .wins(dataLeague.getWins())
                            .losses(dataLeague.getLosses())
                            .queueType(dataLeague.getQueueType())
                            .tier(dataLeague.getTier())
                            .rank(dataLeague.getRank())
                            .build();

                    return leagueEntryDTO;
                }
            }

        }

        try {
            String urlStr = rankUrl + encryptedSummonerId + "?api_key=" + riotApiKey;
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }

            if (result.contains("FLEX")) {
//                System.out.println("result" + result);
                JSONParser jsonParser = new JSONParser();
                JSONArray jsonArray = (JSONArray) jsonParser.parse(result);
                JSONObject k;
//                System.out.println("jsonArray"+jsonArray.size());
                //1은 자유랭크 0은 솔로랭때
                if (jsonArray.size() == 1) {
                    k = (JSONObject) jsonArray.get(0);
                } else if (jsonArray.size() == 2) {
                    k = (JSONObject) jsonArray.get(1);
                } else {
                    return null;
                }
                int wins = Integer.valueOf(k.get("wins").toString());
                int losses = Integer.valueOf(k.get("losses").toString());
                String rank = (String) k.get("rank");
                String tier = (String) k.get("tier");
                String queueType = (String) k.get("queueType");
                int leaguePoints = Integer.valueOf(k.get("leaguePoints").toString());
                String leagueId = (String) k.get("leagueId");
                leagueEntry = LeagueEntryDTO.builder()
                        .wins(wins)
                        .losses(losses)
                        .rank(rank)
                        .tier(tier)
                        .queueType(queueType)
                        .leagueId(leagueId)
                        .leaguePoints(leaguePoints)
                        .build();
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return leagueEntry;
    }

    public Summoner loadUserWithTag(String summonerName, String tag) {
        //유저 찾아보고 있으면 반환
        Summoner user = summonerService.findByNameAndTag(summonerName, tag);
        AccountDTO accountDTO;
        if (user != null) {
            return user;
        }
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(summonerAndTagUrl + summonerName + "/" + tag + "?api_key=" + riotApiKey);

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            HttpEntity entity = response.getEntity();
            accountDTO = objectMapper.readValue(entity.getContent(), AccountDTO.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //puuid를 통해 추가적인 정보를 더 획득
        user = loadUserWithPuuid(accountDTO.getPuuid(), accountDTO.getTagLine(), accountDTO.getGameName());
        return user;
    }

    public Summoner loadUserWithPuuid(String puuid, String tag, String name) {
        //유저 찾아보고 있으면 반환
//        Summoner user = findByNameAndTag(summonerName,tag);
//        if (user != null) {
//            return user;
//        }
        Summoner user;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(summonerPuuidUrl + puuid + "?api_key=" + riotApiKey);

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            HttpEntity entity = response.getEntity();
            user = objectMapper.readValue(entity.getContent(), Summoner.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //유저 db에 저장
        List<String> gameInfo = loadGameId(user.getPuuid(), 0, 30);

        user.setGameInfo(gameInfo);
        user.setTag(tag);
        user.setPrevId(user.getName());
        user.setName(name);
//        summonerService.saveUser(user);

        return user;
    }

    public Summoner loadUser(String summonerName, String tag) {
        //유저 찾아보고 있으면 반환
//        Summoner user = findByNameAndTag(summonerName,tag);
//        if (user != null) {
//            return user;
//        }
        Summoner user;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(summonerUrl + summonerName + "?api_key=" + riotApiKey);

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            HttpEntity entity = response.getEntity();
            user = objectMapper.readValue(entity.getContent(), Summoner.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //유저 db에 저장
        List<String> gameInfo = loadGameId(user.getPuuid(), 0, 30);
        user.setGameInfo(gameInfo);
        user.setTag(tag);
        return user;
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

        long timeDiff = now.getTime() - date.getTime();
        List<Long> timeData = calculatorTime(timeDiff);
        long day = timeData.get(0);
        long hours = timeData.get(1);
        long minutes = timeData.get(2);
        long seconds = timeData.get(3);

        String str = day + "일 " + hours + "시간 " + minutes + "분 " + seconds + "초";
        return str;
    }

    public List<Long> calculatorTime(long time) {

        long seconds = time / 1000;

        long minutes = 0;
        long hours = 0;
        long day = 0;

        if (seconds >= 86400) {
            day = seconds / 86400;
            seconds -= (86400 * day);
        }

        if (seconds >= 3600) {
            hours = seconds / 3600;
            seconds -= (3600 * hours);
        }

        if (seconds >= 60) {
            minutes = seconds / 60;
            seconds -= (60 * minutes);
        }
        List<Long> timeData = new ArrayList<>();
        timeData.add(day);
        timeData.add(hours);
        timeData.add(minutes);
        timeData.add(seconds);

        return timeData;
    }

    public String diff(Date date) {
        long timeDiff = date.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시MM분 E요일");
        String format = simpleDateFormat.format(timeDiff);
        System.out.println(format);
        return format;


    }

}
