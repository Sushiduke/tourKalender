package org.pakkagames.tourkalender.controller;

import java.util.ArrayList;
import java.util.List;

import org.pakkagames.tourkalender.domain.Position;
import org.pakkagames.tourkalender.response.PositionResponse;
import org.pakkagames.tourkalender.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles all REST calls for reading information on {@link Position}.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */

@RestController
public class GetPositionController {
	final static Logger LOGGER = LoggerFactory.getLogger(GetPositionController.class);

	@Autowired
	private PositionService positionService;

	@GetMapping(value = "/getDataOfHour")
	public PositionResponse getPositions(@RequestParam(value = "numberHours", defaultValue = "1") Integer numberHours) {
		LOGGER.info("In AJAX getDataOfHour");
		PositionResponse response = new PositionResponse();
		response.setPositions(positionService.findLatestPositions(numberHours));
		return response;
	}

	@GetMapping(value = "/getAll")
	public PositionResponse getAllPositions() {
		LOGGER.info("In AJAX getAll");
		PositionResponse response = new PositionResponse();

		List<Position> positions = new ArrayList<Position>();
		positionService.findAllPositions().iterator().forEachRemaining(positions::add);

		response.setPositions(positions);
		return response;
	}
}
