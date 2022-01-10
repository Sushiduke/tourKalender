package org.pakkagames.tourkalender.service;

import lombok.NonNull;
import org.pakkagames.tourkalender.gpx.model.WayPoint;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface description for methods that calculate distances.
 */
public interface TrackDistanceService {

    /**
     * method description to calculate distances of {@link WayPoint}.
     *
     * @param wayPoints list of waypoint to calculate the distance of.
     * @return distance in km.
     */
    BigDecimal calculateDistance(@NonNull List<WayPoint> wayPoints);

}
