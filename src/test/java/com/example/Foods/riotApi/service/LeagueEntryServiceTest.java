package com.example.Foods.riotApi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Foods.riotApi.entity.LeagueEntry;
import com.example.Foods.riotApi.entity.LeagueEntryDTO;
import com.example.Foods.riotApi.entity.Summoner;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class LeagueEntryServiceTest {
    @Autowired
    private LeagueEntryService leagueEntryService;
    @Autowired
    private RiotService riotService;
    @Autowired
    private EntityManager em;
    @Autowired
    private SummonerService summonerService;
    @Test
    @Transactional
    public void 랭크정보조회() throws Exception {
        //given
        String input = "괴물쥐-KR3";
        String[] nameAndTag = riotService.splitNameAndTag(input);
        Summoner summoner = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        summonerService.saveUser(summoner,nameAndTag[1],nameAndTag[0]);


        //when
        LeagueEntryDTO leagueEntryDTO = riotService.loadSoloRank(summoner.getId(), summoner.getDataId());
        Long l = leagueEntryService.saveRank(summoner,leagueEntryDTO);
        LeagueEntry id1 = leagueEntryService.findById(l);
        summoner.putLeagueData(id1);
        em.flush();
        LeagueEntryDTO loadRank = riotService.loadSoloRank(summoner.getId(), summoner.getDataId());

        //then
        LeagueEntry id = leagueEntryService.findById(l);
        Assertions.assertEquals(id.getRank(), loadRank.getRank());
    }

    @Test
    @Transactional
    public void 랭크내용저장() throws Exception {
        //given
        String input = "괴물쥐-KR3";
        String[] nameAndTag = riotService.splitNameAndTag(input);
        Summoner summoner = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        summonerService.saveUser(summoner,nameAndTag[1],nameAndTag[0]);
        //when
        LeagueEntryDTO leagueEntryDTO = riotService.loadSoloRank(summoner.getId(),summoner.getDataId());
        Long l = leagueEntryService.saveRank(summoner,leagueEntryDTO);
        LeagueEntry id1 = leagueEntryService.findById(l);
        summoner.putLeagueData(id1);
        em.flush();
        Summoner byId = summonerService.findById(summoner.getDataId());
        LeagueEntry id = leagueEntryService.findById(l);

        //then
        Assertions.assertEquals(byId.getDataId() , id.getSummoner().getDataId());
    }

    @Test
    @Transactional
    public void findBySummoner() throws Exception {
        //given
        String input = "괴물쥐-KR3";
        String[] nameAndTag = riotService.splitNameAndTag(input);
        Summoner summoner = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        summonerService.saveUser(summoner,nameAndTag[1],nameAndTag[0]);
        //when
        LeagueEntryDTO leagueEntryDTO1 = riotService.loadSoloRank(summoner.getId(),summoner.getDataId());
        Long l1 = leagueEntryService.saveRank(summoner,leagueEntryDTO1);
        LeagueEntry id1 = leagueEntryService.findById(l1);

        LeagueEntryDTO leagueEntryDTO2 = riotService.loadSoloRank(summoner.getId(),summoner.getDataId());
        Long l2 = leagueEntryService.saveRank(summoner,leagueEntryDTO2);
        LeagueEntry id2 = leagueEntryService.findById(l2);

        LeagueEntryDTO leagueEntryDTO3 = riotService.loadSoloRank(summoner.getId(),summoner.getDataId());
        Long l3 = leagueEntryService.saveRank(summoner,leagueEntryDTO3);
        LeagueEntry id3 = leagueEntryService.findById(l3);
        summoner.putLeagueData(id1);
        summoner.putLeagueData(id2);
        summoner.putLeagueData(id3);
        em.flush();

        //then
        Assertions.assertEquals(leagueEntryService.findAll().size(),1);
    }

}