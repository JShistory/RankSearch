package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.Participant;
import com.example.Foods.riotApi.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    @Transactional
    public Long saveParticipant(Participant participant, GameInfo gameInfo){

        participant.setGameInfo(gameInfo);
        Participant save = participantRepository.save(participant);
        return save.getId();
    }


    public Participant findById(Long id){
        return participantRepository.findById(id).get();
    }

    @Transactional
    public List<Participant> saveAll(List<Participant> participant, GameInfo gameInfo){
        List<Participant> newData = new ArrayList<>();
        for(Participant data : participant){
            data.setGameInfo(gameInfo);
            newData.add(data);
        }
        List<Participant> participantList = participantRepository.saveAll(newData);
        return participantList;
    }
}
