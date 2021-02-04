package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * A Track Segment holds a list of Track Points which are logically connected in order. To represent a single GPS track where GPS reception was lost, or the GPS receiver was turned
 * off, start a new Track Segment for each continuous span of track data.
 * 
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@JacksonXmlRootElement(localName="trkpt")
public class TrackSegment {
	/**
	 * A Track Point holds the coordinates, elevation, timestamp, and metadata for a single point in a track.
	 */
	private ArrayList<WayPoint> wayPoints;

	/**
	 * You can add extend GPX by adding your own elements from another schema here.
	 */
	private ArrayList<Extension> extensions;

}
