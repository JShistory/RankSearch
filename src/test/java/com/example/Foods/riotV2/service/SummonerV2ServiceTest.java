package com.example.Foods.riotV2.service;

import com.example.Foods.riotV2.dto.SummonerRequestDTO;
import com.example.Foods.riotV2.dto.SummonerResponseDTO;
import com.example.Foods.riotV2.dto.SummonerSaveRequestDTO;
import com.example.Foods.riotV2.utils.HttpConnect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class SummonerV2ServiceTest {
    @Autowired
    HttpConnect httpConnect;
    @Autowired
    SummonerV2Service summonerV2Service;
    @Test
    public void 소환사_검색_API_검증() throws IOException {
        String name = "1Byte";
        String tag = "KR1";
        SummonerSaveRequestDTO requestDTO = summonerV2Service.loadUserWithNameAndTag(name, tag);

        assertEquals(requestDTO.getName().replaceAll(" ",""), name);
        assertEquals(requestDTO.getTag(),tag);

        System.out.println(requestDTO.getName());
        System.out.println(requestDTO.getAccountId());
        System.out.println(requestDTO.getTag());
        System.out.println(requestDTO.getFindName());
        System.out.println(requestDTO.getPuuid());
        System.out.println(requestDTO.getRevisionDate());
        System.out.println(requestDTO.getSummonerLevel());

    }

    @Test
    public void 소환사_검색_API_저장() throws IOException {
        String name = "1Byte";
        String tag = "KR1";
        SummonerSaveRequestDTO requestDTO = summonerV2Service.loadUserWithNameAndTag(name, tag);

        Long saveId = summonerV2Service.save(requestDTO);

        SummonerResponseDTO responseDTO = summonerV2Service.findById(saveId);

        assertEquals(responseDTO.getTag(),tag);

    }
}