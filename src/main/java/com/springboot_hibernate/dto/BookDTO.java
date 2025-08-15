package com.springboot_hibernate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @JacksonXmlProperty(isAttribute = true)
    private Long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssXXX")
    private OffsetDateTime published;
}
