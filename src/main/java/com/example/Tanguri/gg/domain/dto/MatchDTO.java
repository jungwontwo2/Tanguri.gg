package com.example.Tanguri.gg.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MatchDTO {
    private Metadata metadata;
    private Info info;

    // Getters and Setters

    @Data
    public static class Metadata {
        private String dataVersion;
        private String matchId;
        private List<String> participants;

        // Getters and Setters
    }

    @Data
    public static class Info {
        private String endOfGameResult;
        private long gameCreation;
        private int gameDuration;
        private long gameEndTimestamp;
        private long gameId;
        private String gameMode;
        private String gameName;
        private long gameStartTimestamp;
        private String gameType;
        private String gameVersion;
        private int mapId;
        private List<Participant> participants;

        // Getters and Setters
        @Data
        public static class Participant {
            private int allInPings;
            private int assistMePings;
            private int assists;
            private int baronKills;
            private int basicPings;

            // Getters and Setters
        }
    }
}

