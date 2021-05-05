package org.pakkagames.tourkalender.gpx.model;

/**
 * A link to an external resource (Web page, digital photo, video clip, etc) with additional information.
 * 
 * @author JOG
 * @since TourKalender 1.0.0
 */
public class Link {

	private String text;
	private String href;
	private String type;

	/**
	 * @param href A link to an external resource (Web page, digital photo, video clip, etc) with additional information
	 */
	public Link(String href) {
		this.href = href;
	}

	/**
	 * @return Text of hyperlink
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return URL of hyperlink
	 */
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return Mime type of content (image/jpeg)
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Link [text=" + text + ", href=" + href + ", type=" + type + "]";
	}
}
