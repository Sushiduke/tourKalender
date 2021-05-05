package org.pakkagames.tourkalender.gpx.model;

/**
 * A person or organization. Elements must appear in this order.
 * 
 * @author JOG
 * @since TourKalender 1.0.0
 */
public class Person {
	private String name;
	private Email email;
	private Link link;

	/**
	 * @return Name of person or organization.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Name of person or organization.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Email address object.
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * @param email Email address object.
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	/**
	 * @return Link to Web site or other external information about person.
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * @param link Link to Web site or other external information about person.
	 */
	public void setLink(Link link) {
		this.link = link;
	}
}
