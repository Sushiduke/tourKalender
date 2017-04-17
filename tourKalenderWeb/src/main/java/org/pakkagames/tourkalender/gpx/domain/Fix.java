package org.pakkagames.tourkalender.gpx.domain;

/**
 * Type of GPS fix. none means GPS had no fix. To signify "the fix info is unknown, leave out fixType entirely. pps = military signal used.
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class Fix {

	// Constant that defines 'none' Fix type
	public static Fix NONE = new Fix("none");

	// Constant that defines '2d' Fix type
	public static Fix TWO_D = new Fix("2d");

	// Constant that defines '3d' Fix type
	public static Fix THREE_D = new Fix("3d");

	// Constant that defines 'dgps' Fix type
	public static Fix DGPS = new Fix("dgps");

	// Constant that defines'pps' Fix type
	public static Fix PPS = new Fix("pps");

	private String value;

	private Fix(String value) {
		this.value = value;
	}

	public static Fix returnType(String value) {
		if (NONE.getValue().equals(value)) {
			return NONE;
		}
		else if (TWO_D.getValue().equals(value)) {
			return TWO_D;
		}
		else if (THREE_D.getValue().equals(value)) {
			return THREE_D;
		}
		else if (DGPS.getValue().equals(value)) {
			return DGPS;
		}
		else if (PPS.getValue().equals(value)) {
			return PPS;
		}
		return null;
	}

	public String getValue() {
		return value;
	}

}
