package com.springboot_hibernate.service;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeAdapter extends XmlAdapter<String, OffsetDateTime> {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX");

    @Override
    public OffsetDateTime unmarshal(String v) throws Exception {
        return OffsetDateTime.parse(v, formatter);
    }

    @Override
    public String marshal(OffsetDateTime v) throws Exception {
        return v.format(formatter);
    }
}