package com.example.Foods.riotApi.controller;

import com.example.Foods.riotApi.entity.GameInfoDto;
import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.entity.LeagueEntryDTO;
import com.example.Foods.riotApi.entity.MatchDTO;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.entity.SummonerDTO;
import com.example.Foods.riotApi.service.LeagueEntryService;
import com.example.Foods.riotApi.service.RiotService;
import com.example.Foods.riotApi.service.SummonerService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/riot")
@RequiredArgsConstructor
public class RiotController {
    private final RiotService riotService;
    private final SummonerService summonerService;
    private final LeagueEntryService leagueEntryService;


    @GetMapping()
    public String main(){
        return "riot/home";
    }

    @PostMapping("/summonerByName")
    @ResponseBody
    public Summoner findSummonerName(String summonerName){
//        summonerName = summonerName.replaceAll(" ", "%20");
        String[] nameAndTag = riotService.splitNameAndTag(summonerName);

        Summoner apiResult = riotService.loadUser(nameAndTag[0], nameAndTag[1]);
        summonerService.saveUser(apiResult);
        return apiResult;
    }

    @GetMapping("/summonerByName")
    public String SummonerInfo(String summonerName,Model model){
//        summonerName = summonerName.replaceAll(" ", "%20");
        String[] nameAndTag = riotService.splitNameAndTag(summonerName);


        Summoner apiResult = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);

        //검색 시 동일한 닉네임이 2명 이상일 때
        if(summonerService.summonerList(nameAndTag[0]).size()>1 && nameAndTag[1].equals("KR1")){
            return SummonerList(model, nameAndTag[0]);
        }
        if(apiResult == null){
            return "riot/notFoundError";
        }
        summonerService.saveUser(apiResult);


        LeagueEntryDTO leagueEntrySoloDTO = riotService.loadSoloRank(apiResult.getId(),apiResult.getDataId());
        LeagueEntryDTO leagueEntryFlexDTO = riotService.loadFlexRank(apiResult.getId(), apiResult.getDataId());
        LeagueEntry flex = null;
        LeagueEntry solo = null;
        if(leagueEntryFlexDTO != null){
            Long flexId = leagueEntryService.saveRank(apiResult, leagueEntryFlexDTO);
            flex = leagueEntryService.findById(flexId);
        }
        if(leagueEntrySoloDTO != null) {
            Long soloId = leagueEntryService.saveRank(apiResult, leagueEntrySoloDTO);
            solo = leagueEntryService.findById(soloId);
        }
        //솔로랭크 자유랭크



        List<String> gameInfo = apiResult.getGameInfo();
        MatchDTO gameData = riotService.loadGameInfo(gameInfo.get(0));
        Date gameEndTime = riotService.convertUnixTimeToUTC(gameData.getInfo().getGameEndTimestamp());
        String diffEndTimeNowTimeFormat_1 = riotService.diffCurrentTimeAndParam(gameEndTime);
        String diffEndTimeNowTimeFormat_2 = riotService.diff(gameEndTime);


        model.addAttribute("soloLeagueData",solo);
        model.addAttribute("flexLeagueData",flex);
        model.addAttribute("dateFormat",diffEndTimeNowTimeFormat_2);
        model.addAttribute("diffTime",diffEndTimeNowTimeFormat_1);
        model.addAttribute("gameData",gameData);
        model.addAttribute("data",apiResult);
        model.addAttribute("time",gameEndTime);
        return "riot/userInfo";
    }

    @GetMapping("/summonerList")
    public String SummonerList(Model model, String name){
        List<Summoner> summoners = summonerService.summonerList(name);
        model.addAttribute("summonerList",summoners);
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
