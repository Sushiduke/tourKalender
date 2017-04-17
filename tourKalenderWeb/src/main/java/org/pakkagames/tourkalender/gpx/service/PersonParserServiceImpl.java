package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Email;
import org.pakkagames.tourkalender.gpx.domain.Link;
import org.pakkagames.tourkalender.gpx.domain.Person;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class PersonParserServiceImpl implements PersonParserService {

	@Autowired
	GPXParserService GPXParser;

	@Autowired
	EmailParserService emailParser;

	@Autowired
	LinkParserService linkParser;

	public Person parsePerson(Node node) {
		if (node == null) {
			return null;
		}

		Person person = new Person();
		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstant.NODE_NAME.equals(currentNode.getNodeName())) {
					person.setName(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_EMAIL.equals(currentNode.getNodeName())) {
					Email email = emailParser.parseEmail(currentNode);
					if (email != null) {
						person.setEmail(email);
					}
				}
				else if (GPXConstant.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = linkParser.parseLink(currentNode);
					if (link != null) {
						person.setLink(link);
					}
				}
			}
		}

		return person;
	}
}
