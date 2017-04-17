package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Route;
import org.w3c.dom.Node;

public interface RouteParserService {

	public Route parseRoute(Node node) throws Exception;
}
