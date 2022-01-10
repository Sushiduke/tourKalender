package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

/**
 * An ordered sequence of points. (for polygons or polylines, e.g.).
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JacksonXmlRootElement(localName = "ptsegType")
public class PointSegment {
    /**
     * Ordered list of geographic points.
     */
    @Setter
    @JacksonXmlProperty(localName = "pt")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Point> points;

}
