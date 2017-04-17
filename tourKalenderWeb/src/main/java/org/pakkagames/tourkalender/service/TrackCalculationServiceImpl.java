package org.pakkagames.tourkalender.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.pakkagames.tourkalender.gpx.domain.Track;
import org.pakkagames.tourkalender.gpx.domain.TrackSegment;
import org.pakkagames.tourkalender.gpx.domain.WayPoint;
import org.springframework.stereotype.Service;

@Service
public class TrackCalculationServiceImpl implements TrackCalculationService {

	private Double calculateAverageSpeed(Track track, Integer baseTimePeriod) {
		Double averageSpeed = new Double(-1);

		ArrayList<TrackSegment> trackSegments = track.getTrackSegments();

		return averageSpeed;
	}

	public Double calculateDistance(Track track) {

		Integer RADIUS_EARTH = 6370;
		Double distance = new Double(0);

		ArrayList<TrackSegment> trackSegments = track.getTrackSegments();

		ArrayList<WayPoint> wayPoints = trackSegments.get(0).getWayPoints();
		WayPoint lastWayPoint = null;
		for (WayPoint wayPoint : wayPoints) {
			if (lastWayPoint == null) {
				lastWayPoint = wayPoint;
			}
			else {
				double lat1 = lastWayPoint.getLatitude();
				double lat2 = wayPoint.getLatitude();
				double lon1 = lastWayPoint.getLongitude();
				double lon2 = wayPoint.getLongitude();
				double sqrt_value = Math.pow(Math.sin((lat1 - lat2) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lon1 - lon2) / 2), 2);
				distance = distance + 2 * Math.asin(Math.sqrt(sqrt_value));
			}
		}
		return distance * RADIUS_EARTH;
	}

	public Double calculateDistanceClassic(Track track) {

		Double distance = new Double(0);
		BigDecimal distanceBig = new BigDecimal(0);
		BigDecimal DEFAULT =  new BigDecimal(111.111);
		Double sumLongitude = new Double(0);
		Double sumLatitude = new Double(0);

		Double minLatitude = new Double(100);
		Double maxLatitude = new Double(-1);

		ArrayList<TrackSegment> trackSegments = track.getTrackSegments();

		ArrayList<WayPoint> wayPoints = trackSegments.get(0).getWayPoints();
		WayPoint lastWayPoint = null;
		for (WayPoint wayPoint : wayPoints) {
			if (lastWayPoint != null) {
				double lat1 = lastWayPoint.getLatitude();
				double lat2 = wayPoint.getLatitude();
				double lon1 = lastWayPoint.getLongitude();
				double lon2 = wayPoint.getLongitude();
				sumLongitude = sumLongitude + Math.abs(lon1 - lon2);
				sumLatitude = sumLatitude + Math.abs(lat1 - lat2);
			}
			if (wayPoint.getLatitude() < minLatitude) {
				minLatitude = wayPoint.getLatitude();
			}
			if (wayPoint.getLatitude() > maxLatitude) {
				maxLatitude = wayPoint.getLatitude();
			}
			lastWayPoint = wayPoint;
		}
		double latMiddle = (minLatitude + maxLatitude) / 2 * 0.01745;
		distance = distance + Math.sqrt(Math.pow(111.111 * sumLatitude, 2) + Math.pow(111.111 * Math.cos(latMiddle) * sumLongitude, 2));
		
		BigDecimal sumLatitudeBig = new BigDecimal(sumLatitude);
		BigDecimal sumLongitudeBig = new BigDecimal(sumLongitude);
		
		distanceBig = sqrt(sumLatitudeBig.multiply(DEFAULT).pow(2).add(sumLongitudeBig.multiply(new BigDecimal(Math.cos(latMiddle))).multiply(DEFAULT).pow(2)),15).add(distanceBig);

		return distanceBig.doubleValue();
	}

	private BigDecimal sqrt(BigDecimal A, final int SCALE) {
		BigDecimal TWO = new BigDecimal(2);
		BigDecimal x0 = new BigDecimal("0");
		BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
		while (!x0.equals(x1)) {
			x0 = x1;
			x1 = A.divide(x0, SCALE, RoundingMode.HALF_UP);
			x1 = x1.add(x0);
			x1 = x1.divide(TWO, SCALE, RoundingMode.HALF_UP);

		}
		return x1;
	}

}
