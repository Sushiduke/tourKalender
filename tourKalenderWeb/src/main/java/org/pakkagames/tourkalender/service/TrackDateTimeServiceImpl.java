package org.pakkagames.tourkalender.service;

import lombok.NonNull;
import org.pakkagames.tourkalender.exception.TrackProcessException;
import org.pakkagames.tourkalender.gpx.model.WayPoint;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackDateTimeServiceImpl implements TrackDateTimeService {

    private final static int FIRST = 0;

    @Override
    public List<WayPoint> changeAllDateTime(@NonNull List<WayPoint> wayPoints, OffsetDateTime newStartDateTime) {
        try {
            return changeAllDateTime(wayPoints, Duration.between(newStartDateTime, wayPoints.get(FIRST).getTime()));
        } catch (IndexOutOfBoundsException ex) {
            throw new TrackProcessException("The amount of wayPoint must be at least one");
        }
    }

    @Override
    public List<WayPoint> changeAllDateTime(@NonNull List<WayPoint> wayPoints, Duration difference) {

        return wayPoints.stream()
                .peek(wayPoint -> wayPoint.setTime(wayPoint.getTime().plus(difference.toSeconds(), ChronoUnit.SECONDS)))
                .collect(Collectors.toList());
    }
}
