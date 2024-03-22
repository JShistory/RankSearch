package com.example.Foods;

import com.example.Foods.api.RiotApiController;
import com.example.Foods.riotV2.controller.RiotApiV2Controller;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class timeTest {
    @Autowired
    RiotApiController riotApiController;
    @Autowired
    RiotApiV2Controller riotApiV2Controller;

    @Test
    public void 시간_테스트() throws IOException, ParseException {
        String name = "화둔술사";
        String tag = "KR1";
        long start = System.currentTimeMillis();
        riotApiController.findSummoner(name);
        long end = System. currentTimeMillis();

        long time1 = end - start;


        start = System.currentTimeMillis();
        riotApiV2Controller.summoner(name,tag);
        end = System. currentTimeMillis();

        long time2 = end - start;

        System.out.println("기존 코드 수행 시간 " + time1);

        System.out.println("개선 코드 수행 시간 " + time2);
    }
}
