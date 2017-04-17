package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Bounds;
import org.w3c.dom.Node;

public interface BoundParserService {

	public Bounds parseBounds(Node node);
}
