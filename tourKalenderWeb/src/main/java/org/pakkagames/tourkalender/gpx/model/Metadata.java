package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

/**
 * Information about the GPX file, author, and copyright restrictions goes in the metadata section.
 * Providing rich, meaningful information about your GPX files allows others to search for and use
 * your GPS data.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "metadata")
public class Metadata {
	/**
	 * The name of the GPX file.
	 */
	@Setter
	@JacksonXmlProperty(localName = "name")
	private String name;

	/**
	 * A description of the contents of the GPX file.
	 */
	@Setter
	@JacksonXmlProperty(localName = "desc")
	private String description;

	/**
	 * The person or organization who created the GPX file.
	 */
	@Setter
	@JacksonXmlProperty(localName = "author")
	private org.pakkagames.tourkalender.gpx.model.Author author;

	/**
	 * Copyright and license information governing use of the file.
	 */
	@Setter
	@JacksonXmlProperty(localName = "copyright")
	private Copyright copyright;

	/**
	 * URLs associated with the location described in the file.
	 */
	@Setter
	@JacksonXmlProperty(localName = "link")
	@JacksonXmlElementWrapper(useWrapping = false)
	private Set<Link> links;

	/**
	 * The creation date of the file.
	 */
	@Setter
	@JacksonXmlProperty(localName = "time")
	private OffsetDateTime time;

	/**
	 * Keywords associated with the file. <br/>
	 * Search engines or databases can use this information to classify the data.
	 */
	@Setter
	@JacksonXmlProperty(localName = "keywords")
	private String keywords;

	/**
	 * Minimum and maximum coordinates which describe the extent of the coordinates in the file.
	 */
	@Setter
	@JacksonXmlProperty(localName = "bounds")
	private Bounds bounds;

	// TODO: handle extensions in another way than: ignoreUnknown
	// private final Extension extensions;

}
