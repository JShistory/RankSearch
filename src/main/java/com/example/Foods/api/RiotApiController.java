package com.example.Foods.api;

import com.example.Foods.response.BasicResponse;
import com.example.Foods.response.ErrorCode;
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
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

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
                    .build();
        } else {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("소환사를 찾을 수 없습니다.")
                    .result(Collections.emptyList())
                    .build();
        }
        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());

    }

    @GetMapping("/summoner")
    public ResponseEntity<BasicResponse> findSummoner(String input)
            throws IOException, ParseException {
        String[] nameAndTag = riotService.splitNameAndTag(input);
        BasicResponse basicResponse = new BasicResponse();
        String name = nameAndTag[0];
        String tag = nameAndTag[1];

        Summoner summoner = summonerService.findByFindNameAndTag(name, tag);
        Summoner checkSummoner = riotService.loadUserWithTag(name, tag);
        if (checkSummoner == null) {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("찾으시는 소환사가 없습니다.")
                    .build();
            return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
        }


        //db에 소환사가 없으면 api 호출을 통해 소환사의 정보를 불러오고
        //db에 소환사가 있으면 랭크정보만 최신화해서 보여줌
        if (summoner == null && checkSummoner != null) {
            ResponseEntity<BasicResponse> entity = saveSummoner(input);

            List<Object> result = entity.getBody().getResult();
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .result(result)
                    .message("소환사 찾기 성공")
                    .build();
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
                    .build();
        }

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }


    @PostMapping("/summoner")
    public ResponseEntity<BasicResponse> saveSummoner(String input)
            throws IOException, ParseException {
        String[] nameAndTag = riotService.splitNameAndTag(input);
        BasicResponse basicResponse = new BasicResponse();
        String name = nameAndTag[0];
        String tag = nameAndTag[1];
        Summoner summoner;

        summoner = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);

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
        List<String> gameList = riotService.loadGameList(summoner.getPuuid(), 0, 13);
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
            List<Participant> savedParticipants1 = participantService.saveAllV1(
                    participantList.subList(0, participantList.size()), gameInfo);
//            List<Participant> savedParticipants2 = participantService.saveAllV2(participantList.subList(7,15), gameInfo);
//            List<Participant> savedParticipants3 = participantService.saveAllV3(participantList.subList(15,participantList.size()), gameInfo);

            gameInfoService.saveAll(savedParticipants1, gameInfo);
//            gameInfoService.saveAll(savedParticipants2, gameInfo);
//            gameInfoService.saveAll(savedParticipants3, gameInfo);
            summoner.putGameData(matchData);
        }

        basicResponse = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("소환사 조회 성공")
                .result(List.of(summoner))
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());

    }

//    @PostMapping("/summoner")
//    public ResponseEntity<BasicResponse> reload(){
//
//    }


}