package com.springboot_hibernate.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FireStationTest {
@Autowired private FireStation fireStationService;
    @Test
    public void extractHolidays() {
        fireStationService.retrieveHolidaysByRegion("");
    }
}
