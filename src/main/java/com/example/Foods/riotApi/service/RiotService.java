package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.AccountDTO;
import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.GameInfoDto;
import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.entity.LeagueEntryDTO;
import com.example.Foods.riotApi.entity.MatchDTO;
import com.example.Foods.riotApi.entity.MetaData;
import com.example.Foods.riotApi.entity.MetaDataDTO;
import com.example.Foods.riotApi.entity.Participant;
import com.example.Foods.riotApi.entity.ParticipantDto;
import com.example.Foods.riotApi.entity.SpellType;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.repository.LeagueEntryRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Foods.riotApi.repository.RiotRepository;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.IOException;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
                JSONObject k = null;
//                System.out.println("jsonArray"+jsonArray.size());
                //1은 자유랭크 0은 솔로랭때
                for (int i = 0; i < jsonArray.size(); i++) {
                    k = (JSONObject) jsonArray.get(i);
                    String queueType = (String) k.get("queueType");
                    if (queueType.contains("SOLO")) {
                        break;
                    }
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
                JSONObject k = null;
//                System.out.println("jsonArray"+jsonArray.size());
                //1은 자유랭크 0은 솔로랭때
                for (int i = 0; i < jsonArray.size(); i++) {
                    k = (JSONObject) jsonArray.get(i);
                    String queueType = (String) k.get("queueType");
                    if (queueType.contains("FLEX")) {
                        break;
                    }
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
        user = loadUserWithPuuid(accountDTO.getPuuid());
        user.setPrevId(user.getName());
        user.setName(accountDTO.getGameName());
        return user;
    }

    public Summoner loadUserWithPuuid(String puuid) {
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

        user.setTag(tag);
        return user;
    }

    public List<String> loadGameList(String puuid, int start, int count) {
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


    public MetaDataDTO test(String matchId) {
        BufferedReader br;
        MetaDataDTO metaDataDTO;

        try {
            String urlStr = matchDataUrl + matchId + "?api_key=" + riotApiKey;
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
//
            JSONParser jsonParser = new JSONParser();
//            JSONArray jsonArray = (JSONArray) jsonParser.parse(result);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject k = (JSONObject) jsonObject.get("metadata");

            String dataVersion = k.get("dataVersion").toString();
            String matchId1 = k.get("matchId").toString();
            List<String> participants = (List<String>) k.get("participants");
            metaDataDTO = MetaDataDTO.builder()
                    .dataVersion(dataVersion)
                    .matchId(matchId1)
                    .participants(participants)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return metaDataDTO;

    }

    //api 호출로 게임 데이터 가져옴
    public MetaData loadMetaDataInfo(String matchId) {
        BufferedReader br;
        MetaData metaData;

        try {
            String urlStr = matchDataUrl + matchId + "?api_key=" + riotApiKey;
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
//
            JSONParser jsonParser = new JSONParser();
//            JSONArray jsonArray = (JSONArray) jsonParser.parse(result);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject metadata = (JSONObject) jsonObject.get("metadata");

            String dataVersion = metadata.get("dataVersion").toString();
            String matchId1 = metadata.get("matchId").toString();
            List<String> participants = (List<String>) metadata.get("participants");
            metaData = MetaData.builder()
                    .dataVersion(dataVersion)
                    .matchId(matchId1)
                    .participants(participants)
                    .build();

//            JSONObject gameInfo = (JSONObject) jsonObject.get("info");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return metaData;

    }

    public List<Participant> loadParticipantsGameInfo(String matchId) {
        BufferedReader br;
        Participant participantData = null;
        List<Participant> participant = new ArrayList<>();

        try {
            String urlStr = matchDataUrl + matchId + "?api_key=" + riotApiKey;
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
//
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

                participantData = Participant.builder()
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
                        .qCount(qCount)
                        .wCount(wCount)
                        .eCount(eCount)
                        .rCount(rCount)
                        .dCount(dCount)
                        .fCount(fCount)
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
                        .build();

                participant.add(participantData);


            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return participant;

    }

    public GameInfo loadGameInfo(String matchId) {
        BufferedReader br;
        GameInfo gameInfo;

        try {
            String urlStr = matchDataUrl + matchId + "?api_key=" + riotApiKey;
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
//
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject info = (JSONObject) jsonObject.get("info");

            long gameCreation = Long.valueOf(info.get("gameCreation").toString());
            long gameDuration = Long.valueOf(info.get("gameDuration").toString());
            long gameEndTimestamp = Long.valueOf(info.get("gameEndTimestamp").toString());
            long gameId = Long.valueOf(info.get("gameId").toString());
            long gameStartTimestamp = Long.valueOf(info.get("gameStartTimestamp").toString());
            String gameMode = info.get("gameMode").toString();
            String gameName = info.get("gameName").toString();
            String gameType = info.get("gameType").toString();
            String gameVersion = info.get("gameVersion").toString();
            int mapId = Integer.valueOf(info.get("mapId").toString());
            int queueId = Integer.valueOf(info.get("queueId").toString());

            gameInfo = GameInfo.builder()
                    .gameId(gameId)
                    .gameMode(gameMode)
                    .gameType(gameType)
                    .gameVersion(gameVersion)
                    .gameCreation(gameCreation)
                    .gameName(gameName)
                    .gameStartTimestamp(gameStartTimestamp)
                    .gameEndTimestamp(gameEndTimestamp)
                    .gameDuration(gameDuration)
                    .mapId(mapId)
                    .queueId(queueId)
                    .build();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return gameInfo;

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