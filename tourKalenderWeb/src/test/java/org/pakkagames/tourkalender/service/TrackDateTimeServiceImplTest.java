package org.pakkagames.tourkalender.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pakkagames.tourkalender.exception.TrackProcessException;
import org.pakkagames.tourkalender.gpx.model.WayPoint;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrackDateTimeServiceImplTest {

    OffsetDateTime NOW = OffsetDateTime.now();
    OffsetDateTime NOW_PLUS_10_SECONDS = NOW.plus(10, ChronoUnit.SECONDS);
    private TrackDateTimeServiceImpl testee = new TrackDateTimeServiceImpl();

    @Test
    @DisplayName("[positive] when dateTime of two points is set the shiftDateTime method will change the dateTime correctly")
    void shiftWayPointsOneMinuteEarlier() {
        WayPoint wayPointBonn = new WayPoint(new BigDecimal("50.735612"), new BigDecimal("7.100813"));
        WayPoint wayPointBerlin = new WayPoint(new BigDecimal("52.517036"), new BigDecimal("13.388969"));

        wayPointBonn.setTime(NOW_PLUS_10_SECONDS);
        wayPointBerlin.setTime(NOW_PLUS_10_SECONDS);

        List<WayPoint> result = testee.changeAllDateTime(List.of(wayPointBonn, wayPointBerlin), Duration.of(-10, ChronoUnit.SECONDS));

        result.forEach(wayPoint -> assertEquals(NOW, wayPoint.getTime()));
    }

    @Test
    @DisplayName("[negative] when list of wayPoints is empty an exception is thrown")
    void whenListWayPointsContainIsEmptyAnExceptionIsThrown() {
        assertThrows(TrackProcessException.class, () -> testee.changeAllDateTime(Collections.emptyList(), NOW));
    }

}