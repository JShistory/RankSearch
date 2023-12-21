package com.example.Foods.riotApi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Foods.riotApi.entity.Summoner;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RiotServiceTest {

    @Autowired
    private RiotService riotService;
    @Autowired
    private EntityManager em;

//    @Test
//    public void 소환사조회() throws Exception {
//        //given
//        Summoner summoner1 = riotService.loadUserWithTag("1Byte", "KR1");
//        //when
//
//        //then
//    }

    @Test
    public void 중복소환사() throws Exception {
        //given
        String input = "괴물쥐-고라파덕";
        String[] nameAndTag = riotService.splitNameAndTag(input);
        Summoner summoner1 = riotService.loadUserWithTag(nameAndTag[0],nameAndTag[1]);
        Summoner summoner2 = riotService.loadUserWithTag(nameAndTag[0],nameAndTag[1]);
        //when
        Summoner data1 = riotService.findById(summoner2.getDataId());
        Summoner data2 = riotService.findById(summoner1.getDataId());
        //then

        Assertions.assertEquals(data1.getId(),data2.getId());

    }

    @Test
    @Transactional
    public void findByNameAndTag동작() throws Exception {
        //given
        String input = "괴물쥐-KR3";
        String[] nameAndTag = riotService.splitNameAndTag(input);
        Summoner summoner1 = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        //when
        em.flush();
        Summoner summoner = riotService.findByNameAndTag(nameAndTag[0], nameAndTag[1]);
        //then
        assertEquals(summoner.getId(), summoner1.getId());
    }
    @Test
    @Transactional
    public void findByNameAndTag동작V2() throws Exception {
        //given
        String input = "괴물쥐-고라파덕";
        String[] nameAndTag = riotService.splitNameAndTag(input);
        Summoner summoner1 = riotService.loadUserWithTag(nameAndTag[0], nameAndTag[1]);
        //when
        em.flush();
        Summoner summoner = riotService.findByNameAndTag(nameAndTag[0], nameAndTag[1]);
        //then
        assertEquals(summoner.getId(), summoner1.getId());
    }
}