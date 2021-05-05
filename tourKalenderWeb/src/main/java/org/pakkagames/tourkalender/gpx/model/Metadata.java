package org.pakkagames.tourkalender.gpx.model;

import java.util.Date;
import java.util.HashSet;

/**
 * Information about the GPX file, author, and copyright restrictions goes in the metadata section. Providing rich, meaningful information about your GPX files allows others to
 * search for and use your GPS data.
 * 
 * @author JOG
 * @since TourKalender 1.0.0
 * 
 */
public class Metadata extends Extension {
	private String name;
	private String desc;
	private Person author;
	private Copyright copyright;
	private HashSet<Link> link;
	private Date time;
	private String keywords;
	private Bounds bounds;

	public Metadata() {
	}

	/**
	 * @return The name of the GPX file.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name of the GPX file.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return A description of the contents of the GPX file.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc A description of the contents of the GPX file.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return The person or organization who created the GPX file.
	 */
	public Person getAuthor() {
		return author;
	}

	/**
	 * @param author The person or organization who created the GPX file.
	 */
	public void setAuthor(Person author) {
		this.author = author;
	}

	/**
	 * @return Copyright and license information governing use of the file.
	 */
	public Copyright getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright Copyright and license information governing use of the file.
	 */
	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return URLs associated with the location described in the file.
	 */
	public HashSet<Link> getLinks() {
		return link;
	}

	/**
	 * @param links URLs associated with the location described in the file.
	 */
	public void setLinks(HashSet<Link> links) {
		this.link = links;
	}

	/**
	 * @return The creation date of the file.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time The creation date of the file.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return Keywords associated with the file. Search engines or databases can use this information to classify the data.
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords Keywords associated with the file. Search engines or databases can use this information to classify the data.
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return Bound object, filled with minimum and maximum coordinates which describe the extent of the coordinates in the file.
	 */
	public Bounds getBounds() {
		return bounds;
	}

	/**
	 * @param bounds object, filled with minimum and maximum coordinates which describe the extent of the coordinates in the file
	 */
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
}
