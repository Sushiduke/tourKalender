package org.pakkagames.tourkalender.controller;

import org.pakkagames.tourkalender.domain.Position;
import org.pakkagames.tourkalender.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller that handles all calls with write access to the {@link Position}.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */
@Controller
public class SavePositionController {

	final static Logger LOGGER = LoggerFactory.getLogger(SavePositionController.class);

	@Autowired
	private PositionService positionService;

	@PostMapping(value = "/savePosition", headers = "content-type=application/json")
	public ModelAndView savePosition(@RequestBody Position position) {

		LOGGER.info("***************************************bin in SavePositionController::savePosition************************************");
		ModelAndView modelView = new ModelAndView("showJogi");

		LOGGER.info("position: " + position);
		LOGGER.info("position.latitude: " + (position != null ? position.getLatitude() : null));

		positionService.createPosition(position);
		return modelView;
	}
}
