package com.springboot_hibernate.service;

import com.fasterxml.jackson.core.JsonParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot_hibernate.dto.BookDTO;
import com.springboot_hibernate.dto.BookXmlDTO;
import com.springboot_hibernate.dto.BooksDTO;
import com.springboot_hibernate.dto.BooksXmlDTO;
import com.springboot_hibernate.dto.PersonDTO;
import com.springboot_hibernate.dto.PersonResponseDTO;
import com.springboot_hibernate.entity.Book;
import com.springboot_hibernate.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
@Service
public class FileService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PersonResponseDTO retrievePersonFromValidJsonFile() throws IOException {
        File file =  resourceLoader.getResource(
                "classpath:service/no_root_ele_as_array.json").getFile();
        var personList = getTypeRef(file, new TypeReference<List<PersonDTO>>(){});
        return  PersonResponseDTO.builder().personDTOList(personList).build();
    }

    public PersonResponseDTO retrievePersonFromJsonL() throws IOException {
        File file =  resourceLoader.getResource(
                "classpath:service/no_root_ele.jsonl").getFile();
        List<PersonDTO> personDTOList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    PersonDTO person = getByLine(line, PersonDTO.class);
                    personDTOList.add(person);
                }
            }
        }

        return  PersonResponseDTO.builder().personDTOList(personDTOList).build();
    }

    public void marshal() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        BookDTO book =  BookDTO.builder().id(1L)
                .title("great book").published(OffsetDateTime.now()).build();
        BookDTO book2 =  BookDTO.builder().id(2L)
                .title("best seller book").published(OffsetDateTime.now()).build();
        BooksDTO booksDTO = BooksDTO.builder().bookList(Arrays.asList(book, book2)).build();


        // Optional: pretty print
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Write to file or string
       // String xml = xmlMapper.writeValueAsString(booksDTO);
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter("src/main/resources/service/books.xml"));
        xmlMapper.writeValue(writer, booksDTO);
        writer.close();
    }

    public List<BookDTO> unmarshal() throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/service/books.xml"));
        BooksDTO booksDTO = xmlMapper.readValue(br, BooksDTO.class);
        return booksDTO.getBookList();
    }

    public BooksDTO saveBooks(List<BookDTO> bookList) {
     List<Book> books = bookList.stream()
                .map(element -> modelMapper.map(element, Book.class)).toList();
        List<Book> savedBooks = bookRepository.saveAll(books);
       return BooksDTO.builder().bookList(savedBooks.stream()
                .map(element -> modelMapper.map(element, BookDTO.class)).toList()).build();

    }

    private <T> T get(File file, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, type);
    }

    private <T> T getByLine(String line, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); // optional, if fields are unquoted
        return mapper.readValue(line, type);
    }

    private <T> T getTypeRef(File file, TypeReference<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, type);
    }

    protected void marshalWithJaxb() throws JAXBException, IOException {
        BookXmlDTO book = new BookXmlDTO();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setDate(OffsetDateTime.now());
        BookXmlDTO book2 = new BookXmlDTO();
        book2.setId(2L);
        book2.setName("Book2");
        book2.setAuthor("Author2");
        book2.setDate(OffsetDateTime.now());

        BooksXmlDTO booksDTO = BooksXmlDTO.builder().books(Arrays.asList(book, book2)).build();
        JAXBContext context;
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter("src/main/resources/service/books_with_jaxb.xml"));
        context = JAXBContext.newInstance(BooksXmlDTO.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(booksDTO, writer);
        writer.close();
    }

    protected List<BookXmlDTO> unmarshalWithJaxb() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(BooksXmlDTO.class);
        Unmarshaller um = context.createUnmarshaller();
        BooksXmlDTO books = (BooksXmlDTO) um.unmarshal(new FileReader("src/main/resources/service/books_with_jaxb.xml"));
        return books.getBooks();
    }

}
