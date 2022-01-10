package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A geographic point with optional elevation and time. Available for use by other schemas.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@JacksonXmlRootElement(localName = "pt")
public class Point {

	/**
	 * The latitude of the point. This is always in decimal degrees, and always in WGS84 datum.
	 */
	@DecimalMin("-90.0")
	@DecimalMax("90.0")
	private final BigDecimal latitude;

	/**
	 * The longitude of the point. This is always in decimal degrees, and always in WGS84 datum.
	 */
	@DecimalMin("-180.0")
	@DecimalMax("180.0")
	private final BigDecimal longitude;

	/**
	 * Elevation (in meters) of the point.
	 */
	@Setter
	@JacksonXmlProperty(localName = "ele")
	private BigDecimal elevation;

	/**
	 * The time that the point was recorded.
	 */
	@Setter
	private Date time;

	/**
	 * Constructor.
	 *
	 * @param latitude The latitude of the point.
	 * @param longitude The longitude of the point.
	 */
	public Point(@NonNull @JacksonXmlProperty(localName = "lat", isAttribute = true) BigDecimal latitude,
				 @NonNull @JacksonXmlProperty(localName = "lon", isAttribute = true) BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
