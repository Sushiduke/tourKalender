package org.pakkagames.tourkalender.domain;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Entity of a position containing all minimum information about a GPX track.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */
@Entity
public class GPXTrack extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -7799589347941088571L;

	@Column(unique = true, insertable = false, updatable = false)
	private Long id;
	private String userID;
	private Date created;
	private Clob data;
	private String comment;

	public GPXTrack() {
	}

	public GPXTrack(Long id, String userID, Date created, String comment) {
		super();
		this.id = id;
		this.userID = userID;
		this.created = created;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Clob getData() {
		return data;
	}

	public void setData(Clob data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GPXTrack [id=" + id + ", userID=" + userID + ", created=" + created + ", data=" + data + ", comment=" + comment + "]";
	}

}
