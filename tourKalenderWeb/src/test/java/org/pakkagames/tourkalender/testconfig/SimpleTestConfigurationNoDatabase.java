package org.pakkagames.tourkalender.testconfig;

import org.pakkagames.tourkalender.service.TrackCalculationService;
import org.pakkagames.tourkalender.service.TrackCalculationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author jog
 */
@Configuration
@ComponentScan( basePackages = { "org.pakkagames.tourkalender.gpx.service" })
@Profile("test")
public class SimpleTestConfigurationNoDatabase {

	@Bean
	public TrackCalculationService getTrackCalculationService(){
		return new TrackCalculationServiceImpl();
	}
}