package com.example.Foods.riotApi.service;

import com.example.Foods.riotApi.entity.GameInfo;
import com.example.Foods.riotApi.entity.Participant;
import com.example.Foods.riotApi.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public Long saveParticipant(Participant participant, GameInfo gameInfo){
        participant.setGameInfo(gameInfo);
        Participant save = participantRepository.save(participant);
        return save.getId();
    }

    public Participant findById(Long id){
        return participantRepository.findById(id).get();
    }
}
