package com.springboot_hibernate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HolidayNotFoundException extends RuntimeException {
    public HolidayNotFoundException(String messages) {
        super(messages);
    }
}
