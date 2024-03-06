package com.example.Foods.riotV2.serviceV2;

import com.example.Foods.riotV2.dtoV2.SummonerV2RequestDTO;
import com.example.Foods.riotV2.utilsV2.HttpConnect;
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
        SummonerV2RequestDTO requestDTO = summonerV2Service.loadUserWithNameAndTag(name, tag);

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
}