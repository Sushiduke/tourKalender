package org.pakkagames.tourkalender.controller;

import javax.servlet.http.HttpServletRequest;

import org.pakkagames.tourkalender.domain.Position;
import org.pakkagames.tourkalender.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.unbescape.html.HtmlEscape;

@Controller
public class MainController {

	final static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private PositionService positionService;

	/**
	 * Default entry point to show the main page
	 */
	@GetMapping(value = "/")
	public ModelAndView getShowJogi() {
		ModelAndView modelView = new ModelAndView("showJogi_tab");
		Position startPoint = positionService.getLatestPosition();
		modelView.addObject("startPoint", startPoint);
		return modelView;
	}

	@GetMapping(value = "/index.html")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("index");
		return modelView;
	}

	@GetMapping(value = "/upload")
	public ModelAndView upload() {
		ModelAndView modelView = new ModelAndView("upload");
		return modelView;
	}

	@GetMapping(value = "/error.html")
	public ModelAndView error(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView("error");

		modelView.addObject("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append("<ul>");
		while (throwable != null) {
			errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
			throwable = throwable.getCause();
		}
		errorMessage.append("</ul>");
		modelView.addObject("errorMessage", errorMessage.toString());
		return modelView;
	}
}
