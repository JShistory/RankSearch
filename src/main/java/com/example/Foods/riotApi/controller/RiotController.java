package com.example.Foods.riotApi.controller;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.entity.LeagueEntryDTO;
import com.example.Foods.riotApi.entity.MatchData;
import com.example.Foods.riotApi.entity.MetaData;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.service.GameInfoService;
import com.example.Foods.riotApi.service.LeagueEntryService;
import com.example.Foods.riotApi.service.MatchService;
import com.example.Foods.riotApi.service.MetaDataService;
import com.example.Foods.riotApi.service.RiotService;
import com.example.Foods.riotApi.service.SummonerService;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/riot")
@RequiredArgsConstructor
public class RiotController {
    private final RiotService riotService;
    private final SummonerService summonerService;
    private final LeagueEntryService leagueEntryService;
    private final MatchService matchService;
    private final MetaDataService metaDataService;
    private final GameInfoService gameInfoService;

    @GetMapping()
    public String main() {
        return "riot/home";
    }

    @PostMapping("/summonerByName")
    @ResponseBody
    public Summoner findSummonerName(String summonerName) {
//        summonerName = summonerName.replaceAll(" ", "%20");
        String[] nameAndTag = riotService.splitNameAndTag(summonerName);

        Summoner apiResult = riotService.loadUser(nameAndTag[0], nameAndTag[1]);
        summonerService.saveUser(apiResult, nameAndTag[1], nameAndTag[0]);
        return apiResult;
    }

    @GetMapping("/summonerByName")
    public String SummonerInfo(String summonerName, Model model) {
//        summonerName = summonerName.replaceAll(" ", "%20");
        String[] nameAndTag = riotService.splitNameAndTag(summonerName);

        Summoner apiResult = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);

        //검색 시 동일한 닉네임이 2명 이상일 때
        if (summonerService.summonerList(nameAndTag[0]).size() > 1 && nameAndTag[1].equals("KR1")) {
            return SummonerList(model, nameAndTag[0]);
        }
        if (apiResult == null) {
            return "riot/notFoundError";
        }
        summonerService.saveUser(apiResult, nameAndTag[1], nameAndTag[0]);

        LeagueEntryDTO leagueEntrySoloDTO = riotService.loadSoloRank(apiResult.getId(), apiResult.getDataId());
        LeagueEntryDTO leagueEntryFlexDTO = riotService.loadFlexRank(apiResult.getId(), apiResult.getDataId());
        LeagueEntry flex = null;
        LeagueEntry solo = null;
        if (leagueEntryFlexDTO != null) {
            Long flexId = leagueEntryService.saveRank(apiResult, leagueEntryFlexDTO);
            flex = leagueEntryService.findById(flexId);
        }
        if (leagueEntrySoloDTO != null) {
            Long soloId = leagueEntryService.saveRank(apiResult, leagueEntrySoloDTO);
            solo = leagueEntryService.findById(soloId);
        }
        apiResult.putLeagueData(solo);
        apiResult.putLeagueData(flex);
        //게임 정보 불러오기.
        List<String> gameInfo = riotService.loadGameList(apiResult.getPuuid(), 0, 5);
        GameInfo gameInfoData = null;
        MetaData metaDataData;
        if (gameInfo.size() != 0) {
            for (int i = 0; i < gameInfo.size(); i++) {

                List<MatchData> matchDataList = apiResult.getMatchData();
                boolean isContains = false;
                for (MatchData matches : matchDataList) {
                    if (matches.getGameInfo().getGameId().longValue() == Long.valueOf(
                            gameInfo.get(i).split("_")[1])) {
                        isContains = true;
                        break;
                    }
                }
                if (isContains) {
                    continue;
                }
                gameInfoData = riotService.loadGameInfo(gameInfo.get(i));
                metaDataData = riotService.loadMetaDataInfo(gameInfo.get(i));

                gameInfoService.saveGameInfo(gameInfoData);
                metaDataService.saveMetaData(metaDataData);
                Long l = matchService.saveMatch(apiResult, gameInfoData, metaDataData);
                MatchData matchData = matchService.findById(l);
                gameInfoData.putMatch(matchData);
                metaDataData.putMatch(matchData);
                apiResult.putGameData(matchData);

            }
        }

        List<MatchData> matchData = matchService.findBySummoner(apiResult);
//        for(Match matchData : matches){
//            List<String> participants = matchData.getMetaData().getParticipants();
//            List<String> participantsName = new ArrayList<>();
//            for(String participantsData : participants){
//                Summoner summoner = riotService.loadUserWithPuuid(participantsData);
//                participantsName.add(summoner.getName());
//            }
//        }
        List<String> participants = matchData.get(0).getMetaData().getParticipants();
        Summoner summoner = riotService.loadUserWithPuuid(participants.get(0));
        Summoner summoner1 = riotService.loadUserWithPuuid(participants.get(1));
        model.addAttribute("test", summoner);
        model.addAttribute("test1", summoner1);

        GameInfo time = riotService.loadGameInfo(gameInfo.get(0));
        Date gameEndTime = riotService.convertUnixTimeToUTC(time.getGameEndTimestamp());
        String diffEndTimeNowTimeFormat_1 = riotService.diffCurrentTimeAndParam(gameEndTime);
        String diffEndTimeNowTimeFormat_2 = riotService.diff(gameEndTime);

        model.addAttribute("soloLeagueData", solo);
        model.addAttribute("flexLeagueData", flex);
        model.addAttribute("dateFormat", diffEndTimeNowTimeFormat_2);
        model.addAttribute("diffTime", diffEndTimeNowTimeFormat_1);
        model.addAttribute("gameData", gameInfo);
        model.addAttribute("data", apiResult);
        model.addAttribute("time", gameEndTime);
        return "riot/userInfo";
    }

    @GetMapping("/summonerList")
    public String SummonerList(Model model, String name) {
        List<Summoner> summoners = summonerService.summonerList(name);
        model.addAttribute("summonerList", summoners);
        return "riot/summonerList";
    }

//    @GetMapping("/find/{id}")
//    public String Summoner(@PathVariable Long id,Model model){
//        Summoner summoner = summonerService.findById(id);
//        List<LeagueEntry> leagueEntryList = leagueEntryService.findBySummoner(summoner);
//
//        List<String> gameInfo = summoner.getGameInfo();
//        MatchDTO gameData = riotService.loadGameInfo(gameInfo.get(0));
//        Date gameEndTime = riotService.convertUnixTimeToUTC(gameData.getInfo().getGameEndTimestamp());
//        String diffEndTimeNowTimeFormat_1 = riotService.diffCurrentTimeAndParam(gameEndTime);
//        String diffEndTimeNowTimeFormat_2 = riotService.diff(gameEndTime);
//
////        model.addAttribute("soloLeagueData",solo);
////        model.addAttribute("flexLeagueData",flex);
//        model.addAttribute("dateFormat",diffEndTimeNowTimeFormat_2);
//        model.addAttribute("diffTime",diffEndTimeNowTimeFormat_1);
//        model.addAttribute("gameData",gameData);
//        model.addAttribute("data",summoner);
//        model.addAttribute("time",gameEndTime);
//        return "riot/userInfo";
//    }


}