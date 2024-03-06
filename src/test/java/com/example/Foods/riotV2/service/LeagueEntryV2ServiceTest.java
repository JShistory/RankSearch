package com.example.Foods.riotV2.service;

import com.example.Foods.riotV2.dto.LeagueEntryResponseDTO;
import com.example.Foods.riotV2.dto.LeagueEntrySaveRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LeagueEntryV2ServiceTest {

    @Autowired
    LeagueEntryV2Service leagueEntryV2Service;

    @Test
    public void 리그_정보_API_검증() {
        String encryptedId = "zGnc83b-eG_9YZO6mnEU-AvSuI6i98vscbnAm3s1xE-Tx0c";

        HashMap<String, LeagueEntrySaveRequestDTO> entryResponseDTOS = leagueEntryV2Service.loadLeagueEntry(encryptedId);
        if (entryResponseDTOS.containsKey("솔로랭크")) {
            assertEquals(entryResponseDTOS.get("솔로랭크").getQueueType(), "솔로랭크");
        }
        if (entryResponseDTOS.containsKey("자유랭크")) {
            assertEquals(entryResponseDTOS.get("자유랭크").getQueueType(), "자유랭크");
        }
    }

    @Test
    public void 리그_정보_API_저장() {
        String encryptedId = "zGnc83b-eG_9YZO6mnEU-AvSuI6i98vscbnAm3s1xE-Tx0c";

        HashMap<String, LeagueEntrySaveRequestDTO> entryResponseDTOS = leagueEntryV2Service.loadLeagueEntry(encryptedId);
        if (entryResponseDTOS.containsKey("솔로랭크")) {
            LeagueEntrySaveRequestDTO soloLeague = entryResponseDTOS.get("솔로랭크");
            Long saveSoloId = leagueEntryV2Service.save(soloLeague);
            LeagueEntryResponseDTO responseDTO = leagueEntryV2Service.findById(saveSoloId);
            assertEquals(responseDTO.getQueueType(), "솔로랭크");
        }
        if (entryResponseDTOS.containsKey("자유랭크")) {
            LeagueEntrySaveRequestDTO flexLeague = entryResponseDTOS.get("자유랭크");
            Long saveFlexId = leagueEntryV2Service.save(flexLeague);
            LeagueEntryResponseDTO responseDTO = leagueEntryV2Service.findById(saveFlexId);
            assertEquals(responseDTO.getQueueType(), "자유랭크");
        }
    }
}