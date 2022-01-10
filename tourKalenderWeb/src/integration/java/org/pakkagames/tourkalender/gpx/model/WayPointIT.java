package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.pakkagames.tourkalender.gpx.model.WayPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WayPointIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    private static Stream<Arguments> generateInvalidTestData() {
        return Stream.of(Arguments.of("/testfiles/model/validation/waypoint_min_latitude.xml"),
                Arguments.of("/testfiles/model/validation/waypoint_max_latitude.xml"),
                Arguments.of("/testfiles/model/validation/waypoint_max_longitude.xml"),
                Arguments.of("/testfiles/model/validation/waypoint_max_longitude.xml"));
    }


    @Test
    @DisplayName("[positive] when Waypoint is valid and all fields are filled in xml, the values can be read")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/valid/waypoint_valid.xml").getFile();

        WayPoint wayPointResult = xmlMapper.readValue(xmlFile, WayPoint.class);
        assertEquals(new BigDecimal("1.1"), wayPointResult.getLatitude());
        assertEquals(new BigDecimal("2.2"), wayPointResult.getLongitude());
        assertEquals("3.3", wayPointResult.getElevation());
        assertEquals(OffsetDateTime.of(2021, 12, 6, 22, 20, 30, 0, ZoneOffset.UTC), wayPointResult.getTime());
        assertEquals(new BigDecimal("4.4"), wayPointResult.getMagneticVariation());
        assertEquals(new BigDecimal("5.5"), wayPointResult.getGeoIdHeight());
        assertEquals("myName", wayPointResult.getName());
        assertEquals("http://www.example.com", wayPointResult.getComment());
        assertEquals("myDescription", wayPointResult.getDescription());
        assertEquals("mySource", wayPointResult.getSource());
        assertEquals("myType", wayPointResult.getType());
        // TODO add all members
    }

    @ParameterizedTest
    @MethodSource("generateInvalidTestData")
    @DisplayName("[negative] when WayPoint data contains an invalid min latitude is given a ConstraintViolationException is expected")
    void validationMinLatitudeTest(String fileContainingInvalidValues) throws IOException {
        File xmlFile = resourceLoader.getResource(fileContainingInvalidValues).getFile();

        assertThrows(ConstraintViolationException.class, () -> xmlMapper.readValue(xmlFile, WayPoint.class),
                "a ConstraintViolationException is expected during deserialization of file: " + fileContainingInvalidValues);
    }

}