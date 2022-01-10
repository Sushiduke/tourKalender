package org.pakkagames.tourkalender.service;

import lombok.NonNull;
import org.pakkagames.tourkalender.gpx.model.WayPoint;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Interface description of methods to change date and time.
 */
public interface TrackDateTimeService {

    /**
     * Method description to change date and time of {@link WayPoint} with a given new start dateTime.
     *
     * @param wayPoints        list of {@link WayPoint}s that date and time should be changed.
     * @param newStartDateTime TODO
     */
    List<WayPoint> changeAllDateTime(@NonNull List<WayPoint> wayPoints, OffsetDateTime newStartDateTime);

    /**
     * Method description to change date and time of {@link WayPoint} with a given difference as {@link Duration}.
     *
     * @param wayPoints TODO
     * @param difference TODO
     */
    List<WayPoint> changeAllDateTime(@NonNull List<WayPoint> wayPoints, Duration difference);

}
