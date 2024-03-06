package com.example.Foods.riotV2.serviceV2;

import com.example.Foods.riotV2.dtoV2.LeagueEntryV2RequestDTO;
import com.example.Foods.riotV2.dtoV2.LeagueEntryV2ResponseDTO;
import com.example.Foods.riotV2.repositoryV2.LeagueEntryV2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LeagueEntryV2Service {
    private final LeagueEntryV2Repository leagueEntryV2Repository;

    @Transactional
    public Long save(LeagueEntryV2RequestDTO requestDTO){
        return leagueEntryV2Repository.save(requestDTO.toEntity()).getId();
    }


}
