package com.springboot_hibernate.dto;

import lombok.Builder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "books")
@Builder
public class BooksXmlDTO {

    @XmlElement(name = "book", type = BookXmlDTO.class)
    private List<BookXmlDTO> books = new ArrayList<BookXmlDTO>();

    public BooksXmlDTO() {}

    public BooksXmlDTO(List<BookXmlDTO> books) {
        this.books = books;
    }

    public List<BookXmlDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookXmlDTO> books) {
        this.books = books;
    }
}