package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.Match;
import com.example.Foods.riotApi.entity.MetaData;
import com.example.Foods.riotApi.repository.MetaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetaDataService {
    private final MetaDataRepository metaDataRepository;

    public Long saveMetaData(MetaData metaData){
        MetaData save = metaDataRepository.save(metaData);
        return save.getId();
    }
}
