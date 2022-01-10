package org.pakkagames.tourkalender.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.gpx.model.WayPoint;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TrackDistanceServiceImplTest {

    private static final BigDecimal EXPECTED_DISTANCE_BONN_BERLIN = new BigDecimal("476.910126989202681650752");

    private TrackDistanceServiceImpl testee = new TrackDistanceServiceImpl();

    @Test
    @DisplayName("when track of waypoint of Bonn and Berlin are given, the distance is about 540 km")
    void distanceBetweenBonnAndBerlinTest() {

        WayPoint wayPointBonn = new WayPoint(new BigDecimal("50.735612"), new BigDecimal("7.100813"));
        WayPoint wayPointBerlin = new WayPoint(new BigDecimal("52.517036"), new BigDecimal("13.388969"));

        BigDecimal distanceResult = testee.calculateDistance(List.of(wayPointBonn, wayPointBerlin));

        assertEquals(new BigDecimal("476.910126989202681650752"), distanceResult);
    }

    @Test
    @DisplayName("when track of waypoint of Bonn, Berlin and back to Bonn are given, the distance is doubled")
    void distanceBetweenBonnAndBerlinAndBackTest() {

        WayPoint wayPointBonn = new WayPoint(new BigDecimal("50.735612"), new BigDecimal("7.100813"));
        WayPoint wayPointBerlin = new WayPoint(new BigDecimal("52.517036"), new BigDecimal("13.388969"));

        BigDecimal distanceResult = testee.calculateDistance(List.of(wayPointBonn, wayPointBerlin, wayPointBonn));

        assertEquals(new BigDecimal("2").multiply(EXPECTED_DISTANCE_BONN_BERLIN), distanceResult);
    }
}