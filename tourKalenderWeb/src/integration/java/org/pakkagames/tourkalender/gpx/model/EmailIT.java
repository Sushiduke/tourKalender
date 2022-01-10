package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.gpx.model.Email;
import org.pakkagames.tourkalender.gpx.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    @Test
    @DisplayName("[positive] when Email is valid in xml no exception is thrown")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/valid/email_valid.xml").getFile();

        Email emailResult = xmlMapper.readValue(xmlFile, Email.class);

        assertEquals("myEmailName", emailResult.getName());
        assertEquals("me@example.com", emailResult.getEmail());
        assertEquals(new Link("emaillink.com", "myEmailText", "myEmailType"), emailResult.getLink());
    }

}