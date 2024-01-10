package com.example.Foods.riotApi.repository;

import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.entity.SummonerDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiotRepository extends JpaRepository<Summoner,Long> {
//    Summoner findByName(String name);
    Summoner findByNameAndTag(String name, String tag);
    List<Summoner> findByName(String name);
    List<Summoner> findByAccountId(String accountId);
    Summoner findByFindNameAndTag(String name, String tag);
    List<Summoner> findByFindName(String name);
}
