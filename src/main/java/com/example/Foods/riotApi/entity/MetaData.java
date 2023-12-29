package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dataVersion;
    private String matchId;
    @OneToOne(mappedBy = "metaData", cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "match_id")
    private MatchData matchData;

    @Column(length = 10000)
    private List<String> participants = new ArrayList<>();

    @Builder
    public MetaData(String dataVersion, String matchId, List<String> participants) {
        this.dataVersion = dataVersion;
        this.matchId = matchId;
        this.participants = participants;
    }

    public MetaData() {

    }

    public void putMatch(MatchData matchData){
        this.matchData = matchData;
    }
}