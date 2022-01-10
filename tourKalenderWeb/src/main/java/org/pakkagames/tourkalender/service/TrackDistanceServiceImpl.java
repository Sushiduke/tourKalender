package org.pakkagames.tourkalender.service;

import lombok.NonNull;
import org.pakkagames.tourkalender.gpx.model.Track;
import org.pakkagames.tourkalender.gpx.model.WayPoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.HALF_DOWN;
import static org.nd4j.linalg.util.BigDecimalMath.PI;

@Service
public class TrackDistanceServiceImpl implements TrackDistanceService {

    private static final BigDecimal ONE = new BigDecimal("1");
    private static final BigDecimal TWO = new BigDecimal("2");

    private static final BigDecimal NO_DISTANCE = new BigDecimal("0");
    private static final BigDecimal RADIUS_EARTH = new BigDecimal("6371.0008");
    private static final BigDecimal PI_DIV_180 = PI.divide(new BigDecimal("180"), HALF_DOWN);


    private BigDecimal calculateAverageSpeed(Track track, Integer baseTimePeriod) {
        return new BigDecimal("0");
    }

    /**
     * Method calculates the distance of all {@link WayPoint}s of the first {@link org.pakkagames.tourkalender.gpx.model.TrackSegment} of the track.
     * <br/>
     * It based on the haversine formula that determines the great-circle distance between two points on a sphere given
     * their longitudes and latitudes.
     *
     * @param wayPoints list of waypoints that to calculate the distance of.
     * @return distance in km of all waypoints.
     */
    @Override
    public BigDecimal calculateDistance(@NonNull List<WayPoint> wayPoints) {

        WayPoint lastWayPoint = null;
        BigDecimal distance = NO_DISTANCE;
        for (WayPoint wayPoint : wayPoints) {
            if (lastWayPoint == null) {
                lastWayPoint = wayPoint;
            } else {
                BigDecimal lat1Radians = lastWayPoint.getLatitude().multiply(PI_DIV_180);
                BigDecimal lat2Radians = wayPoint.getLatitude().multiply(PI_DIV_180);
                BigDecimal deltaLatRadians = lastWayPoint.getLatitude().subtract(wayPoint.getLatitude()).multiply(PI_DIV_180);
                BigDecimal deltaLonRadians = lastWayPoint.getLongitude().subtract(wayPoint.getLongitude()).multiply(PI_DIV_180);

                BigDecimal sinusLatitude = this.sin(deltaLatRadians.divide(TWO, HALF_DOWN));
                BigDecimal cosinusLatitude = this.cos(lat1Radians).multiply(this.cos(lat2Radians));
                BigDecimal sinusLongitude = this.sin(deltaLonRadians.divide(TWO, HALF_DOWN));

                BigDecimal squareOfHalfTheChordLength = sinusLatitude.pow(2).add((cosinusLatitude).multiply(sinusLongitude.pow(2)));

                BigDecimal angularDistance = TWO.multiply(this.atan2(squareOfHalfTheChordLength.sqrt(DECIMAL64), (ONE.subtract(squareOfHalfTheChordLength)).sqrt(DECIMAL64)));
                distance = distance.add(angularDistance);
            }
            lastWayPoint = wayPoint;
        }
        return distance.multiply(RADIUS_EARTH);
    }

    /**
     * Returns the arcus tangus of two Cartesian coordinates.
     *
     * @param ordinate the ordinate coordinate
     * @param abscissa the abscissa coordinate
     * @return the theta component of the point (r, theta) in polar coordinates that corresponds to the point (x, y) in Cartesian coordinates.
     */
    private BigDecimal atan2(BigDecimal ordinate, BigDecimal abscissa) {
        return BigDecimal.valueOf(Math.atan2(ordinate.doubleValue(), abscissa.doubleValue()));
    }

    private BigDecimal sin(BigDecimal angleRadion) {
        return BigDecimal.valueOf(Math.sin(angleRadion.doubleValue()));
    }

    private BigDecimal cos(BigDecimal angleRadion) {
        return BigDecimal.valueOf(Math.cos(angleRadion.doubleValue()));
    }

}
