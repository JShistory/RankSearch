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

import java.util.ArrayList;
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
        long startTime = System.currentTimeMillis();
        SummonerSaveRequestDTO summonerDTO = summonerV2Service.loadUserWithNameAndTag(name, tag);
        SummonerV2 summoner = summonerDTO.toEntity();

        //랭크 연관 관계 설정
        HashMap<String, LeagueEntrySaveRequestDTO> leagueEntryDTOHashMap = leagueEntryV2Service.loadLeagueEntry(summonerDTO.getEncryptedId());
        for (LeagueEntrySaveRequestDTO leagueEntryDTO : leagueEntryDTOHashMap.values()) {
            summoner.addLeagueEntry(leagueEntryDTO.toEntity());
        }
        //게임 정보(20게임) 연관 관계 설정), 게임 참가자 연관관계 설정
        //20게임에 대한 matchId를 가져옴
        List<GameInfoSaveRequestDTO> gameInfoDTOList = gameInfoV2Service.loadMatchData(summonerDTO.getPuuid(), start, count);
        for (GameInfoSaveRequestDTO gameInfoDTO : gameInfoDTOList) {
            GameInfoV2 gameInfoEntity = gameInfoDTO.toEntity();
            summoner.addGameInfo(gameInfoEntity);
            //각 게임당 참가자들의 정보
            List<GameUserSaveRequestDTO> gameUserDTOList = gameUserV2Service.loadGameUserData(gameInfoDTO.getMatchId());
            for (GameUserSaveRequestDTO gameUserDTO : gameUserDTOList) {
                gameInfoEntity.addGameUser(gameUserDTO.toEntity());
            }
        }

        long executionTime = System.currentTimeMillis() - startTime;
        log.info("Execution time: {} milliseconds", executionTime);

        return summonerV2Service.save(summoner);
    }

    @GetMapping("/summoner/{id}")
    public SummonerResponseDTO findById(@PathVariable Long id) {
        log.info(String.valueOf(id));
        SummonerResponseDTO responseDTO = summonerV2Service.findById(id);
        return responseDTO;
    }

    @DeleteMapping("/summoner/{id}")
    public Long delete(@PathVariable Long id) {
        return summonerV2Service.delete(id);
    }

}
