package com.example.Foods.riotApi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.MatchData;
import com.example.Foods.riotApi.entity.MetaData;
import com.example.Foods.riotApi.entity.Summoner;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MatchDataServiceTest {
    @Autowired
    private RiotApiService riotApiService;
    @Autowired
    private MatchService matchService;

    @Autowired
    private MetaDataService metaDataService;
    @Autowired
    private GameInfoService gameInfoService;

    @Test
    @Transactional
    public void 매치저장() throws Exception {
        //given
        String input = "1Byte";
        String[] nameAndTag = riotApiService.splitNameAndTag(input);
        Summoner summoner = riotApiService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        List<String> gameList = riotApiService.loadGameList(summoner.getPuuid(), 0, 3);
        MetaData metaDataDTO = riotApiService.loadMetaDataInfo(gameList.get(0));
        GameInfo gameInfoDto = riotApiService.loadGameInfo(gameList.get(0));


        //when
        GameInfo gameInfo = GameInfo.builder()
                .gameCreation(gameInfoDto.getGameCreation())
                .gameEndTimestamp(gameInfoDto.getGameEndTimestamp())
                .gameDuration(gameInfoDto.getGameDuration())
                .gameId(gameInfoDto.getGameId())
                .gameType(gameInfoDto.getGameType())
                .gameMode(gameInfoDto.getGameMode())
                .gameVersion(gameInfoDto.getGameVersion())
                .gameName(gameInfoDto.getGameName())
                .gameStartTimestamp(gameInfoDto.getGameStartTimestamp())
                .mapId(gameInfoDto.getMapId())
                .queueId(gameInfoDto.getQueueId())
                .build();
        MetaData metaData = MetaData.builder()
                .matchId(metaDataDTO.getMatchId())
                .dataVersion(metaDataDTO.getDataVersion())
                .participants(metaDataDTO.getParticipants())
                .build();

        metaDataService.saveMetaData(metaData);
        gameInfoService.saveGameInfo(gameInfo);

        Long l = matchService.saveMatch(summoner, gameInfo, metaData);

        //then
        MatchData matchData = matchService.findById(l);
        metaData.putMatch(matchData);
        gameInfo.putMatch(matchData);
        assertEquals(matchData.getSummoner().getName(), summoner.getName());

    }

    @Test
    @Transactional
    public void mata데이터불러오기() throws Exception {
        //given
//        MatchDTO matchDTO = riotService.loadGameInfo("KR_6815291259");
        MetaData metaData = riotApiService.loadMetaDataInfo("KR_6815291259");
        //when
        //then
        Assertions.assertEquals(metaData.getMatchId(),"KR_6815291259");
    }

    @Test
    @Transactional
    public void gameInfo데이터불러오기() throws Exception {
        //given
//        MatchDTO matchDTO = riotService.loadGameInfo("KR_6815291259");
        GameInfo gameInfo = riotApiService.loadGameInfo("KR_6815291259");
        //when
        //then
        Assertions.assertEquals(gameInfo.getGameMode(),"ARAM");
    }


}