package org.pakkagames.tourkalender.gpx.domain;

/**
 * Information about the copyright holder and any license governing use of this file. By linking to an appropriate license, you may place your data into the public domain or grant
 * additional usage rights.
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 * 
 */
public class Copyright {
	private String year;
	private String license;
	private String author;

	public Copyright(String author) {
		this.author = author;
	}

	/**
	 * @return Year of copyright.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year Year of copyright.
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return Link to external file containing license text.
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * @param license Link to external file containing license text.
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * @return Copyright holder.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author Copyright holder.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
}
