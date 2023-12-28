package com.example.Foods.riotApi.repository;

import com.example.Foods.riotApi.entity.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoRepository extends JpaRepository<GameInfo, Long> {
    GameInfo findByGameIdIs(Long gameId);
}
