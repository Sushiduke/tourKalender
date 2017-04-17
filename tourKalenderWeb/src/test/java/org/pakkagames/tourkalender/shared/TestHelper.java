package org.pakkagames.tourkalender.shared;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestHelper {

	// Java 8
	public static Instant createZoneDateTime(int year, int month, int day, int hour, int minute, int second) {
		int nanoOfSecond = 0;
		return ZonedDateTime.of(year, month, day, hour, minute, second, nanoOfSecond, ZoneId.of("Europe/Berlin")).toInstant();
	}

}
