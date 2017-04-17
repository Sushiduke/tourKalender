package org.pakkagames.tourkalender.gpx.service;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pakkagames.tourkalender.gpx.domain.GPX;
import org.pakkagames.tourkalender.gpx.domain.WayPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Testclass takes an example file and parse all WayPoint elements, that are located under <gpx> root.<br>
 * Tested elements <wpt>
 * <ul>
 * <li>Number of WayPoints</li>
 * <li>coordinates (latitude, longitude and elevation) of specific named elements</li>
 * </ul>
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.pakkagames.tourkalender.testconfig.SimpleTestConfigurationNoDatabase.class })
@ActiveProfiles(value = "test")
public class WayPointParserServiceTest {

	final Logger LOGGER = LoggerFactory.getLogger(WayPointParserServiceTest.class);
	final String EXAMPLE_WAYPOINT_FILE = "/waypoint_test.gpx";
	
	@Autowired
	GPXParserService gpxParserService;

	@Autowired
	WayPointParserService wayPointParserService;

	InputStream input;

	@Before
	public void setUp() {
		input = getClass().getResourceAsStream(EXAMPLE_WAYPOINT_FILE);
	}

	/**
	 * Count the number of WayPoints in a TrackSegment from the testdata and compare it with the number EXPECTED_AMOUNT.
	 */
	@Test
	public void parseAmountOfWayPointsInTrackSegment() {
		int EXPECTED_AMOUNT = 38;
		GPX gpx = null;
		HashSet<WayPoint> wayPoints = null;
		try {
			gpx = gpxParserService.parseGPXTour(input);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(gpx);
		wayPoints = gpx.getWaypoints();
		Assert.assertNotNull(wayPoints);
		Assert.assertEquals("Number of WayPoints", EXPECTED_AMOUNT, wayPoints.size());
	}

	/**
	 * Check both coordinates and the elevation of the wayPoints No 3 and 8 from the testdata.
	 */
	@Test
	public void parseCoordinatesOfWayPointsInGPX() {
		double EXPECTED_LATITUDE3 = 50.715435035526752;
		double EXPECTED_LONGITUDE3 = 6.446907967329025;
		double EXPECTED_ELEVATION3 = 167.88145399999999;

		double EXPECTED_LATITUDE8 = 50.711723025888205;
		double EXPECTED_LONGITUDE8 = 6.444288035854697;
		double EXPECTED_ELEVATION8 = 171.37829600000001;
		double DELTA = 0.0;

		GPX gpx = null;
		try {
			gpx = gpxParserService.parseGPXTour(input);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		WayPoint wayPoint3 = null;
		WayPoint wayPoint8 = null;

		Iterator<WayPoint> wayPoints = gpx.getWaypoints().iterator();
		while (wayPoints.hasNext()) {
			WayPoint wayPoint = wayPoints.next();
			if ("Name 3".equals(wayPoint.getName())) {
				wayPoint3 = wayPoint;
			}
			if ("Name 8".equals(wayPoint.getName())) {
				wayPoint8 = wayPoint;
			}
		}
		Assert.assertEquals("WayPoint 3, Latitude: ", EXPECTED_LATITUDE3, wayPoint3.getLatitude(), DELTA);
		Assert.assertEquals("WayPoint 3, Longitude: ", EXPECTED_LONGITUDE3, wayPoint3.getLongitude(), DELTA);
		Assert.assertEquals("WayPoint 3, Elevation: ", EXPECTED_ELEVATION3, wayPoint3.getElevation(), DELTA);

		Assert.assertEquals("WayPoint 8, Latitude: ", EXPECTED_LATITUDE8, wayPoint8.getLatitude(), DELTA);
		Assert.assertEquals("WayPoint 8, Longitude: ", EXPECTED_LONGITUDE8, wayPoint8.getLongitude(), DELTA);
		Assert.assertEquals("WayPoint 8, Elevation: ", EXPECTED_ELEVATION8, wayPoint8.getElevation(), DELTA);
	}
}
