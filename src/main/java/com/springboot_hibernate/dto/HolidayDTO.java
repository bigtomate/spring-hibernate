package com.springboot_hibernate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
public class HolidayDTO {
    @JsonProperty("division")
    private String division;

    @JsonProperty("events")
    private List<EventDTO> events;

   /* "england-and-wales": {
        "division": "england-and-wales",
                "events": [
        {
            "title": "New Year’s Day",
                "date": "2024-01-01",
                "notes": "",
                "bunting": true
        },
        */
}
class EventDTO {

    @JsonProperty("title")
    private String title;
    private String notes;
    private Date date;
    private Boolean bunting;
}
