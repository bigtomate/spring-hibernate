package com.springboot_hibernate.hackerrank_restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FootBallResult {
    @JsonProperty("team2")
    String team2;

    @JsonProperty("team1")
    String team1;
    @JsonProperty("team1goals")
    int team1goals;
    @JsonProperty("team2goals")
    int team2goals;
}
