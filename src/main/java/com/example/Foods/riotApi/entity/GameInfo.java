package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class GameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long gameCreation;
    private Long gameDuration;
    private Long gameEndTimestamp;
    private Long gameStartTimestamp;
    private Long gameId;
    private String gameMode;
    private String gameName;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private int queueId;
    @JsonManagedReference
    @OneToMany(mappedBy = "gameInfo", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<>();

    @JsonBackReference
    @OneToOne(mappedBy = "gameInfo", cascade = CascadeType.ALL)
    private MatchData matchData;

    @Builder
    public GameInfo(Long gameCreation, Long gameDuration, Long gameEndTimestamp, Long gameId, String gameMode, String gameName, Long gameStartTimestamp, String gameType
    ,String gameVersion, int mapId, int queueId){
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameEndTimestamp =  gameEndTimestamp;
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.gameStartTimestamp = gameStartTimestamp;
        this.gameType = gameType;
        this.gameVersion = gameVersion;
        this.mapId = mapId;
        this.queueId = queueId;
    }

    public GameInfo() {

    }
    public void putMatch(MatchData matchData){
        this.matchData = matchData;
    }
    public void putParticipants(Participant participant){
        while(participants.size() > 20){
            participants.remove(0);
        }
        this.participants.add(participant);
    }
}
