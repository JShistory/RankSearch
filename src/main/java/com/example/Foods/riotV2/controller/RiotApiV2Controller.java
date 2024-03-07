package com.example.Foods.riotV2.controller;

import com.example.Foods.riotV2.domain.GameInfoV2;
import com.example.Foods.riotV2.domain.SummonerV2;
import com.example.Foods.riotV2.dto.*;
import com.example.Foods.riotV2.service.GameInfoV2Service;
import com.example.Foods.riotV2.service.GameUserV2Service;
import com.example.Foods.riotV2.service.LeagueEntryV2Service;
import com.example.Foods.riotV2.service.SummonerV2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v2")
@RestController
public class RiotApiV2Controller {
    //게임 정보를 몇개 불러올꺼냐
    private final int start = 0;
    private final int count = 20;
    private final SummonerV2Service summonerV2Service;
    private final LeagueEntryV2Service leagueEntryV2Service;
    private final GameInfoV2Service gameInfoV2Service;
    private final GameUserV2Service gameUserV2Service;

    @PostMapping("/summoner")
    public Long summoner(@RequestParam String name, @RequestParam String tag) {
        SummonerSaveRequestDTO requestDTO = summonerV2Service.loadUserWithNameAndTag(name, tag);
        SummonerV2 summonerV2 = requestDTO.toEntity();

        HashMap<String, LeagueEntrySaveRequestDTO> LeagueEntryrequestDTOHashMap = leagueEntryV2Service.loadLeagueEntry(requestDTO.getEncryptedId());
        for (LeagueEntrySaveRequestDTO data : LeagueEntryrequestDTOHashMap.values()) {
            summonerV2.addLeagueEntry(data.toEntity());
        }
        List<GameInfoSaveRequestDTO> gameInfoRequestDTO = gameInfoV2Service.loadMatchData(requestDTO.getPuuid(), start, count);
        for(GameInfoSaveRequestDTO data : gameInfoRequestDTO){
            summonerV2.addGameInfo(data.toEntity());
        }

        for(GameInfoSaveRequestDTO data : gameInfoRequestDTO){
            List<GameUserSaveRequestDTO> gameUserRequestDTO = gameUserV2Service.loadGameUserData(data.getMatchId());
            GameInfoV2 entity = data.toEntity();
            for(GameUserSaveRequestDTO dto : gameUserRequestDTO){
                entity.addGameUser(dto.toEntity());
            }
            gameInfoV2Service.save(entity);
        }



        return summonerV2Service.save(summonerV2);
    }

    @GetMapping("/summoner/{id}")
    public SummonerResponseDTO findById(@PathVariable Long id) {
        log.info(String.valueOf(id));
        return summonerV2Service.findById(id);
    }

    @DeleteMapping("/summoner/{id}")
    public Long delete(@PathVariable Long id) {
        return summonerV2Service.delete(id);
    }

}
