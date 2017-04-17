package org.pakkagames.tourkalender.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pakkagames.tourkalender.domain.Position;
import org.pakkagames.tourkalender.repository.PositionRepository;
import org.pakkagames.tourkalender.shared.TestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Testclass contains tests for all methods of interface {@link PositionService}.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.pakkagames.tourkalender.testconfig.SimpleTestHSQLConfiguration.class })
@ActiveProfiles(value = "test")
public class PositionServiceTest {

	final static Logger LOGGER = LoggerFactory.getLogger(PositionServiceTest.class);

	@Autowired
	PositionRepository repository;

	@Autowired
	PositionService service;

	Position position;

	@Before
	public void setUp() {
		// Testdata, near Bonn
		position = new Position(50.732451, 7.109564, 30.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 0, 0)), "hier starte ich");
		position = repository.save(position);
		position = new Position(50.730563, 7.110401, 30.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 2, 0)), "");
		position = repository.save(position);
		position = new Position(50.724480, 7.116461, 31.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 4, 0)), "");
		position = repository.save(position);
		position = new Position(50.720540, 7.123864, 31.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 6, 0)), "");
		position = repository.save(position);
		position = new Position(50.716274, 7.141137, 32.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 8, 0)), "");
		position = repository.save(position);
		position = new Position(50.714182, 7.148540, 32.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 10, 0)), "Reifen platt");
		position = repository.save(position);
		position = new Position(50.710639, 7.156097, 33.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 12, 0)), "Reifen wieder voll");
		position = repository.save(position);
		position = new Position(50.706929, 7.161397, 33.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 14, 0)), "");
		position = repository.save(position);
		position = new Position(50.693241, 7.167671, 33.0, Date.from(TestHelper.createZoneDateTime(2015, 9, 5, 7, 16, 0)), "Latest");
		position = repository.save(position);
	}

	@After
	public void tearDown() {
		// delete all data at the end of every test method.
		repository.deleteAll();
	}

	/**
	 * Count the number of positions in testdata and compare it with the number
	 * EXPECTED_AMOUNT_OF_POSITIONS.
	 */
	@Test
	public void findAllPositions() {
		final int EXPECTED_AMOUNT_OF_POSITIONS = 9;
		int existingPositions = 0;
		Iterable<Position> iterable = service.findAllPositions();
		for (Position p : iterable) {
			LOGGER.debug("dateTime: " + p.getDateTime());
			existingPositions++;
		}
		assertNotNull(iterable);
		assertEquals("testdata contains " + EXPECTED_AMOUNT_OF_POSITIONS + " positions", EXPECTED_AMOUNT_OF_POSITIONS, existingPositions);
	}

	/**
	 * Two new positions are added. Test checks, if these two can be found with the
	 * findLatestPositions() method. All entries in between the last and minus
	 * ONE_HOUR_BACK
	 */
	@Test
	public void findLatestPositions() {
		final int EXPECTED_AMOUNT_OF_POSITIONS = 2; // 2 new data entries
		final Integer ONE_HOUR_BACK = 1;

		position = new Position(50.702451, 7.165397, 30.0, Date.from(TestHelper.createZoneDateTime(2015, 10, 5, 7, 0, 0)), "different month than initial data");
		position = repository.save(position);
		position = new Position(50.698563, 7.170401, 30.0, Date.from(TestHelper.createZoneDateTime(2015, 10, 5, 7, 2, 0)), "different month than initial data");
		position = repository.save(position);

		List<Position> positions = service.findLatestPositions(ONE_HOUR_BACK);
		assertNotNull(positions);
		assertEquals("testdata contains " + EXPECTED_AMOUNT_OF_POSITIONS + " positions", EXPECTED_AMOUNT_OF_POSITIONS, positions.size());

	}

	/**
	 * Test if a latest Position can be retrieved and if its the right one.
	 */
	@Test
	public void findLatestPosition() {
		final int EXPECTED_AMOUNT_OF_POSITIONS = 1;

		Position position = service.getLatestPosition();
		assertNotNull(position);
		assertEquals("latest position should be only one", EXPECTED_AMOUNT_OF_POSITIONS, position != null ? 1 : 0);

		assertEquals("Latitude of latest", position.getLatitude(), new Double(50.693241));
		assertEquals("Longitude of latest", position.getLongitude(), new Double(7.167671));
		assertEquals("Comment of latest", position.getComment(), "Latest");
	}

	/**
	 * Count the number of positions in testdata and compare it with the number
	 * EXPECTED_AMOUNT_OF_POSITIONS.
	 */
	@Test
	public void findPositions() {
		final int EXPECTED_AMOUNT_OF_POSITIONS = 8;
		ZonedDateTime now = TestHelper.createZoneDateTime(2015, 9, 5, 7, 14, 0).atZone(ZoneId.of("Europe/Berlin"));
		ZonedDateTime past1970 = TestHelper.createZoneDateTime(1970, 1, 1, 0, 0, 0).atZone(ZoneId.of("Europe/Berlin"));

		List<Position> listOfPositions = service.findPositions(past1970, now);
		assertNotNull(listOfPositions);
		assertEquals("testdata contains " + EXPECTED_AMOUNT_OF_POSITIONS + " positions", EXPECTED_AMOUNT_OF_POSITIONS, listOfPositions.size());
	}

}
