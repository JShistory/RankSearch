package com.example.Foods.riotApi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Foods.riotApi.entity.Summoner;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class RiotApiServiceTest {

    @Autowired
    private RiotApiService riotApiService;
    @Autowired
    private EntityManager em;
    @Autowired
    private SummonerService summonerService;

//    @Test
//    public void 소환사조회() throws Exception {
//        //given
//        Summoner summoner1 = riotService.loadUserWithTag("1Byte", "KR1");
//        //whenloadParticipantsGameInfo
//
//        //then
//    }

    @Test
    public void 중복소환사() throws Exception {
        //given
        String input = "1Byte";
        String[] nameAndTag = riotApiService.splitNameAndTag(input);
        Summoner summoner1 = riotApiService.loadUserWithTag(nameAndTag[0],nameAndTag[1]);
        Summoner summoner2 = riotApiService.loadUserWithTag(nameAndTag[0],nameAndTag[1]);
        summonerService.saveUser(summoner1,nameAndTag[1],nameAndTag[0]);
        summonerService.saveUser(summoner2,nameAndTag[1],nameAndTag[0]);
        //when
        Summoner data1 = summonerService.findById(summoner2.getDataId());
        Summoner data2 = summonerService.findById(summoner1.getDataId());
        //then

        Assertions.assertEquals(data1.getId(),data2.getId());

    }

    @Test
    @Transactional
    @DisplayName("findByAndTag로 값을 찾았을 때 저장한 값과 아이디가 같아야됨V1")
    public void findByNameAndTag동작V1() throws Exception {
        //given
        String input = "괴물쥐-KR3";
        String[] nameAndTag = riotApiService.splitNameAndTag(input);
        Summoner summoner1 = riotApiService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        summonerService.saveUser(summoner1,nameAndTag[1],nameAndTag[0]);
        //when
        em.flush();
        Summoner summoner = summonerService.findByNameAndTag(nameAndTag[0], nameAndTag[1]);
        //then
        assertEquals(summoner.getId(), summoner1.getId());
    }
    @Test
    @Transactional
    @DisplayName("findByAndTag로 값을 찾았을 때 저장한 값과 아이디가 같아야됨V2")
    public void findByNameAndTag동작V2() throws Exception {
        //given
        String input = "괴물쥐-고라파덕";
        String[] nameAndTag = riotApiService.splitNameAndTag(input);
        Summoner summoner1 = riotApiService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        summonerService.saveUser(summoner1,nameAndTag[1],nameAndTag[0]);

        //when
        em.flush();
        Summoner summoner = summonerService.findByNameAndTag(nameAndTag[0], nameAndTag[1]);
        //then
        assertEquals(summoner.getId(), summoner1.getId());
    }



}