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
//        summoner.setPrevId(summoner.getName());
//        summoner.setName(name);
        summoner.setTag(tag);
        summoner.setFindName(summoner.getName().toLowerCase().replaceAll(" ",""));
        riotRepository.save(summoner);
        return summoner.getDataId();
    }

    public List<Summoner> summonerList(String name) {
        return riotRepository.findByName(name);
    }

    public Summoner findByNameAndTag(String name, String tag) {
        return riotRepository.findByNameAndTag(name, tag);
    }
    public Summoner findByFindNameAndTag(String name, String tag) {
        return riotRepository.findByFindNameAndTag(name.toLowerCase().replaceAll(" ",""), tag);
    }

    public Summoner findById(Long id) {
        return riotRepository.findById(id).get();
    }

    public List<Summoner> findAll(){
        return riotRepository.findAll();
    }

}