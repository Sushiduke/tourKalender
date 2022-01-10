package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

/**
 * An email of a person or organization. Broken into two parts (id and domain) to help prevent email
 * harvesting
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "email")
public class Email {

    /**
     * Name of person or organization.
     */
    private String name;

    /**
     * The email address.
     */
    @JacksonXmlProperty(localName = "id")
    private String email;

    /**
     * Link to website or other external information about person.
     */
    private Link link;
}
