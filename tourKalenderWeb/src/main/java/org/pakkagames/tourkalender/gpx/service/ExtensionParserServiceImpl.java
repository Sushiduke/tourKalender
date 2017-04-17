package org.pakkagames.tourkalender.gpx.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

@Service
public class ExtensionParserServiceImpl implements ExtensionParserService {

	@Override
	public String getId() {
		return "Basic Extension Parser";
	}

	@Override
	public Object parseExtensions(Node node) {
		// TODO get your object from the node
		return null;
	}

	@Override
	public void writeExtensions(Node node, Document doc) {
		// TODO write your object to node
	}

}
