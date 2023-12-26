package com.example.Foods.riotApi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.GameInfoDto;
import com.example.Foods.riotApi.entity.Match;
import com.example.Foods.riotApi.entity.MatchDTO;
import com.example.Foods.riotApi.entity.MetaData;
import com.example.Foods.riotApi.entity.MetaDataDTO;
import com.example.Foods.riotApi.entity.Participant;
import com.example.Foods.riotApi.entity.Summoner;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatchServiceTest {
    @Autowired
    private RiotService riotService;
    @Autowired
    private EntityManager em;
    @Autowired
    private SummonerService summonerService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private LeagueEntryService leagueEntryService;

    @Autowired
    private MetaDataService metaDataService;
    @Autowired
    private GameInfoService gameInfoService;

    @Autowired
    private ParticipantService participantService;

    @Test
    @Transactional
    public void 매치저장() throws Exception {
        //given
        String input = "1Byte";
        String[] nameAndTag = riotService.splitNameAndTag(input);
        Summoner summoner = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        List<String> gameList = riotService.loadGameList(summoner.getPuuid(), 0, 30);
        MetaData metaDataDTO = riotService.loadMetaDataInfo(gameList.get(0));
        GameInfo gameInfoDto = riotService.loadGameInfo(gameList.get(0));


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
        Match match = matchService.findById(l);
        metaData.putMatch(match);
        gameInfo.putMatch(match);
        assertEquals(match.getSummoner().getName(), summoner.getName());

    }

    @Test
    @Transactional
    public void mata데이터불러오기() throws Exception {
        //given
//        MatchDTO matchDTO = riotService.loadGameInfo("KR_6815291259");
        MetaData metaData = riotService.loadMetaDataInfo("KR_6815291259");
        //when
        //then
        Assertions.assertEquals(metaData.getMatchId(),"KR_6815291259");
    }

    @Test
    @Transactional
    public void gameInfo데이터불러오기() throws Exception {
        //given
//        MatchDTO matchDTO = riotService.loadGameInfo("KR_6815291259");
        GameInfo gameInfo = riotService.loadGameInfo("KR_6815291259");
        //when
        //then
        Assertions.assertEquals(gameInfo.getGameMode(),"ARAM");
    }


}