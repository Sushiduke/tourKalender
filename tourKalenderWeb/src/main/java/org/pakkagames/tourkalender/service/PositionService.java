package org.pakkagames.tourkalender.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.pakkagames.tourkalender.domain.Position;

/**
 * Interface that contains all service methods handling with {@link Position}.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */

public interface PositionService {
	public Position createPosition(Position position);

	public Iterable<Position> findAllPositions();

	// Java 8
	public List<Position> findPositions(ZonedDateTime fromDate, ZonedDateTime toDate);

	// Java 7
	// public List<Position> findPositions(DateTime fromDate, DateTime toDate);

	public List<Position> findLatestPositions(Integer numberHours);

	public Position getLatestPosition();

	public Position getFirstPositionOfCurrentTrack();
}
