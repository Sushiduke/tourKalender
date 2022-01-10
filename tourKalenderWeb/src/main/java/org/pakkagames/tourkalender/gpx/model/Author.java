package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

/**
 * A person or organization. Elements must appear in this order.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JacksonXmlRootElement(localName = "author")
public class Author {

    /**
     * Link to Web site or other external information about person.
     */
    private Link link;

    /**
     * Name of person or organization.
     */
    private String name;

    /**
     * Email address.
     */
    private Email email;
}
