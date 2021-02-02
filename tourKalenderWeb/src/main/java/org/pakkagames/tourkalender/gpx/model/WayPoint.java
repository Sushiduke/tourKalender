package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.HashSet;

/**
 * A waypoint is an addressable point, represents a coordinate, a point of interest or a named feature on a map.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@JacksonXmlRootElement(localName="wpt")
public class WayPoint {

    /**
     * The latitude of the point. This is always in decimal degrees, and always in WGS84 datum.
     */
    @NonNull
    @JacksonXmlProperty(localName = "lat")
    private final Double latitude;

    /**
     * The longitude of the point. This is always in decimal degrees, and always in WGS84 datum.
     */
    @NonNull
    @JacksonXmlProperty(localName = "lon")
    private final Double longitude;

    /**
     * Elevation (in meters) of the point.
     */
    @JacksonXmlProperty(localName = "ele")
    private Double elevation;

    /**
     * Creation/modification timestamp for element. Date and time in are in Univeral Coordinated Time (UTC), not local time!
     * Conforms to ISO 8601 specification for date/time representation. Fractional seconds are allowed for millisecond timing in tracklogs.
     */
    private OffsetDateTime time;

    /**
     * Magnetic variation at the point, units: meters
     */
    @JacksonXmlProperty(localName = "magvar")
    private Double magneticVariation;

    /**
     * Height, in meters, of geoid (mean sea level) above WGS-84 earth ellipsoid. (NMEA GGA message)
     */
    @JacksonXmlProperty(localName = "geoidheight")
    private double geoIdHeight;

    /**
     * The GPS name of the waypoint. This field will be transferred to and from the GPS. GPX does not place restrictions on the length of this field or
     * the characters contained in it. It is up to the receiving application to validate the field before sending it to the GPS.
     */
    private String name;

    /**
     * GPS waypoint comment. Sent to GPS as comment.
     */
    @JacksonXmlProperty(localName = "cmt")
    private String comment;

    /**
     * A text description of the element. Holds additional information about the element intended for the user, not the GPS.
     */
    @JacksonXmlProperty(localName = "desc")
    private String description;

    /**
     * Source of data. Included to give user some idea of reliability and accuracy of data
     */
    private String src;

    /**
     * Link to additional information about the waypoint.
     */
    private HashSet<Link> link;

    /**
     * Text of GPS symbol name. For interchange with other programs, use the exact spelling of the symbol as displayed on the GPS.
     * If the GPS abbreviates words, spell them out.
     */
    private String sym;

    /**
     * Type (classification) of the waypoint.
     */
    private String type;

    /**
     * Type of GPX fix.
     */
    private Fix fix;

    /**
     * Number of satellites used to calculate the GPX fix.
     */
    private int sat;

    /**
     * Horizontal dilution of precision.
     */
    private double hdop;

    /**
     * Vertical dilution of precision.
     */
    private double vdop;

    /**
     * Position dilution of precision.
     */
    private double pdop;

    /**
     * Number of seconds since last DGPS update.
     */
    @JacksonXmlProperty(localName = "ageofdgpsdata")
    private double ageOfGpsData;

    /**
     * ID of DGPS station used in differential correction
     */
    @JacksonXmlProperty(localName = "dgpsid")
    private int dGpsStationId;

    /**
     * You can add extend GPX by adding your own elements from another schema here.
     */
    private Extension extensions;

}
