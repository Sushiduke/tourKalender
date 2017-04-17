package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Copyright;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class CopyrightParserServiceImpl implements CopyrightParserService {

	@Autowired
	GPXParserService GPXParser;

	public Copyright parseCopyright(Node node) {
		if (node == null) {
			return null;
		}

		Copyright copyright = new Copyright(null);

		NamedNodeMap attrs = node.getAttributes();

		for (int idx = 0; idx < attrs.getLength(); idx++) {
			Node attr = attrs.item(idx);
			if (GPXConstant.ATTR_AUTHOR.equals(attr.getNodeName())) {
				copyright.setAuthor(attr.getNodeValue());
			}
		}

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {

			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstant.NODE_YEAR.equals(currentNode.getNodeName())) {
					copyright.setYear(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_LICENSE.equals(currentNode.getNodeName())) {
					copyright.setLicense(GPXParser.getNodeValueAsString(currentNode));
				}
			}
		}

		return copyright;
	}
}
