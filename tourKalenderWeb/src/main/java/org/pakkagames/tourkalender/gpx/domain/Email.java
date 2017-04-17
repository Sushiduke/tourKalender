package org.pakkagames.tourkalender.gpx.domain;

/**
 * An email address. Broken into two parts (id and domain) to help prevent email harvesting
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class Email {
	private String id;
	private String domain;

	public Email(String id, String domain) {
		this.id = id;
		this.domain = domain;
	}

	public Email(String emailId) {
		String[] splittedEmail = emailId.split("@");
		if (splittedEmail.length == 1) {
			this.id = splittedEmail[0];
			this.domain = splittedEmail[1];
		}
		else {
			this.id = "";
			this.domain = "";
		}
	}

	/**
	 * @return id of 1st half of email address
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id id of 1st half of email address
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return domain half of email address
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain domain half of email address
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
}