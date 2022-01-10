package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.pakkagames.tourkalender.gpx.model.Bounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BoundsIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    private static Stream<Arguments> generateInvalidTestData() {
        return Stream.of(Arguments.of("/testfiles/model/validation/bounds_max_latitude.xml"),
                Arguments.of("/testfiles/model/validation/bounds_max_longitude.xml"),
                Arguments.of("/testfiles/model/validation/bounds_min_latitude.xml"),
                Arguments.of("/testfiles/model/validation/bounds_min_longitude.xml"));
    }

    @Test
    @DisplayName("[positive] when Bounds is valid in xml no exception is thrown")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/valid/bounds_valid.xml").getFile();

        assertDoesNotThrow(() -> xmlMapper.readValue(xmlFile, Bounds.class));
    }

    @ParameterizedTest
    @MethodSource("generateInvalidTestData")
    @DisplayName("[negative] when a invalid min latitude is given a ConstraintViolationException is expected")
    void validationMinLatitudeTest(String fileContainingInvalidValues) throws IOException {
        File xmlFile = resourceLoader.getResource(fileContainingInvalidValues).getFile();

        assertThrows(ConstraintViolationException.class, () -> xmlMapper.readValue(xmlFile, Bounds.class),
                "a ConstraintViolationException is expected during deserialization of file: " + fileContainingInvalidValues);
    }
}