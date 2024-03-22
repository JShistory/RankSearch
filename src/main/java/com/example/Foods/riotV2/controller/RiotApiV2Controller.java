package com.example.Foods.riotV2.controller;

import com.example.Foods.response.BasicResponse;
import com.example.Foods.riotV2.domain.GameInfoV2;
import com.example.Foods.riotV2.domain.SummonerV2;
import com.example.Foods.riotV2.dto.*;
import com.example.Foods.riotV2.service.GameInfoV2Service;
import com.example.Foods.riotV2.service.GameUserV2Service;
import com.example.Foods.riotV2.service.LeagueEntryV2Service;
import com.example.Foods.riotV2.service.SummonerV2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    public ResponseEntity<BasicResponse> summoner(@RequestParam String name, @RequestParam String tag) {
        long startTime = System.currentTimeMillis();
        SummonerSaveRequestDTO summonerDTO = summonerV2Service.loadUserWithNameAndTag(name, tag);
        SummonerV2 summoner = summonerDTO.toEntity();

        //랭크 연관 관계 설정
        HashMap<String, LeagueEntrySaveRequestDTO> leagueEntryDTOHashMap = leagueEntryV2Service.loadLeagueEntry(summoner.getEncryptedId());
        leagueEntryV2Service.setLeagueEntrySummoner(leagueEntryDTOHashMap, summoner);

        //게임 정보(20게임) 연관 관계 설정), 게임 참가자 연관관계 설정
        //20게임에 대한 matchId를 가져옴
        List<GameInfoSaveRequestDTO> gameInfoDTOList = gameInfoV2Service.loadMatchData(summoner.getPuuid(), start, count);
        gameInfoV2Service.setGameSummoner(gameInfoDTOList, summoner);

        long executionTime = System.currentTimeMillis() - startTime;
        log.info("Execution time: {} milliseconds", executionTime);
        summonerV2Service.save(summoner);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BasicResponse(201, HttpStatus.CREATED, "Summoner Created successfully", Collections.singletonList(summonerDTO.getId())));
    }

    @GetMapping("/summoner/load/{id}")
    public ResponseEntity<BasicResponse> updateSummoner(@PathVariable Long id) {
        SummonerResponseDTO responseDTO = summonerV2Service.findById(id);
        SummonerV2 summoner = responseDTO.toEntity();

        log.info(summoner.getEncryptedId());
        //랭크 연관 관계 설정
        HashMap<String, LeagueEntrySaveRequestDTO> leagueEntryDTOHashMap = leagueEntryV2Service.loadLeagueEntry(summoner.getEncryptedId());
        leagueEntryV2Service.setLeagueEntrySummoner(leagueEntryDTOHashMap, summoner);

        List<GameInfoSaveRequestDTO> gameInfoDTOList = gameInfoV2Service.loadMatchData(summoner.getPuuid(), start, count);
        gameInfoV2Service.setGameSummoner(gameInfoDTOList, summoner);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new BasicResponse(204, HttpStatus.NO_CONTENT, "Summoner updated successfully", null));
    }

    @GetMapping("/summoner/{id}")
    public ResponseEntity<BasicResponse> findById(@PathVariable Long id) {
        log.info(String.valueOf(id));
        SummonerResponseDTO responseDTO = summonerV2Service.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new BasicResponse(200, HttpStatus.OK, "Summoner find successfully", Collections.singletonList(responseDTO)));
    }

    @DeleteMapping("/summoner/{id}")
    public ResponseEntity<BasicResponse> delete(@PathVariable Long id) {
        summonerV2Service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new BasicResponse(204, HttpStatus.NO_CONTENT, "Summoner deleted successfully", Collections.singletonList(id)));
    }

}
