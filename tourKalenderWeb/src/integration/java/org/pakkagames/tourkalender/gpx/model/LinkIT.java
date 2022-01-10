package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.gpx.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LinkIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    @Test
    @DisplayName("[positive] when Link is valid and all fields are filled in xml, the values can be read")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/valid/link_valid.xml").getFile();

        Link linkResult = xmlMapper.readValue(xmlFile, Link.class);
        assertEquals("http://www.example.com", linkResult.getHref());
        assertEquals("myText", linkResult.getText());
        assertEquals("myType", linkResult.getType());
    }

    @Test
    @DisplayName("[negative] when no href attribute is given a ValueInstantiationException is expected")
    void validationMinLatitudeTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/link_missing_href.xml").getFile();

        assertThrows(ValueInstantiationException.class, () -> xmlMapper.readValue(xmlFile, Link.class),
                "a ConstraintViolationException is expected during deserialization of file: " + xmlFile);
    }

}