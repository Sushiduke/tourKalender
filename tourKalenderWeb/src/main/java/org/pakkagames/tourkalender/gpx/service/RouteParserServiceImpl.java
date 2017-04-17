package org.pakkagames.tourkalender.gpx.service;

import java.util.HashSet;
import java.util.Iterator;

import org.pakkagames.tourkalender.gpx.domain.Link;
import org.pakkagames.tourkalender.gpx.domain.Route;
import org.pakkagames.tourkalender.gpx.domain.WayPoint;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class RouteParserServiceImpl implements RouteParserService {

	@Autowired
	GPXParserService GPXParser;

	@Autowired
	LinkParserService linkParser;

	@Autowired
	WayPointParserService waypointParser;

	public Route parseRoute(Node node) throws Exception {
		if (node == null) {
			return null;
		}
		Route route = new Route();
		NodeList nodes = node.getChildNodes();
		if (nodes != null) {
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node currentNode = nodes.item(idx);
				if (GPXConstant.NODE_NAME.equals(currentNode.getNodeName())) {
					route.setName(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_CMT.equals(currentNode.getNodeName())) {
					route.setComment(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_DESC.equals(currentNode.getNodeName())) {
					route.setDescription(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_SRC.equals(currentNode.getNodeName())) {
					route.setSrc(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = linkParser.parseLink(currentNode);
					if (route.getLinks() == null) {
						route.setLinks(new HashSet<Link>());
					}
					route.getLinks().add(link);
				}
				else if (GPXConstant.NODE_NUMBER.equals(currentNode.getNodeName())) {
					route.setNumber(GPXParser.getNodeValueAsInteger(currentNode));
				}
				else if (GPXConstant.NODE_TYPE.equals(currentNode.getNodeName())) {
					route.setType(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<ExtensionParserService> it = GPXParser.getExtensionParsers().iterator();
					while (it.hasNext()) {
						while (it.hasNext()) {
							ExtensionParserService parser = it.next();
							Object data = parser.parseExtensions(currentNode);
							route.addExtensionData(parser.getId(), data);
						}
					}
				}
				else if (GPXConstant.NODE_RTEPT.equals(currentNode.getNodeName())) {
					WayPoint waypoint = waypointParser.parseWaypoint(currentNode);
					route.getRoutePoints().add(waypoint);
				}
			}
		}

		return route;
	}
}
