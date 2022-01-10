package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * This class holds gpx information from a &lt;gpx&gt; node. <br>
 *
 * <p>GPX specification for this tag: <code>
 * &lt;gpx version="1.1" creator="" xsd:string [1]"&gt;<br>
 * &nbsp;&nbsp;&nbsp;&lt;metadata&gt; xsd:string &lt;/metadata&gt; [0..1]<br>
 * &nbsp;&nbsp;&nbsp;&lt;wpt&gt; xsd:string &lt;/wpt&gt; [0..1]<br>
 * &nbsp;&nbsp;&nbsp;&lt;rte&gt; xsd:string &lt;/rte&gt; [0..1]<br>
 * &nbsp;&nbsp;&nbsp;&lt;trk&gt; xsd:string &lt;/trk&gt; [0..1]<br>
 * &nbsp;&nbsp;&nbsp;&lt;extensions&gt; extensionsType &lt;/extensions&gt; [0..1]<br>
 * &lt;/gpx&gt;<br>
 * </code>
 *
 * @since TourKalender 1.0.0
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Jacksonized
@JacksonXmlRootElement(localName = "gpx")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gpx {

    /**
     * The name or URL of the software that created your GPX document. <br/>
     * This allows others to inform the creator of a GPX instance document that fails to validate.
     */
    @NonNull
    private final String creator;

    /**
     * The version number in your GPX document, mandatory.
     */
    @NonNull
    @JacksonXmlProperty(localName = "version", isAttribute = true)
    private final String version;

    /**
     * Metadata about the file.
     */
    @Setter
    @JacksonXmlProperty(localName = "metadata")
    private Metadata metadata;

    /**
     * A list of waypoints.
     */
    @Setter
    @JacksonXmlProperty(localName = "wpt")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<WayPoint> waypoints;

    /**
     * A list of waypoints.
     */
    @Setter
    @JacksonXmlProperty(localName = "rte")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Route> routes;

    /**
     * A list of tracks.
     */
    @Setter
    @JacksonXmlProperty(localName = "trk")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Track> tracks;

    // TODO: handle extensions in another way than: ignoreUnknown
    // private final Extension extensions;

}
