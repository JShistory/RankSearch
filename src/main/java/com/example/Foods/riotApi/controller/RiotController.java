package com.example.Foods.riotApi.controller;

import com.example.Foods.riotApi.entity.GameInfoDto;
import com.example.Foods.riotApi.entity.MatchDTO;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.entity.SummonerDTO;
import com.example.Foods.riotApi.service.RiotService;
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


    @GetMapping()
    public String main(){
        return "riot/home";
    }

    @PostMapping("/summonerByName")
    @ResponseBody
    public Summoner findSummonerName(String summonerName){
        summonerName = summonerName.replaceAll(" ", "%20");
        String[] nameAndTag = riotService.splitNameAndTag(summonerName);

        Summoner apiResult = riotService.loadUser(nameAndTag[0], nameAndTag[1]);
        return apiResult;
    }

    @GetMapping("/summonerByName")
    public String SummonerInfo(String summonerName,Model model){
        summonerName = summonerName.replaceAll(" ", "%20");
        String[] nameAndTag = riotService.splitNameAndTag(summonerName);


        Summoner apiResult = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        if(riotService.summonerList(nameAndTag[0]).size()>1 && nameAndTag[1].equals("KR1")){
            return SummonerList(model, nameAndTag[0]);
        }

        if(apiResult == null){
            return "riot/notFoundError";
        }

        List<String> gameInfo = apiResult.getGameInfo();
        MatchDTO gameData = riotService.loadGameInfo(gameInfo.get(0));
        Date gameEndTime = riotService.convertUnixTimeToUTC(gameData.getInfo().getGameEndTimestamp());
        String diffEndTimeNowTimeFormat_1 = riotService.diffCurrentTimeAndParam(gameEndTime);
        String diffEndTimeNowTimeFormat_2 = riotService.diff(gameEndTime);

        model.addAttribute("dateFormat",diffEndTimeNowTimeFormat_2);
        model.addAttribute("diffTime",diffEndTimeNowTimeFormat_1);
        model.addAttribute("gameData",gameData);
        model.addAttribute("data",apiResult);
        model.addAttribute("time",gameEndTime);
        return "riot/userInfo";
    }

    @GetMapping("/summonerList")
    public String SummonerList(Model model, String name){
        List<Summoner> summoners = riotService.summonerList(name);
        model.addAttribute("summonerList",summoners);
        return "riot/summonerList";
    }

    @GetMapping("/find/{id}")
    public String Summoner(@PathVariable Long id,Model model){
        Summoner summoner = riotService.findById(id);


        List<String> gameInfo = summoner.getGameInfo();
        MatchDTO gameData = riotService.loadGameInfo(gameInfo.get(0));
        Date gameEndTime = riotService.convertUnixTimeToUTC(gameData.getInfo().getGameEndTimestamp());
        String diffEndTimeNowTimeFormat_1 = riotService.diffCurrentTimeAndParam(gameEndTime);
        String diffEndTimeNowTimeFormat_2 = riotService.diff(gameEndTime);

        model.addAttribute("dateFormat",diffEndTimeNowTimeFormat_2);
        model.addAttribute("diffTime",diffEndTimeNowTimeFormat_1);
        model.addAttribute("gameData",gameData);
        model.addAttribute("data",summoner);
        model.addAttribute("time",gameEndTime);
        return "riot/userInfo";
    }


}
