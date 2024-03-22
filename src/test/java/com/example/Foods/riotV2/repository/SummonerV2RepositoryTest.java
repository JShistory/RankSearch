package com.example.Foods.riotV2.domain;

import com.example.Foods.riotV2.dto.SummonerSaveRequestDTO;
import com.example.Foods.riotV2.repository.LeagueEntryV2Repository;
import com.example.Foods.riotV2.repository.SummonerV2Repository;
import com.example.Foods.riotV2.service.SummonerV2Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class SummonerV2RepositoryTest {
    @Autowired
    private LeagueEntryV2Repository leagueEntryV2Repository;
    @Autowired
    private SummonerV2Repository summonerV2Repository;
    @Autowired
    private SummonerV2Service summonerV2Service;
    @Transactional
    @Test
    public void Summoner_LeagueEntry연관관계확인(){
        SummonerV2 summonerV2 = SummonerV2.builder()
                .puuid("123")
                .tag("test")
                .findName("1byte")
                .name("1Byte")
                .profileIconId(1)
                .accountId("1")
                .revisionDate(1L)
                .build();

        LeagueEntryV2 leagueEntryV2 = LeagueEntryV2.builder()
                .leaguePoints(1)
                .leagueId("12")
                .tier("s")
                .wins(1)
                .losses(1)
                .rank("12")
                .queueType("123")
                .build();

        summonerV2.addLeagueEntry(leagueEntryV2);
        summonerV2Repository.save(summonerV2);

        String savedTier = "s";
        Optional<LeagueEntryV2> saveId = leagueEntryV2Repository.findById(leagueEntryV2.getId());

        assertEquals(savedTier,saveId.get().getTier());
    }

    @Transactional
    @Test
    public void Query_확인(){
        SummonerV2 summonerV2 = new SummonerV2();
        String name = "1Byte";
        String tag = "KR1";
        SummonerSaveRequestDTO requestDTO = summonerV2Service.loadUserWithNameAndTag(name, tag);
        summonerV2Service.save(requestDTO);

        List<SummonerV2> summoners = summonerV2Repository.findByFindNameAndTag("1byte", "KR1");

        assertEquals(1, summoners.size());
        assertEquals("1byte",summoners.get(0).getFindName());
    }

}