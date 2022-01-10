package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.gpx.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class AuthorIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    @Test
    @DisplayName("[positive] when Author is valid in xml no exception is thrown")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/valid/author_valid.xml").getFile();

        assertDoesNotThrow(() -> xmlMapper.readValue(xmlFile, Author.class));
    }

}