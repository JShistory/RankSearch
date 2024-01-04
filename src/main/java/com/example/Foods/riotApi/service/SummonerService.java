package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.Summoner;
import com.example.Foods.riotApi.repository.RiotRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Summoner> summonerList(String name) {
        return riotRepository.findByName(name);
    }
    @Transactional(readOnly = true)
    public Summoner findByNameAndTag(String name, String tag) {
        return riotRepository.findByNameAndTag(name, tag);
    }
    @Transactional(readOnly = true)
    public Summoner findByFindNameAndTag(String name, String tag) {
        return riotRepository.findByFindNameAndTag(name.toLowerCase().replaceAll(" ",""), tag);
    }
    @Transactional(readOnly = true)
    public Summoner findById(Long id) {
        return riotRepository.findById(id).get();
    }
    @Transactional(readOnly = true)
    public List<Summoner> findAll(){
        return riotRepository.findAll();
    }

}