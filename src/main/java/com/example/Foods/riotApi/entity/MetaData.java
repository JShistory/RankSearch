package com.example.Foods.riotApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @OneToOne(mappedBy = "metaData")
    @JsonBackReference
    @JoinColumn(name = "match_id")
    private Match match;
    private List<String> participants;

    @Builder
    public MetaData(String dataVersion, String matchId, List<String> participants) {
        this.dataVersion = dataVersion;
        this.matchId = matchId;
        this.participants = participants;
    }

    public MetaData() {

    }

    public void putMatch(Match match){
        this.match = match;
    }
}
