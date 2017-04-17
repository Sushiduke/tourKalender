package org.pakkagames.tourkalender.controller;

import java.util.HashMap;
import java.util.Map;

import org.pakkagames.tourkalender.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

	final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private PositionService positionService;


	@PostMapping(value = "/showSW")
	public void index() {
		RestTemplate template = new RestTemplate();
		
		String token = "";
		String url = "";
		Map<String, String> uriVariables = new HashMap<String, String>();
		token = template.postForObject(url, null, String.class, uriVariables);
		return ;
	}

}
