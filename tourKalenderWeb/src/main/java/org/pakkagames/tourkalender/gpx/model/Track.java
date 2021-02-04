package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;

/**
 * This class represents a track - an ordered list of points describing a path (trackSegments).
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@JacksonXmlRootElement(localName="trk")
public class Track {
    /**
     * GPS name of track.
     */
    private final String name;

    /**
     * GPS comment for track.
     */
    @JacksonXmlProperty(localName = "cmt")
    private final String comment;

    /**
     * User description of track.
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
     * A Track Segment holds a list of Track Points which are logically connected in order.
     * To represent a single GPS track where GPS reception was lost, or the GPS receiver was turned off,
     * start a new Track Segment for each continuous span of track data.
     */
    @JacksonXmlProperty(localName = "trkseg")
    private final List<TrackSegment> trackSegments;

}
