package org.pakkagames.tourkalender.gpx.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface ExtensionParserService {

	public String getId();

	public Object parseExtensions(Node node);

	public void writeExtensions(Node node, Document doc);

}
