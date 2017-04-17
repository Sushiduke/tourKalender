package org.pakkagames.tourkalender.repository;

import java.util.Date;
import java.util.List;

import org.pakkagames.tourkalender.domain.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository class to have CRUD operations on the {@link Position} domain.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */

@Repository
@EnableJpaRepositories
public interface PositionRepository extends CrudRepository<Position, Long> {

	public final static String FROMDATE_TODATE = "select p from Position p where " //
			+ "p.dateTime >= :fromDate and p.dateTime <= :toDate";

	@Query(FROMDATE_TODATE)
	List<Position> findPosition(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
