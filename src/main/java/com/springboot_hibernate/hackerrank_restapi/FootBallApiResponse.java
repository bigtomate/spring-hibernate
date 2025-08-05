package com.springboot_hibernate.hackerrank_restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FootBallApiResponse {
    @JsonProperty("total_pages")
    int totalPages;

    @JsonProperty("data")
    List<FootBallResult> data;

    @JsonProperty("total")
    int total;
}
