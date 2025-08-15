package com.springboot_hibernate.controller;

import com.springboot_hibernate.dto.HolidayDTO;
import com.springboot_hibernate.dto.PersonResponseDTO;
import com.springboot_hibernate.service.FireStation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HolidayController {
    @Autowired
    FireStation fireStationService;

    @Operation(summary = "get all person")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "person fetched successfully"),
            @ApiResponse(responseCode = "404", description = "person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/holidays")
    public ResponseEntity<HolidayDTO> retrieveHolidayByRegion(@RequestParam String region) throws IOException {
        return ResponseEntity.ok(fireStationService.retrieveHolidaysByRegion(region));
        // return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
