package com.example.Foods.riotV2.controller;

import com.example.Foods.riotV2.domain.LeagueEntryV2;
import com.example.Foods.riotV2.domain.SummonerV2;
import com.example.Foods.riotV2.dto.LeagueEntrySaveRequestDTO;
import com.example.Foods.riotV2.dto.SummonerResponseDTO;
import com.example.Foods.riotV2.dto.SummonerSaveRequestDTO;
import com.example.Foods.riotV2.service.LeagueEntryV2Service;
import com.example.Foods.riotV2.service.SummonerV2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v2")
@RestController
public class RiotApiV2Controller {
    private final SummonerV2Service summonerV2Service;
    private final LeagueEntryV2Service leagueEntryV2Service;

    @PostMapping("/summoner")
    public Long summoner(@RequestParam String name, @RequestParam String tag) {
        SummonerSaveRequestDTO requestDTO = summonerV2Service.loadUserWithNameAndTag(name, tag);
        SummonerV2 summonerV2 = requestDTO.toEntity();

        HashMap<String, LeagueEntrySaveRequestDTO> requestDTOHashMap = leagueEntryV2Service.loadLeagueEntry(requestDTO.getEncryptedId());
        for (LeagueEntrySaveRequestDTO data : requestDTOHashMap.values()) {
            summonerV2.addLeagueEntry(data.toEntity());
        }
        return summonerV2Service.save(summonerV2);
    }

    @GetMapping("/summoner/{id}")
    public SummonerResponseDTO findById(@PathVariable Long id) {
//        SummonerResponseDTO responseDTO = summonerV2Service.findById(id);
        return summonerV2Service.findById(id);
    }

    @DeleteMapping("/summoner/{id}")
    public Long delete(@PathVariable Long id) {
        return summonerV2Service.delete(id);
    }

}
