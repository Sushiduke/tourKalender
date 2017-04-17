package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Link;
import org.w3c.dom.Node;

public interface LinkParserService {

	public Link parseLink(Node node);
}
