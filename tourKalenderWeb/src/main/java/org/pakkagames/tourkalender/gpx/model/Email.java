package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * An email of a person or organization. Broken into two parts (id and domain) to help prevent email harvesting
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@JacksonXmlRootElement(localName = "email")
public class Email {
    /**
     * Name of person or organization.
     */
    private final String name;

    /**
     * The email address.
     */
    private final String email;

    /**
     * Link to Web site or other external information about person.
     */
    private final Link link;

}