package com.springboot_hibernate.service;

import com.springboot_hibernate.exception.HolidayNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FireStationTest {
@Autowired private FireStation fireStationService;
    @Test
    public void extractHolidays() throws IOException {
        Exception exception = assertThrows(HolidayNotFoundException.class, () -> {
            fireStationService.retrieveHolidaysByRegion("");
        });
    }
}
