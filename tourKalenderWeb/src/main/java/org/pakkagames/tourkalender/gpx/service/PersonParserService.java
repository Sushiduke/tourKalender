package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Person;
import org.w3c.dom.Node;

public interface PersonParserService {

	public Person parsePerson(Node node);
}
