package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

/**
 * A Track Segment holds a list of Track Points which are logically connected in order. To represent
 * a single GPS track where GPS reception was lost, or the GPS receiver was turned off, start a new
 * Track Segment for each continuous span of track data.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JacksonXmlRootElement(localName = "trkseg")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackSegment {
	/**
	 * A Track Point holds the coordinates, elevation, timestamp, and metadata for a single point in a
	 * track.
	 */
	@Setter
	@JacksonXmlProperty(localName = "trkpt")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<WayPoint> wayPoints;

  /*
  TODO: handle extensions in another way than: ignoreUnknown
  @JacksonXmlProperty(localName = "extensions")
  private Extension extensions;
  */
}
