package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.gpx.model.Gpx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GpxIT {

  private final ResourceLoader resourceLoader = new DefaultResourceLoader();

  @Autowired private ObjectMapper xmlMapper;

  @Test
  void whenJavaGotFromXmlFile_thenCorrect() throws IOException {
    File xmlFile = resourceLoader.getResource("/testfiles/garmin_two_same_trkpt.xml").getFile();

    Gpx gpx = xmlMapper.readValue(xmlFile, Gpx.class);
    assertNotNull(gpx);
    assertNotNull(gpx.getTracks());
    assertNotNull(gpx.getTracks().get(0));
    assertNotNull(gpx.getTracks().get(0).getTrackSegments());
    assertEquals(2, gpx.getTracks().get(0).getTrackSegments().size());
    assertEquals(10, gpx.getTracks().get(0).getTrackSegments().get(0).getWayPoints().size());
    assertEquals(5, gpx.getTracks().get(0).getTrackSegments().get(1).getWayPoints().size());
  }

  @Test
  void whenTwoSameTrksegAreGivenBothAreEqualTest() throws IOException {
    File xmlFile = resourceLoader.getResource("/testfiles/garmin_two_same_trkpt.xml").getFile();

    Gpx gpx = xmlMapper.readValue(xmlFile, Gpx.class);

    assertEquals(gpx.getTracks().get(0).getTrackSegments(), gpx.getTracks().get(0).getTrackSegments(), "the two trkseg should be equal");
  }
}
