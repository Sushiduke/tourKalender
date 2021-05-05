package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Two latitude/longitude pairs defining the extent of an element.
 * 
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@JacksonXmlRootElement(localName="bounds")
public class Bounds {
	/**
	 * The minimum latitude.
	 */
	@NonNull
	@JacksonXmlProperty(localName = "minlat")
	private final Double minLatitude;

	/**
	 * The minimum longitude.
	 */
	@NonNull
	@JacksonXmlProperty(localName = "minlon")
	private final double minLongitude;

	/**
	 * The maximum longitude.
	 */
	@NonNull
	@JacksonXmlProperty(localName = "maxlat")
	private final double maxLatitude;

	/**
	 * The maximum longitude.
	 */
	@NonNull
	@JacksonXmlProperty(localName = "maxlon")
	private final double maxLongitude;

}