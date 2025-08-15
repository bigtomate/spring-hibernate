package com.springboot_hibernate.config;
import com.springboot_hibernate.dto.BookDTO;
import com.springboot_hibernate.entity.Book;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();


        // 1. Register the converter FIRST (global, reusable)
        modelMapper.addConverter(new Converter<OffsetDateTime, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<OffsetDateTime, LocalDateTime> context) {
                OffsetDateTime source = context.getSource();
                return source == null ? null : source
                        .atZoneSameInstant(ZoneOffset.UTC)  // Convert to UTC
                        .toLocalDateTime();                 // Then extract LocalDateTime
            }
        });

        // Converter: LocalDateTime → OffsetDateTime
        modelMapper.addConverter(new Converter<LocalDateTime, OffsetDateTime>() {
            @Override
            public OffsetDateTime convert(MappingContext<LocalDateTime, OffsetDateTime> context) {
                LocalDateTime source = context.getSource();
                return source == null ? null : source.atOffset(ZoneOffset.UTC);
            }
        });


        // 2. Define type map for field renaming (title → name)
        modelMapper.createTypeMap(BookDTO.class, Book.class)
                .addMapping(BookDTO::getTitle, Book::setName); // title → name

        modelMapper.createTypeMap(Book.class, BookDTO.class)
                .addMappings(m -> {
                    m.map(Book::getName, BookDTO::setTitle);
                    m.map(Book::getId, BookDTO::setId);
                    m.map(src -> {
                        LocalDateTime pub = src.getPublished();
                        if (pub == null) return null;
                        return pub.atOffset(ZoneOffset.UTC);  // Assume stored in UTC
                    }, BookDTO::setPublished);
                });
        // Optional: Skip null values to avoid overwriting with null
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        return modelMapper;
    }
}