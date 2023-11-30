package com.example.Foods.riotApi.repository;

import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.entity.SummonerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiotRepository extends JpaRepository<Summoner,String> {
    SummonerDTO findByName(String summonerName);
}
