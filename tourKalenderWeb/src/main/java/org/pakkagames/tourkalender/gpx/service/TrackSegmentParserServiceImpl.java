package org.pakkagames.tourkalender.gpx.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.pakkagames.tourkalender.gpx.domain.TrackSegment;
import org.pakkagames.tourkalender.gpx.domain.WayPoint;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class TrackSegmentParserServiceImpl implements TrackSegmentParserService {

	@Autowired
	GPXParserService GPXParser;

	@Autowired
	WayPointParserService waypointService;

	public TrackSegment parseTrackSegment(Node node) throws Exception {
		if (node == null) {
			return null;
		}

		TrackSegment trackSegment = new TrackSegment();
		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int idx = 0; idx < childNodes.getLength(); idx++) {
				Node currentNode = childNodes.item(idx);
				if (GPXConstant.NODE_TRKPT.equals(currentNode.getNodeName())) {
					WayPoint waypoint = waypointService.parseWaypoint(currentNode);

					if (waypoint != null) {
						if (trackSegment.getWayPoints() == null) {
							trackSegment.setWayPoints(new ArrayList<WayPoint>());
						}
						trackSegment.getWayPoints().add(waypoint);
					}
				}
				else if (GPXConstant.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<ExtensionParserService> it = GPXParser.getExtensionParsers().iterator();
					while (it.hasNext()) {
						while (it.hasNext()) {
							ExtensionParserService parser = it.next();
							Object data = parser.parseExtensions(currentNode);
							trackSegment.addExtensionData(parser.getId(), data);
						}
					}
				}
			}
		}

		return trackSegment;
	}
}
