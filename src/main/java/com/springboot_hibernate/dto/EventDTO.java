package com.springboot_hibernate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDTO {

    @JsonProperty("title")
    private String title;
    @JsonProperty("notes")
    private String notes;
    //@JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("bunting")
    private Boolean bunting;
}
