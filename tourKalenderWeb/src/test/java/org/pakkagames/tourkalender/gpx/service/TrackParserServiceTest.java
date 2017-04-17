package org.pakkagames.tourkalender.gpx.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pakkagames.tourkalender.gpx.domain.Track;
import org.pakkagames.tourkalender.gpx.domain.WayPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Testclass takes an example file containing only the one TrackSegment and several WayPoints. <br>
 * Tested elements
 * <ul>
 * <li>Number of WayPoints</li>
 * <li>description</li>
 * <li>coordinates (latitude, longitude and elevation)</li>
 * </ul>
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.pakkagames.tourkalender.testconfig.SimpleTestConfigurationNoDatabase.class })
@ActiveProfiles(value = "test")
public class TrackParserServiceTest {

	final Logger LOGGER = LoggerFactory.getLogger(TrackParserServiceTest.class);
	final String EXAMPLE_WAYPOINT_FILE = "/track_test.gpx";

	@Autowired
	TrackParserService trackParserService;

	Node firstChild;

	@Before
	public void setUp() {

		InputStream input = getClass().getResourceAsStream("/track_test.gpx");
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(input);
			firstChild = doc.getFirstChild();
			input.close();
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Count the number of WayPoints in a TrackSegment from the testdata and compare it with the number EXPECTED_AMOUNT.
	 */
	@Test
	public void parseAmountOfWayPointsInTrackSegment() {
		int EXPECTED_AMOUNT = 27;
		Track track = null;
		try {
			track = trackParserService.parseTrack(firstChild);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(track);
		Assert.assertEquals("Number of WayPoints", EXPECTED_AMOUNT, track.getTrackSegments().get(0).getWayPoints().size());
	}

	/**
	 * Check the description in every WayPoint of in a TrackSegment from the testdata and compare it with the number EXPECTED_AMOUNT.
	 */
	@Test
	public void parseDescriptionOfWayPointsInTrackSegment() {
		String EXPECTED_STRING = "Trackpoint ";
		Track track = null;
		try {
			track = trackParserService.parseTrack(firstChild);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(track);

		Iterator<WayPoint> iterator = track.getTrackSegments().get(0).getWayPoints().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			WayPoint wayPoint = iterator.next();
			i++;
			Assert.assertEquals("Description", EXPECTED_STRING + i, wayPoint.getDescription());
		}
	}

	/**
	 * Check both coordinates of the WayPoint No 16 from the testdata.
	 */
	@Test
	public void parseCoordinatesOfWayPointsInTrackSegment() {
		double EXPECTED_LATITUDE = 51.266332436352968;
		double EXPECTED_LONGITUDE = 6.848857020959258;
		double EXPECTED_ELEVATION = 104.9210205;

		double DELTA = 0.0;
		
		Track track = null;
		try {
			track = trackParserService.parseTrack(firstChild);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(track);

		WayPoint wayPoint16 = track.getTrackSegments().get(0).getWayPoints().get(15);
		Assert.assertEquals("Latitude: ", EXPECTED_LATITUDE, wayPoint16.getLatitude(), DELTA);
		Assert.assertEquals("Longitude: ", EXPECTED_LONGITUDE, wayPoint16.getLongitude(), DELTA);
		Assert.assertEquals("Elevation: ", EXPECTED_ELEVATION, wayPoint16.getElevation(), DELTA);
	}

	/**
	 * Check the description in every WayPoint of in a TrackSegment from the testdata and compare it with the number EXPECTED_AMOUNT.
	 */
	@Test
	public void parseNameOfWayPointsInTrackSegment() {
		String EXPECTED_NAME = "Name 10";

		Track track = null;
		try {
			track = trackParserService.parseTrack(firstChild);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(track);

		WayPoint wayPoint10 = track.getTrackSegments().get(0).getWayPoints().get(9);
		Assert.assertEquals("Name: ", EXPECTED_NAME, wayPoint10.getName());
	}

}
