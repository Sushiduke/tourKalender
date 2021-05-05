package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * In GPX this class has the tag 'rte'. It is an ordered list of waypoints representing a series of turn points leading to a destination.
 * 
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@JacksonXmlRootElement(localName="rte")
public class Route {

	/**
	 * GPS name of route.
	 */
	private final String name;

	/**
	 * GPS comment for route.
	 */
	@JacksonXmlProperty(localName = "cmt")
	private final String comment;


	/**
	 * Text description of route for user.
	 */
	@JacksonXmlProperty(localName = "desc")
	private final String description;

	/**
	 * Source of data. Included to give user some idea of reliability and accuracy of data.
	 */
	@JacksonXmlProperty(localName = "src")
	private final String source;

	/**
	 * Links to external information about track.
	 */
	@JacksonXmlProperty(localName = "link")
	private final HashSet<Link> links;

	/**
	 * GPS track number.
	 */
	private final Integer number;

	/**
	 * Type (classification) of track.
	 */
	private final String type;

	/**
	 * A list of route points.
	 */
	@JacksonXmlProperty(localName = "rtept")
	private final ArrayList<WayPoint> routePoints;

	/**
	 * You can add extend GPX by adding your own elements from another schema here.
	 */
	private final ArrayList<Extension> extensions;

}
