package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

/**
 * This class represents a track - an ordered list of points describing a path (trackSegments).
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@JacksonXmlRootElement(localName = "trk")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

    /**
     * GPS name of track.
     */
    @Setter
    @JacksonXmlProperty(localName = "name")
    private String name;

    /**
     * GPS comment for track.
     */
    @Setter
    @JacksonXmlProperty(localName = "cmt")
    private String comment;

    /**
     * User description of track.
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
    private Integer number;
    /**
     * Type (classification) of track.
     */
    @Setter
    private String type;
    /**
     * A Track Segment holds a list of Track Points which are logically connected in order. To
     * represent a single GPS track where GPS reception was lost, or the GPS receiver was turned off,
     * start a new Track Segment for each continuous span of track data.
     */
    @Setter
    @JacksonXmlProperty(localName = "trkseg")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<TrackSegment> trackSegments;

    /*
     * TODO: handle extensions in another way than: ignoreUnknown
     * @JacksonXmlProperty(localName = "extensions")
     * private Extension extensions;
     */
}
