package org.pakkagames.tourkalender.gpx.service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;

import org.pakkagames.tourkalender.gpx.domain.Bounds;
import org.pakkagames.tourkalender.gpx.domain.Copyright;
import org.pakkagames.tourkalender.gpx.domain.Link;
import org.pakkagames.tourkalender.gpx.domain.Metadata;
import org.pakkagames.tourkalender.gpx.domain.Person;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class MetadataParserServiceImpl implements MetadataParserService {

	@Autowired
	GPXParserService GPXParser;

	@Autowired
	PersonParserService personParser;

	@Autowired
	BoundParserService boundParser;

	@Autowired
	LinkParserService linkParser;

	@Autowired
	CopyrightParserService copyrightParser;

	@Autowired
	ExtensionParserService extensionParser;

	public Metadata parseMetadata(Node node) throws DOMException, ParseException {
		if (node == null) {
			return null;
		}

		Metadata metadata = new Metadata();

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstant.NODE_NAME.equals(currentNode.getNodeName())) {
					metadata.setName(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_DESC.equals(currentNode.getNodeName())) {
					metadata.setDesc(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_AUTHOR.equals(currentNode.getNodeName())) {
					Person author = personParser.parsePerson(currentNode);
					if (author != null) {
						metadata.setAuthor(author);
					}
				}
				else if (GPXConstant.NODE_COPYRIGHT.equals(currentNode.getNodeName())) {
					Copyright copyright = copyrightParser.parseCopyright(currentNode);
					if (copyright != null) {
						metadata.setCopyright(copyright);
					}
				}
				else if (GPXConstant.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = linkParser.parseLink(currentNode);
					if (link != null) {
						if (metadata.getLinks() == null) {
							metadata.setLinks(new HashSet<Link>());
						}
						metadata.getLinks().add(link);
					}
				}
				else if (GPXConstant.NODE_TIME.equals(currentNode.getNodeName())) {
					metadata.setTime(GPXParser.getNodeValueAsDate(currentNode));
				}
				else if (GPXConstant.NODE_KEYWORDS.equals(currentNode.getNodeName())) {
					metadata.setKeywords(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_BOUNDS.equals(currentNode.getNodeName())) {
					Bounds bounds = boundParser.parseBounds(currentNode);
					if (bounds != null) {
						metadata.setBounds(bounds);
					}
				}
				else if (GPXConstant.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<ExtensionParserService> it = GPXParser.getExtensionParsers().iterator();
					while (it.hasNext()) {
						ExtensionParserService parser = it.next();
						Object data = parser.parseExtensions(currentNode);
						metadata.addExtensionData(parser.getId(), data);
					}
				}
			}
		}
		return metadata;
	}
}
