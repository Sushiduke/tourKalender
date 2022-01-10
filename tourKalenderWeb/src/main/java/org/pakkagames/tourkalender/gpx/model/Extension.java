package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.Map;

/**
 * This class holds generic extension information from any node that can have extensions defined.
 * <p>
 * <p/
 * Any custom extension parser used when parsing a gpx file will return a custom object instance that will be kept in properties defined in this class.
 * <p/>
 * Multiple extension parsers can be used when parsing. Every extension parser defines an unique id that will
 * be used as a HashMap key for the parsed object values.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@JacksonXmlRootElement(localName = "extensions")
public class Extension {

    private final Map<String, Object> extensionData; // = Collections.emptyMap();
}
