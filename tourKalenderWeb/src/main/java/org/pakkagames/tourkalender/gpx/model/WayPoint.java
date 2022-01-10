package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

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
@JacksonXmlRootElement(localName = "trkpt")
public class WayPoint implements Cloneable {

    /**
     * The latitude of the point. This is always in decimal degrees, and always in WGS84 datum.
     */
    @NonNull
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private final BigDecimal latitude;
    /**
     * The longitude of the point. This is always in decimal degrees, and always in WGS84 datum.
     */
    @NonNull
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private final BigDecimal longitude;
    /**
     * Elevation (in meters) of the point.
     */
    @Setter
    @JacksonXmlProperty(localName = "ele", isAttribute = true)
    private String elevation;
    /**
     * Creation/modification timestamp for element. Date and time in are saved in this model as
     * OffsetDateTime containing the UTC (Univeral Coordinated Time) with an offset from UTC/Greenwich, not local time!
     * Conforms to ISO 8601 specification for date/time representation.
     * Fractional seconds are allowed for millisecond timing in tracklogs.
     */
    @Setter
    @JacksonXmlProperty(localName = "time")
    private OffsetDateTime time;
    /**
     * Magnetic variation (in degrees) at the point.
     */
    @Setter
    @JacksonXmlProperty(localName = "magvar")
    private BigDecimal magneticVariation;
    /**
     * Height, in meters, of geoid (mean sea level) above WGS-84 earth ellipsoid. (NMEA GGA message)
     */
    @Setter
    @JacksonXmlProperty(localName = "geoidheight")
    private BigDecimal geoIdHeight;
    /**
     * The GPS name of the waypoint. This field will be transferred to and from the GPS. GPX does not
     * place restrictions on the length of this field or the characters contained in it. It is up to
     * the receiving application to validate the field before sending it to the GPS.
     */
    @Setter
    private String name;
    /**
     * GPS waypoint comment. Sent to GPS as comment.
     */
    @Setter
    @JacksonXmlProperty(localName = "cmt")
    private String comment;
    /**
     * A text description of the element. Holds additional information about the element intended for
     * the user, not the GPS.
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
     * Link to additional information about the waypoint.
     */
    @Setter
    @JacksonXmlProperty(localName = "link")
    private Set<Link> links;
    /**
     * Text of GPS symbol name. For interchange with other programs, use the exact spelling of the
     * symbol as displayed on the GPS. If the GPS abbreviates words, spell them out.
     */
    @Setter
    private String sym;
    /**
     * Type (classification) of the waypoint.
     */
    @Setter
    private String type;
    /**
     * Type of GPX fix.
     */
    @Setter
    private Fix fix;
    /**
     * Number of satellites used to calculate the GPX fix.
     */
    @Setter
    private int sat;
    /**
     * Horizontal dilution of precision.
     */
    @Setter
    @JacksonXmlProperty(localName = "hdop")
    private double horizontalDilution;
    /**
     * Vertical dilution of precision.
     */
    @Setter
    @JacksonXmlProperty(localName = "vdop")
    private double verticalDilution;
    /**
     * Position dilution of precision.
     */
    @Setter
    @JacksonXmlProperty(localName = "pdop")
    private double positionDilution;
    /**
     * Number of seconds since last DGPS update.
     */
    @Setter
    @JacksonXmlProperty(localName = "ageofdgpsdata")
    private double ageOfGpsData;
    /**
     * ID of DGPS station used in differential correction
     */
    @Setter
    @JacksonXmlProperty(localName = "dgpsid")
    private int dGpsStationId;

    /**
     * Constructor.
     *
     * @param latitude  The latitude of the point.
     * @param longitude The longitude of the point.
     */
    public WayPoint(@NonNull @JacksonXmlProperty(localName = "lat", isAttribute = true) BigDecimal latitude,
                    @NonNull @JacksonXmlProperty(localName = "lon", isAttribute = true) BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Creates and returns a copy of this object.
     * <br/>
     * See {@link java.lang.Object#clone()} for details.
     *
     * @return a deep copy of the object.
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new WayPoint(this.getLatitude(), this.getLongitude(), this.getElevation(), this.getTime(),
                    this.getMagneticVariation(), this.getGeoIdHeight(), this.getName(), this.getComment(),
                    this.getDescription(), this.getSource(), this.getLinks(), this.getSym(), this.getType(),
                    this.getFix(), this.getSat(), this.getHorizontalDilution(), this.getVerticalDilution(),
                    this.getPositionDilution(), this.getAgeOfGpsData(), this.getDGpsStationId());
        }
    }

    // TODO: handle extensions in another way than: ignoreUnknown
    // private final Extension extensions;
}
