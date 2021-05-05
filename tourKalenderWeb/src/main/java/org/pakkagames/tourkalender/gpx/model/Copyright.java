package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Information about the copyright holder and any license governing use of this file. By linking to an appropriate license, you may place
 * your data into the public domain or grant * additional usage rights.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 * 
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@JacksonXmlRootElement(localName="copyright")
public class Copyright {

	/**
	 * Copyright holder (TopoSoft, Inc.).
	 */
	@NonNull
	private final String author;

	/**
	 * Year of copyright.
	 */
	private final String year;

	/**
	 * Link to external file containing license text.
	 */
	private final String license;

}
