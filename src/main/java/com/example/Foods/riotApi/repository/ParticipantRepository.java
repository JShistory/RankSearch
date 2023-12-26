package com.example.Foods.riotApi.repository;

import com.example.Foods.riotApi.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
