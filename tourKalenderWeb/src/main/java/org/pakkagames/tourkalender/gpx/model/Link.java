package org.pakkagames.tourkalender.gpx.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

/**
 * A link to an external resource (webpage, digital photo, video clip, etc.) with additional
 * information.
 *
 * @author JOG
 * @since TourKalender 1.0.0
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@JacksonXmlRootElement(localName = "link")
public class Link implements Cloneable {

	/**
	 * URL of hyperlink.
	 */
	private final String href;

	/**
	 * Text of hyperlink.
	 */
	@Setter
	private String text;

	/**
	 * Link to Web site or other external information about person.
	 */
	@Setter
	private String type;

	public Link(@NonNull @JacksonXmlProperty(localName = "href") String href) {
		this.href = href;
	}

	/**
	 * Creates and returns a copy of this object.
	 * <br/>
	 * See {@link java.lang.Object#clone()} for details.
	 *
	 * @return a deep copy of the object.
	 */
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new Link(this.getHref(), this.getText(), this.getType());
		}
	}
}
