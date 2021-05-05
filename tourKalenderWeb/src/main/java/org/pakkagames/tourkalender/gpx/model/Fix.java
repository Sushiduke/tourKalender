package org.pakkagames.tourkalender.gpx.model;

/**
 * Type of GPS fix. none means GPS had no fix. To signify "the fix info is unknown, leave out fixType entirely. pps = military signal used.
 * 
 * @author JOG
 * @since TourKalender 1.0.0
 */
public enum Fix {

	// Constant that defines 'none' Fix type
	NONE("none"),

	// Constant that defines '2d' Fix type
	TWO_D("2d"),

	// Constant that defines '3d' Fix type
	THREE_D("3d"),

	// Constant that defines 'dgps' Fix type
	 DGPS ("dgps"),

	// Constant that defines 'pps' Fix type
	PPS("pps");

	private String value;

	Fix(String value) {
		this.value = value;
	}

}
