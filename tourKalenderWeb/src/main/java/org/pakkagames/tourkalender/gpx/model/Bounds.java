package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * Two latitude/longitude pairs defining the extent of an element.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "bounds")
public class Bounds {

	/**
	 * The minimum latitude.
	 */
	@NonNull
	@DecimalMin("-90.0")
	@JacksonXmlProperty(localName = "minlat")
	private BigDecimal minLatitude;

	/**
	 * The minimum longitude.
	 */
	@NonNull
	@DecimalMin("-180.0")
	@JacksonXmlProperty(localName = "minlon")
	private BigDecimal minLongitude;

	/**
	 * The maximum longitude.
	 */
	@NonNull
	@DecimalMax("90.0")
	@JacksonXmlProperty(localName = "maxlat")
	private BigDecimal maxLatitude;

	/**
	 * The maximum longitude.
	 */
	@NonNull
	@DecimalMax("180.0")
	@JacksonXmlProperty(localName = "maxlon")
	private BigDecimal maxLongitude;
}
