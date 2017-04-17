package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Link;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class LinkParserServiceImpl implements LinkParserService {

	@Autowired
	GPXParserService GPXParser;

	public Link parseLink(Node node) {
		if (node == null) {
			return null;
		}

		Link link = new Link(null);
		NamedNodeMap attrs = node.getAttributes();

		for (int idx = 0; idx < attrs.getLength(); idx++) {
			Node attr = attrs.item(idx);
			if (GPXConstant.ATTR_HREF.equals(attr.getNodeName())) {
				link.setHref(attr.getNodeValue());
			}
		}

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstant.NODE_TEXT.equals(currentNode.getNodeName())) {
					link.setText(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_TYPE.equals(currentNode.getNodeName())) {
					link.setType(GPXParser.getNodeValueAsString(currentNode));
				}
			}
		}

		return link;
	}
}
