package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.gpx.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MetadataIT {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ObjectMapper xmlMapper;

    @Test
    @DisplayName("[positive] when Metadata is valid and all fields are filled in xml, the values can be read")
    void validationTest() throws IOException {
        File xmlFile = resourceLoader.getResource("/testfiles/model/validation/valid/metadata_valid.xml").getFile();

        Metadata metadataResult = xmlMapper.readValue(xmlFile, Metadata.class);
        assertEquals("myName", metadataResult.getName());
        assertEquals("myDesc", metadataResult.getDescription());
        assertEquals(createAuthor(), metadataResult.getAuthor());
        assertEquals(new Copyright("myAuthor", "myYear", "myLicence"), metadataResult.getCopyright());
        assertEquals(Collections.singleton(new Link("www.myLink.com", "myText", "myType")), metadataResult.getLinks());
        assertEquals(OffsetDateTime.of(2021, 12, 6, 22, 20, 30, 0, ZoneOffset.UTC), metadataResult.getTime());
        assertEquals("myKeyword", metadataResult.getKeywords());
        assertEquals(new Bounds(BigDecimal.valueOf(20.0), BigDecimal.valueOf(21.0), BigDecimal.valueOf(22.0), BigDecimal.valueOf(23.0)), metadataResult.getBounds());
    }

    private Author createAuthor() {
        Author myAuthor = new Author();
        myAuthor.setName("myAuthorName");
        myAuthor.setLink(new Link("www.example.com", "myText", "myAuthorType"));
        myAuthor.setEmail(new Email("myEmailName", "me@example.com", new Link("emaillink.com", "myEmailText", "myEmailType")));
        return myAuthor;
    }
}