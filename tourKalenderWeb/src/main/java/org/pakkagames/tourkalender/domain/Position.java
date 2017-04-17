package org.pakkagames.tourkalender.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Entity of a position containing all minimum information about a GPX position.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */
@Entity
public class Position extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -7799589347941088571L;

	@Column(unique = true, insertable = false, updatable = false)
	private Long id;
	private Double latitude;
	private Double longitude;
	private Double elevation;
	private Date dateTime;
	private String comment;

	public Position() {
	}

	public Position(Long id, Double latitude, Double longitude, Double elevation, Date dateTime, String comment) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.dateTime = dateTime;
		this.comment = comment;
	}

	public Position(Double latitude, Double longitude, Double elevation, Date dateTime, String comment) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.dateTime = dateTime;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", elevation=" + elevation + ", dateTime=" + dateTime + ", comment=" + comment + "]";
	}

}
