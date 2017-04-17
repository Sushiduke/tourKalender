package org.pakkagames.tourkalender.service;

import org.pakkagames.tourkalender.gpx.domain.WayPoint;

public class SpeedCalculationDTO {

	private WayPoint wayPoint1;
	private WayPoint wayPoint2;

	public WayPoint getWayPoint1() {
		return wayPoint1;
	}

	public void setWayPoint1(WayPoint wayPoint1) {
		this.wayPoint1 = wayPoint1;
	}

	public WayPoint getWayPoint2() {
		return wayPoint2;
	}

	public void setWayPoint2(WayPoint wayPoint2) {
		this.wayPoint2 = wayPoint2;
	}

	public Double getDeltaDistance() {
		double deltaLongitude = this.wayPoint1.getLongitude() - this.wayPoint2.getLongitude();
		double deltaLatitude = this.wayPoint1.getLatitude() - this.wayPoint2.getLatitude();
		return Math.sqrt(deltaLatitude * deltaLatitude + deltaLongitude * deltaLongitude);
	}

	public Long getDeltaTime() {
		Long delta = this.wayPoint2.getTime().getTime() - this.wayPoint2.getTime().getTime();
		return delta;
	}

}
