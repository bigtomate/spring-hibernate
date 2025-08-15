package com.springboot_hibernate.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@JacksonXmlRootElement(localName = "books")
@AllArgsConstructor
@NoArgsConstructor
public class BooksDTO {
    @JacksonXmlElementWrapper(useWrapping = true)
    @JacksonXmlProperty(localName = "book")
    private List<BookDTO> bookList;

}


