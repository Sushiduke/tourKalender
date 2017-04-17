package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.WayPoint;
import org.w3c.dom.Node;

public interface WayPointParserService {

	public WayPoint parseWaypoint(Node node) throws Exception;
}
