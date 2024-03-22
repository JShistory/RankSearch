package com.example.Foods.riotV2.repository;


import com.example.Foods.riotV2.domain.SummonerV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SummonerV2Repository extends JpaRepository<SummonerV2, Long> {
    @Query("select s From SummonerV2 s where s.findName = :name and s.tag = :tag")
    List<SummonerV2> findByFindNameAndTag(@Param("name") String findName, @Param("tag") String tag);
}
