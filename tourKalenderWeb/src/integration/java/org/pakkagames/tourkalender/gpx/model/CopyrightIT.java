package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.gpx.model.Copyright;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CopyrightIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    @Test
    @DisplayName("[positive] when Copyright is valid in xml no exception is thrown")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/valid/copyright_valid.xml").getFile();

        assertDoesNotThrow(() -> xmlMapper.readValue(xmlFile, Copyright.class));
    }

    @Test
    @DisplayName("[negative] when data of Copyright misses href attribute is given a ValueInstantiationException is expected")
    void validationMinLatitudeTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/copyright_missing_author.xml").getFile();

        assertThrows(ValueInstantiationException.class, () -> xmlMapper.readValue(xmlFile, Copyright.class),
                "a ConstraintViolationException is expected during deserialization of file: " + xmlFile);
    }
}