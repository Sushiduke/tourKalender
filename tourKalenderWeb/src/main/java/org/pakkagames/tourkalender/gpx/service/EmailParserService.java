package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Email;
import org.w3c.dom.Node;

public interface EmailParserService {

	public Email parseEmail(Node node);
}
