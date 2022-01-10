package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

/**
 * Model for the GPX route.<br/>
 * It is an ordered list of waypoints representing a series of turn points leading to a destination.<br/>
 * In GPX this class has the tag 'rte'.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@Jacksonized
@EqualsAndHashCode
@JacksonXmlRootElement(localName = "rte")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

	/**
	 * GPS name of route.
	 */
	@Setter
	private String name;

	/**
	 * GPS comment for route.
	 */
	@Setter
	@JacksonXmlProperty(localName = "cmt")
	private String comment;

	/**
	 * Text description of route for user.
	 */
	@Setter
	@JacksonXmlProperty(localName = "desc")
	private String description;

	/**
	 * Source of data. Included to give user some idea of reliability and accuracy of data.
	 */
	@Setter
	@JacksonXmlProperty(localName = "src")
	private String source;

	/**
	 * Links to external information about track.
	 */
	@Setter
	@JacksonXmlProperty(localName = "link")
	@JacksonXmlElementWrapper(useWrapping = false)
	private Set<Link> links;

	/**
	 * GPS track number.
	 */
	@Min(0)
	@Setter
	private int number;

	/**
	 * Type (classification) of track.
	 */
	@Setter
	private String type;

	/**
	 * A list of route points.
	 */
	@Setter
	@JacksonXmlProperty(localName = "rtept")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<WayPoint> routePoints;

	/**
	 * Setter for field number.
	 *
	 * @param number non negative integer.
	 */
    /*
    public void setNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("the parameter number must have non negative integer value");
        }
        this.number = number;
    }
*/
	/*
	 * TODO: handle extensions in another way than: ignoreUnknown
	 * private final Extension extensions;
	 */
}
