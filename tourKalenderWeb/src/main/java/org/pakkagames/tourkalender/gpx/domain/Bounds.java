package org.pakkagames.tourkalender.gpx.domain;

/**
 * Two latitude/longitude pairs defining the extent of an element.
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class Bounds {
	private double minLatitude;
	private double minLongitude;
	private double maxLatitude;
	private double maxLongitude;

	public Bounds(double minlat, double maxlat, double minlon, double maxlon) {
		this.setMinLatitude(minlat);
		this.setMaxLatitude(maxlat);
		this.setMinLongitude(minlon);
		this.setMaxLongitude(maxlon);
	}

	/**
	 * @return The minimum latitude.
	 */
	public double getMinLatitude() {
		return minLatitude;
	}

	/**
	 * @param minLat The minimum latitude.
	 */
	public void setMinLatitude(double minLatitude) {
		this.minLatitude = minLatitude;
	}

	/**
	 * @return The minimum longitude.
	 */
	public double getMinLongitude() {
		return minLongitude;
	}

	/**
	 * @param minLongitude The minimum longitude.
	 */
	public void setMinLongitude(double minLongitude) {
		this.minLongitude = minLongitude;
	}

	/**
	 * @return The maximum latitude.
	 */
	public double getMaxLatitude() {
		return maxLatitude;
	}

	/**
	 * @param maxLatitude The maximum latitude.
	 */
	public void setMaxLatitude(double maxLatitude) {
		this.maxLatitude = maxLatitude;
	}

	/**
	 * @return The maximum longitude.
	 */
	public double getMaxLongitude() {
		return maxLongitude;
	}

	/**
	 * @param maxLongitude The maximum longitude.
	 */
	public void setMaxLongitude(double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}

}