package com.springboot_hibernate.service;

import com.springboot_hibernate.dto.BookDTO;
import com.springboot_hibernate.dto.BookXmlDTO;
import com.springboot_hibernate.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FileServiceTest {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired FileService fileService;

    @Test
    public void testCreateXmlWithJaxb() throws JAXBException, IOException {
        fileService.marshalWithJaxb();
    }

    @Test
    public void testUnmarshalWithJaxb() throws JAXBException, IOException {
        List<BookXmlDTO> booklist = fileService.unmarshalWithJaxb();
        log.info(booklist.toString());
    }

    @Test
    public void testCreateXmlWithXmlMapper() throws IOException {
        fileService.marshal();
    }

    @Test
    public void testReadXmlWithXmlMapper() throws IOException {
        List<BookDTO> booklist =  fileService.unmarshal();
        log.info(booklist.toString());

    }

}
