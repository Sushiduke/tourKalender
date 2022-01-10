package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.pakkagames.tourkalender.gpx.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PointIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    /**
     * Creates Arguments for a {@link ParameterizedTest}.
     *
     * @return Creates a {@link Stream} of {@link Arguments} containing the filename with invalid xml.
     */
    private static Stream<Arguments> generateInvalidTestData() {
        return Stream.of(Arguments.of("/testfiles/model/validation/point_max_latitude.xml"),
                Arguments.of("/testfiles/model/validation/point_max_longitude.xml"),
                Arguments.of("/testfiles/model/validation/point_min_latitude.xml"),
                Arguments.of("/testfiles/model/validation/point_min_longitude.xml"));
    }

    @Test
    @DisplayName("[positive] when Point is valid and all fields are filled in xml, the values can be read")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/valid/point_valid.xml").getFile();

        Point pointResult = xmlMapper.readValue(xmlFile, Point.class);
        assertEquals(new BigDecimal("89.3"), pointResult.getLatitude());
        assertEquals(new BigDecimal("6.8332"), pointResult.getLongitude());
        assertEquals(new BigDecimal("34.83"), pointResult.getElevation());
    }

    @ParameterizedTest
    @MethodSource("generateInvalidTestData")
    @DisplayName("[negative] when a invalid min latitude is given a ConstraintViolationException is expected")
    void validationMinLatitudeTest(String fileContainingInvalidValues) throws IOException {
        File xmlFile = resourceLoader.getResource(fileContainingInvalidValues).getFile();

        assertThrows(ConstraintViolationException.class, () -> xmlMapper.readValue(xmlFile, Point.class),
                "a ConstraintViolationException is expected during deserialization of file: " + fileContainingInvalidValues);
    }

}