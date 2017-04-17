package org.pakkagames.tourkalender.service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.pakkagames.tourkalender.domain.Position;
import org.pakkagames.tourkalender.repository.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the PositionService using repository calls
 * {@link PositionRepository}.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

	final static Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);

	@Autowired
	private PositionRepository positionRepository;

	@Override
	public Position createPosition(Position position) {
		LOGGER.debug("PositionServiceImpl::createPosition: position: " + position);
		return positionRepository.save(position);
	}

	@Override
	public Iterable<Position> findAllPositions() {
		return positionRepository.findAll();
	}

	@Override
	public List<Position> findPositions(ZonedDateTime fromDate, ZonedDateTime toDate) {
		LOGGER.debug("fromDate: " + Date.from(fromDate.toInstant()));
		LOGGER.debug("toDate: " + Date.from(toDate.toInstant()));
		return positionRepository.findPosition(Date.from(fromDate.toInstant()), Date.from(toDate.toInstant()));
	}

	@Override
	public List<Position> findLatestPositions(Integer numberHours) {
		Instant latestDate = getLatestPosition().getDateTime().toInstant();
		Instant numberOfHoursBack = latestDate.minus(numberHours, ChronoUnit.HOURS);
		return positionRepository.findPosition(Date.from(numberOfHoursBack), Date.from(latestDate));
	}

	@Override
	public Position getLatestPosition() {
		List<Position> positions = new ArrayList<Position>();
		positionRepository.findAll().iterator().forEachRemaining(positions::add);
		return sortPositions(positions).get(0);
	}

	public Position getFirstPositionOfCurrentTrack() {
		Position retval = null;
		List<Position> positions = new ArrayList<Position>();
		positionRepository.findAll().iterator().forEachRemaining(positions::add);
		List<Position> all = sortPositions(positions);
		for (Position p : all) {
			if (retval == null) {
				retval = p;
			}
		}
		return retval;
	}

	private List<Position> sortPositions(List<Position> positions) {
		Comparator<Position> latestFirstComparator = new Comparator<Position>() {
			public int compare(Position p1, Position p2) {
				return p2.getDateTime().compareTo(p1.getDateTime());
			}
		};
		Collections.sort(positions, latestFirstComparator);
		return positions;
	}

}
