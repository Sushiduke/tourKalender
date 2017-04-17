package org.pakkagames.tourkalender.service;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pakkagames.tourkalender.gpx.domain.Track;
import org.pakkagames.tourkalender.gpx.service.TrackParserService;
import org.pakkagames.tourkalender.gpx.service.TrackParserServiceTest;
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
public class TrackCalculationTest {

	final Logger LOGGER = LoggerFactory.getLogger(TrackCalculationTest.class);
	final String EXAMPLE_WAYPOINT_FILE = "/Hilden_Metro.gpx";

	@Autowired
	TrackParserService trackParserService;

	@Autowired
	TrackCalculationService trackCalculationService;

	Node firstChild;

	@Before
	public void setUp() {

		InputStream input = getClass().getResourceAsStream(EXAMPLE_WAYPOINT_FILE);
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
		
		Track track = null;
		try {
			track = trackParserService.parseTrack(firstChild);
		}		catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(track);
		double distance = trackCalculationService.calculateDistance(track);
		Assert.assertNotNull(distance);
		
		distance = trackCalculationService.calculateDistanceClassic(track);
		Assert.assertNotNull(distance);

		Double sumLongitude = new Double(0);
		Double sumLatitude = new Double(0);
		sumLongitude = sumLongitude + Math.abs(6.827280828729272 - 6.828197138383985);
		sumLatitude = sumLatitude + Math.abs(51.234644735231996 - 51.2347153108567);
		distance = Math.sqrt(Math.pow(111.6 * sumLatitude, 2) + Math.pow(111.6 * Math.cos(51.234644735231996) * sumLongitude, 2));
		LOGGER.info("distance: {}", distance);
	}
}
