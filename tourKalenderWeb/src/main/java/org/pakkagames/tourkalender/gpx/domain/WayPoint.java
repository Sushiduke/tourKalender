package org.pakkagames.tourkalender.gpx.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

/**
 * wpt represents a waypoint, point of interest, or named feature on a map.
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class WayPoint extends Extension {

	private double elevation;
	private Date time;
	private double magneticVariation;
	private double geoIdHeight;
	private String name;
	private String comment;
	private String description;
	private String src;
	private HashSet<Link> link;
	private String sym;
	private String type;
	private Fix fix;
	private int sat;
	private double hdop;
	private double vdop;
	private double pdop;
	private double ageOfGpsData;
	private int dGpsStationId;
	private double latitude;
	private double longitude;

	public WayPoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * XML name for this is: ele
	 * @return Elevation (in meters) of the point.
	 */
	// @XmlAttribute(name = "ele")
	public double getElevation() {
		return this.elevation;
	}

	/**
	 * @param elevation Elevation (in meters) of the point.
	 */
	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return Creation/modification timestamp for element. Date and time in are in Univeral Coordinated Time (UTC), not local time! Conforms to ISO 8601 specification for
	 * date/time representation. Fractional seconds are allowed for millisecond timing in tracklogs.
	 */
	public Date getTime() {
		return this.time;
	}

	/**
	 * @param time Creation/modification timestamp for element. Date and time in are in Univeral Coordinated Time (UTC), not local time! Conforms to ISO 8601 specification for
	 * date/time representation. Fractional seconds are allowed for millisecond timing in tracklogs.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return Magnetic variation (in degrees) at the point
	 */
	public double getMagneticVariation() {
		return magneticVariation;
	}

	/**
	 * @param magneticVariation Magnetic variation (in degrees) at the point
	 */
	public void setMagneticVariation(double magneticVariation) {
		this.magneticVariation = magneticVariation;
	}

	/**
	 * @return Height (in meters) of geoid (mean sea level) above WGS84 earth ellipsoid. As defined in NMEA GGA message.
	 */
	public double getGeoIdHeight() {
		return geoIdHeight;
	}

	/**
	 * @param geoIdHeight Height (in meters) of geoid (mean sea level) above WGS84 earth ellipsoid. As defined in NMEA GGA message.
	 */
	public void setGeoIdHeight(double geoIdHeight) {
		this.geoIdHeight = geoIdHeight;
	}

	/**
	 * @return The GPS name of the waypoint. This field will be transferred to and from the GPS. GPX does not place restrictions on the length of this field or the characters
	 * contained in it. It is up to the receiving application to validate the field before sending it to the GPS.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The GPS name of the waypoint. This field will be transferred to and from the GPS. GPX does not place restrictions on the length of this field or the characters
	 * contained in it. It is up to the receiving application to validate the field before sending it to the GPS.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return GPS waypoint comment. Sent to GPS as comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment GPS waypoint comment. Sent to GPS as comment.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return A text description of the element. Holds additional information about the element intended for the user, not the GPS.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description A text description of the element. Holds additional information about the element intended for the user, not the GPS.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Source of data. Included to give user some idea of reliability and accuracy of data. "Garmin eTrex", "USGS quad Boston North", e.g.
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src Source of data. Included to give user some idea of reliability and accuracy of data. "Garmin eTrex", "USGS quad Boston North", e.g.
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @return Link to additional information about the waypoint.
	 */
	public HashSet<Link> getLink() {
		return link;
	}

	/**
	 * @param link Link to additional information about the waypoint.
	 */
	public void setLink(HashSet<Link> link) {
		this.link = link;
	}

	/**
	 * @return Text of GPS symbol name. For interchange with other programs, use the exact spelling of the symbol as displayed on the GPS. If the GPS abbreviates words, spell them
	 * out.
	 */
	public String getSym() {
		return sym;
	}

	/**
	 * @param sym Text of GPS symbol name. For interchange with other programs, use the exact spelling of the symbol as displayed on the GPS. If the GPS abbreviates words, spell
	 * them out.
	 */
	public void setSym(String sym) {
		this.sym = sym;
	}

	/**
	 * @return Type (classification) of the waypoint.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type Type (classification) of the waypoint.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Type of GPX fix.
	 */
	public Fix getFix() {
		return fix;
	}

	/**
	 * @param fix Type of GPX fix.
	 */
	public void setFix(Fix fix) {
		this.fix = fix;
	}

	/**
	 * @return Number of satellites used to calculate the GPX fix.
	 */
	public int getSat() {
		return sat;
	}

	/**
	 * @param sat Not negative number of satellites used to calculate the GPX fix.
	 */
	public void setSat(int sat) {
		this.sat = sat;
	}

	/**
	 * @return Horizontal dilution of precision.
	 */
	public double getHdop() {
		return hdop;
	}

	/**
	 * @param hdop Horizontal dilution of precision.
	 */
	public void setHdop(double hdop) {
		this.hdop = hdop;
	}

	/**
	 * @return Vertical dilution of precision.
	 */
	public double getVdop() {
		return vdop;
	}

	/**
	 * @param vdop Vertical dilution of precision.
	 */
	public void setVdop(double vdop) {
		this.vdop = vdop;
	}

	/**
	 * @return Position dilution of precision.
	 */
	public double getPdop() {
		return pdop;
	}

	/**
	 * @param pdop Position dilution of precision.
	 */
	public void setPdop(double pdop) {
		this.pdop = pdop;
	}

	/**
	 * @return Number of seconds since last DGPS update.
	 */
	public double getAgeOfGpsData() {
		return ageOfGpsData;
	}

	/**
	 * @param ageOfGpsData Number of seconds since last DGPS update.
	 */
	public void setAgeOfGpsData(double ageOfGpsData) {
		this.ageOfGpsData = ageOfGpsData;
	}

	/**
	 * @return ID of DGPS station used in differential correction.
	 */
	public int getdGpsStationId() {
		return dGpsStationId;
	}

	/**
	 * @param dGpsStationId ID of DGPS station used in differential correction.
	 */
	public void setdGpsStationId(int dGpsStationId) {
		this.dGpsStationId = dGpsStationId;
	}

	/**
	 * @return The latitude of the point. This is always in decimal degrees, and always in WGS84 datum.
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude The latitude of the point. This is always in decimal degrees, and always in WGS84 datum.
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return The longitude of the point. This is always in decimal degrees, and always in WGS84 datum.
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude The longitude of the point. This is always in decimal degrees, and always in WGS84 datum.
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Returns a String representation of this waypoint.
	 */
	// TODO: Check impl
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = "";
		if (this.time != null) {
			date = sdf.format(this.time);
		}
		sb.append("[");
		sb.append("name:'" + this.name + "' ");
		sb.append("lat:" + this.latitude + " ");
		sb.append("lon:" + this.longitude + " ");
		sb.append("elv:" + this.elevation + " ");
		sb.append("time:" + date + " ");
		sb.append("fix:" + this.fix + " ");
		if (this.extensionData != null) {
			sb.append("extensions:{");
			Iterator<String> it = this.extensionData.keySet().iterator();
			while (it.hasNext()) {
				sb.append(it.next());
				if (it.hasNext()) {
					sb.append(",");
				}
			}
			sb.append("}");
		}
		sb.append("]");
		return sb.toString();
	}

}
