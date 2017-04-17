package org.pakkagames.tourkalender.gpx.domain;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class represents a track - an ordered list of points describing a path (trackSegments)
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class Track extends Extension {
	private String name;
	private String comment;
	private String description;
	private String src;
	private HashSet<Link> links;
	private int number;
	private String type;
	private ArrayList<TrackSegment> trackSegments;

	/**
	 * @return GPS name of track.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name GPS name of track.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return GPS comment for track.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment GPS comment for track.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return User description of track.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description User description of track.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Source of data. Included to give user some idea of reliability and accuracy of data.
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src Source of data. Included to give user some idea of reliability and accuracy of data.
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @return Links to external information about track
	 */
	public HashSet<Link> getLinks() {
		return links;
	}

	/**
	 * @param links Links to external information about track
	 */
	public void setLinks(HashSet<Link> links) {
		this.links = links;
	}

	/**
	 * @return GPS track number.
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number GPS track number.
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return Type (classification) of track.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type Type (classification) of track.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * A Track Segment holds a list of Track Points which are logically connected in order. To represent a single GPS track where GPS reception was lost, or the GPS receiver was
	 * turned off, start a new Track Segment for each continuous span of track data.
	 * 
	 * @return Track Segments.
	 * 
	 */
	public ArrayList<TrackSegment> getTrackSegments() {
		return trackSegments;
	}

	/**
	 * A Track Segment holds a list of Track Points which are logically connected in order. To represent a single GPS track where GPS reception was lost, or the GPS receiver was
	 * turned off, start a new Track Segment for each continuous span of track data.
	 * 
	 * @param trackSegments List of TrackSegment
	 */
	public void setTrackSegments(ArrayList<TrackSegment> trackSegments) {
		this.trackSegments = trackSegments;
	}

	// TODO: check impl
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("trk[");
		sb.append("name:" + name + " ");
		sb.append("]");
		return sb.toString();
	}

}
