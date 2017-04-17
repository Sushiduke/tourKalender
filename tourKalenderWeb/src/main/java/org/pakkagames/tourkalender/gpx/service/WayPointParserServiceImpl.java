package org.pakkagames.tourkalender.gpx.service;

import java.util.HashSet;
import java.util.Iterator;

import org.pakkagames.tourkalender.gpx.domain.Link;
import org.pakkagames.tourkalender.gpx.domain.WayPoint;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class WayPointParserServiceImpl implements WayPointParserService {

	@Autowired
	GPXParserService GPXParser;

	@Autowired
	LinkParserService linkParser;

	public WayPoint parseWaypoint(Node node) throws Exception {
		if (node == null) {
			return null;
		}
		WayPoint waypoint = new WayPoint(0, 0);
		NamedNodeMap attrs = node.getAttributes();
		// check for lat attribute
		Node latNode = attrs.getNamedItem(GPXConstant.ATTR_LAT);
		if (latNode != null) {
			Double latVal = null;
			latVal = Double.parseDouble(latNode.getNodeValue());
			waypoint.setLatitude(latVal);
		}
		else {
			throw new Exception("no lat value in waypoint data.");
		}
		// check for lon attribute
		Node lonNode = attrs.getNamedItem(GPXConstant.ATTR_LON);
		if (lonNode != null) {
			Double lonVal = Double.parseDouble(lonNode.getNodeValue());
			waypoint.setLongitude(lonVal);
		}
		else {
			throw new Exception("no lon value in waypoint data.");
		}

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstant.NODE_ELE.equals(currentNode.getNodeName())) {
					waypoint.setElevation(GPXParser.getNodeValueAsDouble(currentNode));
				}
				else if (GPXConstant.NODE_TIME.equals(currentNode.getNodeName())) {
					waypoint.setTime(GPXParser.getNodeValueAsDate(currentNode));
				}
				else if (GPXConstant.NODE_MAGVAR.equals(currentNode.getNodeName())) {
					waypoint.setMagneticVariation(GPXParser.getNodeValueAsDouble(currentNode));
				}
				else if (GPXConstant.NODE_GEOIDHEIGHT.equals(currentNode.getNodeName())) {
					waypoint.setGeoIdHeight(GPXParser.getNodeValueAsDouble(currentNode));
				}
				else if (GPXConstant.NODE_NAME.equals(currentNode.getNodeName())) {
					waypoint.setName(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_CMT.equals(currentNode.getNodeName())) {
					waypoint.setComment(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_DESC.equals(currentNode.getNodeName())) {
					waypoint.setDescription(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_SRC.equals(currentNode.getNodeName())) {
					waypoint.setSrc(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = linkParser.parseLink(currentNode);

					if (link != null) {
						if (waypoint.getLink() == null) {
							waypoint.setLink(new HashSet<Link>());
						}
						waypoint.getLink().add(link);
					}
				}
				else if (GPXConstant.NODE_SYM.equals(currentNode.getNodeName())) {
					waypoint.setSym(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_TYPE.equals(currentNode.getNodeName())) {
					waypoint.setType(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_FIX.equals(currentNode.getNodeName())) {
					waypoint.setFix(GPXParser.getNodeValueAsFixType(currentNode));
				}
				else if (GPXConstant.NODE_SAT.equals(currentNode.getNodeName())) {
					waypoint.setSat(GPXParser.getNodeValueAsInteger(currentNode));
				}
				else if (GPXConstant.NODE_HDOP.equals(currentNode.getNodeName())) {
					waypoint.setHdop(GPXParser.getNodeValueAsDouble(currentNode));
				}
				else if (GPXConstant.NODE_VDOP.equals(currentNode.getNodeName())) {
					waypoint.setVdop(GPXParser.getNodeValueAsDouble(currentNode));
				}
				else if (GPXConstant.NODE_PDOP.equals(currentNode.getNodeName())) {
					waypoint.setPdop(GPXParser.getNodeValueAsDouble(currentNode));
				}
				else if (GPXConstant.NODE_AGEOFGPSDATA.equals(currentNode.getNodeName())) {
					waypoint.setAgeOfGpsData(GPXParser.getNodeValueAsDouble(currentNode));
				}
				else if (GPXConstant.NODE_DGPSID.equals(currentNode.getNodeName())) {
					waypoint.setdGpsStationId(GPXParser.getNodeValueAsInteger(currentNode));
				}
				else if (GPXConstant.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<ExtensionParserService> it = GPXParser.getExtensionParsers().iterator();
					while (it.hasNext()) {
						ExtensionParserService parser = it.next();
						Object data = parser.parseExtensions(currentNode);
						waypoint.addExtensionData(parser.getId(), data);
					}
				}
			}
		}

		return waypoint;
	}

}
