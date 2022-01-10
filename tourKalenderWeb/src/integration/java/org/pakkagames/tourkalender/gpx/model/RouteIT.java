package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.gpx.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RouteIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    @Test
    @DisplayName("[negative] when Route data contains an invalid min number is given a ConstraintViolationException is expected")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/route_min_number.xml").getFile();

        assertThrows(ConstraintViolationException.class, () -> xmlMapper.readValue(xmlFile, Route.class));
    }

}