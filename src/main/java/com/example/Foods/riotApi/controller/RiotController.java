package com.example.Foods.riotApi.controller;

import com.example.Foods.riotApi.entity.ResponseVO;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.entity.SummonerDTO;
import com.example.Foods.riotApi.service.RiotService;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        Summoner apiResult = riotService.loadUser(summonerName);
        return apiResult;
    }

    @GetMapping("/summonerByName")
    public String SummonerInfo(String summonerName, Model model){
        summonerName = summonerName.replaceAll(" ", "%20");
        Summoner apiResult = riotService.loadUser(summonerName);
        if(apiResult == null){
            return "redirect:/";
        }
        List<String> gameData = riotService.loadGameInfo(apiResult.getPuuid(), 0,30);
        System.out.println(gameData);
        model.addAttribute("data",apiResult);
        return "riot/userInfo";
    }


}
