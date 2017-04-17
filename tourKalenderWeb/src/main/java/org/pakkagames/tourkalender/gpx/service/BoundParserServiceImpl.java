package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Bounds;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Service
public class BoundParserServiceImpl implements BoundParserService {

	public Bounds parseBounds(Node node) {
		if (node == null) {
			return null;
		}

		Bounds bounds = new Bounds(0, 0, 0, 0);

		NamedNodeMap attrs = node.getAttributes();

		for (int idx = 0; idx < attrs.getLength(); idx++) {
			Node attr = attrs.item(idx);
			if (GPXConstant.ATTR_MINLAT.equals(attr.getNodeName())) {
				bounds.setMinLatitude(Double.parseDouble(attr.getNodeValue()));
			}
			else if (GPXConstant.ATTR_MINLON.equals(attr.getNodeName())) {
				bounds.setMinLongitude(Double.parseDouble(attr.getNodeValue()));
			}
			else if (GPXConstant.ATTR_MAXLAT.equals(attr.getNodeName())) {
				bounds.setMaxLatitude(Double.parseDouble(attr.getNodeValue()));
			}
			else if (GPXConstant.ATTR_MAXLON.equals(attr.getNodeName())) {
				bounds.setMaxLongitude(Double.parseDouble(attr.getNodeValue()));
			}
		}

		return bounds;
	}
}
