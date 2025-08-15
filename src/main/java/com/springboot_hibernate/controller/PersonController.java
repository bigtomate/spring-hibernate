package com.springboot_hibernate.controller;

import com.springboot_hibernate.dto.BookDTO;
import com.springboot_hibernate.dto.BooksDTO;
import com.springboot_hibernate.dto.PersonResponseDTO;
import com.springboot_hibernate.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PersonController {
    private FileService fileService;

    @Autowired
    public PersonController(FileService fileService) {
        this.fileService = fileService;
    }

    @Operation(summary = "get all person")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "person fetched successfully"),
            @ApiResponse(responseCode = "404", description = "person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/person")
    public ResponseEntity<PersonResponseDTO> retrieveAllPerson() throws IOException {
        return ResponseEntity.ok(fileService.retrievePersonFromValidJsonFile());
       // return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/person_jsonl")
    public ResponseEntity<PersonResponseDTO> retrieveAllPersonFromJsonL() throws IOException {
        return ResponseEntity.ok(fileService.retrievePersonFromJsonL());
    }

    @PostMapping("/create/book")
    public ResponseEntity<BooksDTO> createBook() throws IOException {
        List<BookDTO> booksDTOList =  fileService.unmarshal();
        booksDTOList.forEach(bookDTO -> {bookDTO.setId(null);});
        return ResponseEntity.ok(fileService.saveBooks(booksDTOList));
    }
}
