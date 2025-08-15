package com.springboot_hibernate.dto;

import com.springboot_hibernate.service.OffsetDateTimeAdapter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.OffsetDateTime;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "name", "date" })
@ToString
public class BookXmlDTO {
    private Long id;
    private String name;
    private String author;
    private OffsetDateTime published;

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "date")
    @XmlJavaTypeAdapter(OffsetDateTimeAdapter.class)
    public void setDate(OffsetDateTime date) {
        this.published = date;
    }

    @XmlTransient
    public void setAuthor(String author) {
        this.author = author;
    }

    public OffsetDateTime getDate() {
        return published;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }
}