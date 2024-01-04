package com.example.Foods.api;

import com.example.Foods.response.BasicResponse;
import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.entity.LeagueEntryDTO;
import com.example.Foods.riotApi.entity.MatchData;
import com.example.Foods.riotApi.entity.MetaData;
import com.example.Foods.riotApi.entity.Participant;
import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.service.GameInfoService;
import com.example.Foods.riotApi.service.LeagueEntryService;
import com.example.Foods.riotApi.service.MatchService;
import com.example.Foods.riotApi.service.MetaDataService;
import com.example.Foods.riotApi.service.ParticipantService;
import com.example.Foods.riotApi.service.RiotService;
import com.example.Foods.riotApi.service.SummonerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class RiotApiController {
    private final RiotService riotService;
    private final SummonerService summonerService;
    private final LeagueEntryService leagueEntryService;
    private final MatchService matchService;
    private final MetaDataService metaDataService;
    private final GameInfoService gameInfoService;
    private final ParticipantService participantService;

    @GetMapping("/summoners")
    public ResponseEntity<BasicResponse> summoners() {
        BasicResponse basicResponse = new BasicResponse();
        List<Summoner> summonerList = summonerService.findAll();
        if (!summonerList.isEmpty()) {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("소환사 리스트 조회 성공")
                    .result(Arrays.asList(summonerList.toArray()))
                    .count(1)
                    .build();
        } else {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("소환사를 찾을 수 없습니다.")
                    .result(Collections.emptyList())
                    .count(0)
                    .build();
        }
        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());

    }

    @GetMapping("/summoner")
    public ResponseEntity<BasicResponse> findSummoner(String input) {
        String[] nameAndTag = riotService.splitNameAndTag(input);
        BasicResponse basicResponse = new BasicResponse();
        String name = nameAndTag[0];
        String tag = nameAndTag[1];

        Summoner summoner = summonerService.findByFindNameAndTag(name, tag);
        Summoner checkSummoner = riotService.loadUserWithTag(name, tag);
        if (summoner == null && checkSummoner != null) {
            ResponseEntity<BasicResponse> entity = saveSummoner(input);

            if (entity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                basicResponse = entity.getBody();

            } else {
                List<Object> result = entity.getBody().getResult();
                basicResponse = BasicResponse.builder()
                        .code(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .result(result)
                        .message("소환사 찾기 성공")
                        .count(1)
                        .build();
            }
        } else {
            LeagueEntryDTO soloLeagueEntryDTO = riotService.loadSoloRank(summoner.getId(), summoner.getDataId());
            LeagueEntryDTO flexLeagueEntryDTO = riotService.loadFlexRank(summoner.getId(), summoner.getDataId());
            LeagueEntry flex = null;
            LeagueEntry solo = null;
            if (soloLeagueEntryDTO != null) {
                Long soloRankId = leagueEntryService.saveRank(summoner, soloLeagueEntryDTO);
                solo = leagueEntryService.findById(soloRankId);
            }

            if (flexLeagueEntryDTO != null) {
                Long flexRankId = leagueEntryService.saveRank(summoner, flexLeagueEntryDTO);
                flex = leagueEntryService.findById(flexRankId);
            }
            summoner.putLeagueData(solo);
            summoner.putLeagueData(flex);

            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .result(Collections.singletonList(summoner))
                    .message("소환사 찾기 성공")
                    .count(1)
                    .build();
        }

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }


    @PostMapping("/summoner")
    public ResponseEntity<BasicResponse> saveSummoner(String input) {
        String[] nameAndTag = riotService.splitNameAndTag(input);
        BasicResponse basicResponse = new BasicResponse();
        String name = nameAndTag[0];
        String tag = nameAndTag[1];
        Summoner summoner;

        summoner = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        if (summoner == null) {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("소환사를 찾을 수 없습니다.")
                    .result(Collections.emptyList())
                    .count(0)
                    .build();
            return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
        }

        Summoner checkSummoner = summonerService.findByFindNameAndTag(name, tag);
        if (checkSummoner == null) {
            summonerService.saveUser(summoner, tag, name);
        } else {
            summoner = checkSummoner;
        }

        //솔로랭크, 자유랭크 정보 저장
        LeagueEntryDTO soloLeagueEntryDTO = riotService.loadSoloRank(summoner.getId(), summoner.getDataId());
        LeagueEntryDTO flexLeagueEntryDTO = riotService.loadFlexRank(summoner.getId(), summoner.getDataId());
        LeagueEntry flex = null;
        LeagueEntry solo = null;
        if (soloLeagueEntryDTO != null) {
            Long soloRankId = leagueEntryService.saveRank(summoner, soloLeagueEntryDTO);
            solo = leagueEntryService.findById(soloRankId);
        }

        if (flexLeagueEntryDTO != null) {
            Long flexRankId = leagueEntryService.saveRank(summoner, flexLeagueEntryDTO);
            flex = leagueEntryService.findById(flexRankId);
        }
        summoner.putLeagueData(solo);
        summoner.putLeagueData(flex);

        //최근 20개의 게임을 불러옴
        List<String> gameList = riotService.loadGameList(summoner.getPuuid(), 0, 20);
        GameInfo gameInfo;
        MetaData metaData;
        List<MatchData> matchDataList = summoner.getMatchData();
        for (String game : gameList) {
            boolean isContains = false;
            //매치 데이터를 비교해서 이미 있는 값이면 값을 넣지 않음
            for (MatchData matches : matchDataList) {
                if (matches.getGameInfo().getGameId().longValue() == Long.valueOf(
                        game.split("_")[1])) {
                    isContains = true;
                    break;
                }
            }
            if (isContains) {
                continue;
            }

            gameInfo = riotService.loadGameInfo(game);
            metaData = riotService.loadMetaDataInfo(game);
            gameInfoService.saveGameInfo(gameInfo);
            metaDataService.saveMetaData(metaData);

            Long matchId = matchService.saveMatch(summoner, gameInfo, metaData);
            MatchData matchData = matchService.findById(matchId);
            gameInfo.putMatch(matchData);
            metaData.putMatch(matchData);

            //참가자 정보에 대한 코드
            List<Participant> participantList = riotService.loadParticipantsGameInfo(game);
            List<Participant> savedParticipants = participantService.saveAll(participantList, gameInfo);
            gameInfoService.saveAll(savedParticipants, gameInfo);
            summoner.putGameData(matchData);
        }

        basicResponse = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("소환사 저장 성공")
                .result(List.of(summoner))
                .count(1)
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());

    }

//    @PostMapping("/summoner")
//    public ResponseEntity<BasicResponse> reload(){
//
//    }
}