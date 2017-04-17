package org.pakkagames.tourkalender.gpx.domain;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * In GPX this class has the tag 'rte'. It is an ordered list of waypoints representing a series of turn points leading to a destination.
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class Route extends Extension {
	private String name;
	private String comment;
	private String description;
	private String src;
	private HashSet<Link> links;
	private int number;

	private String type;
	private ArrayList<WayPoint> routePoints;

	/**
	 * @return GPS name of route.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name New name for GPS.
	 */
	// @XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return GPS comment for route.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment GPS comment for route.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return Text description of route for user.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description Text description of route for user. Not sent to GPS.
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
	 * @return Links to external information about track.
	 */
	public HashSet<Link> getLinks() {
		return links;
	}

	/**
	 * @param links Links to external information about track.
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
	 * @return A list of route points.
	 */
	public ArrayList<WayPoint> getRoutePoints() {
		return routePoints;
	}

	/**
	 * @param routePoints A list of route points.
	 */
	// @XmlAttribute(name ="rtept")
	public void setRoutePoints(ArrayList<WayPoint> routePoints) {
		this.routePoints = routePoints;
	}

	// TODO: check impl
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("rte[");
		sb.append("name:" + name + " ");
		int points = 0;
		if (routePoints != null) {
			points = routePoints.size();
		}
		sb.append("rtepts:" + points + " ");
		sb.append("]");
		return sb.toString();
	}
}
