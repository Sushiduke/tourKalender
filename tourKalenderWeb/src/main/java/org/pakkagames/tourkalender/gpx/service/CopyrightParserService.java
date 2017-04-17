package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Copyright;
import org.w3c.dom.Node;

public interface CopyrightParserService {

	public Copyright parseCopyright(Node node);
}
