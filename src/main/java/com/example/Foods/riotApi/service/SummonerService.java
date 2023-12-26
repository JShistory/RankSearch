package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.repository.RiotRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummonerService {

    private final RiotRepository riotRepository;


    @Transactional
    public Long saveUser(Summoner summoner, String tag, String name) {
        summoner.setPrevId(summoner.getName());
        summoner.setName(name);
        summoner.setTag(tag);
        riotRepository.save(summoner);
        return summoner.getDataId();
    }

    public List<Summoner> summonerList(String name) {
        return riotRepository.findByName(name);
    }

    public Summoner findByNameAndTag(String summonerName, String tag) {
        return riotRepository.findByNameAndTag(summonerName, tag);
    }

    public Summoner findById(Long id) {
        return riotRepository.findById(id).get();
    }

    public List<Summoner> findAll(){
        return riotRepository.findAll();
    }

}
