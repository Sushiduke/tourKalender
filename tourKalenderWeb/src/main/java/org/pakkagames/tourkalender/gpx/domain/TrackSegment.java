package org.pakkagames.tourkalender.gpx.domain;

import java.util.ArrayList;

/**
 * A Track Segment holds a list of Track Points which are logically connected in order. To represent a single GPS track where GPS reception was lost, or the GPS receiver was turned
 * off, start a new Track Segment for each continuous span of track data.
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class TrackSegment extends Extension {
	private ArrayList<WayPoint> wayPoints;
	private ArrayList<Extension> extensions;

	/**
	 * @return A Track Point holds the coordinates, elevation, timestamp, and metadata for a single point in a track.
	 */
	// @XmlAttribute(name ="trkpt")
	public ArrayList<WayPoint> getWayPoints() {
		return wayPoints;
	}

	/**
	 * @param waypoints A Track Point holds the coordinates, elevation, timestamp, and metadata for a single point in a track.
	 */
	public void setWayPoints(ArrayList<WayPoint> waypoints) {
		this.wayPoints = waypoints;
	}

	/**
	 * @return List of extensions.
	 */
	public ArrayList<Extension> getExtensions() {
		return extensions;
	}

	/**
	 * @param extensions You can add extend GPX by adding your own elements from another schema here.
	 */
	public void setExtensions(ArrayList<Extension> extensions) {
		this.extensions = extensions;
	}

}
