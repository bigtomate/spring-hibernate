package com.springboot_hibernate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("height")
    private Double height;
    @JsonProperty("weight")
    private Double weight;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contactChannel;
}
