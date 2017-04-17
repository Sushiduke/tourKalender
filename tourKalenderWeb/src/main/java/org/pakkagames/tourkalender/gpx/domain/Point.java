package org.pakkagames.tourkalender.gpx.domain;

import java.util.Date;

/**
 * A geographic point with optional elevation and time. Available for use by other schemas.
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class Point {

	private double elevation;
	private Date time;
	private double latitude;
	private double longitude;

	public Point(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return The elevation (in meters) of the point.
	 */
	public double getElevation() {
		return elevation;
	}

	/**
	 * @param elevation The elevation (in meters) of the point.
	 */
	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return The time that the point was recorded.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time The time that the point was recorded.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return The latitude of the point. Decimal degrees, WGS84 datum.
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude The latitude of the point. Decimal degrees, WGS84 datum.
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return The latitude of the point. Decimal degrees, WGS84 datum.
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude The latitude of the point. Decimal degrees, WGS84 datum.
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}