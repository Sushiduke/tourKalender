package org.pakkagames.tourkalender.gpx.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.pakkagames.tourkalender.gpx.domain.Link;
import org.pakkagames.tourkalender.gpx.domain.Track;
import org.pakkagames.tourkalender.gpx.domain.TrackSegment;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class TrackParserServiceImpl implements TrackParserService {

	@Autowired
	GPXParserService GPXParser;

	@Autowired
	LinkParserService linkParser;

	@Autowired
	TrackSegmentParserService trackSegmentParser;

	public Track parseTrack(Node node) throws Exception {
		if (node == null) {
			return null;
		}
		Track track = new Track();
		NodeList nodes = node.getChildNodes();
		if (nodes != null) {
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node currentNode = nodes.item(idx);
				if (GPXConstant.NODE_NAME.equals(currentNode.getNodeName())) {
					track.setName(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_CMT.equals(currentNode.getNodeName())) {
					track.setComment(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_DESC.equals(currentNode.getNodeName())) {
					track.setDescription(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_SRC.equals(currentNode.getNodeName())) {
					track.setSrc(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_LINK.equals(currentNode.getNodeName())) {
					Link link = linkParser.parseLink(currentNode);
					if (link != null) {
						if (track.getLinks() == null) {
							track.setLinks(new HashSet<Link>());
						}
						track.getLinks().add(link);
					}
				}
				else if (GPXConstant.NODE_NUMBER.equals(currentNode.getNodeName())) {
					track.setNumber(GPXParser.getNodeValueAsInteger(currentNode));
				}
				else if (GPXConstant.NODE_TYPE.equals(currentNode.getNodeName())) {
					track.setType(GPXParser.getNodeValueAsString(currentNode));
				}
				else if (GPXConstant.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					Iterator<ExtensionParserService> it = GPXParser.getExtensionParsers().iterator();
					while (it.hasNext()) {
						while (it.hasNext()) {
							ExtensionParserService parser = it.next();
							Object data = parser.parseExtensions(currentNode);
							track.addExtensionData(parser.getId(), data);
						}
					}
				}
				else if (GPXConstant.NODE_TRKSEG.equals(currentNode.getNodeName())) {
					TrackSegment trackSegment = trackSegmentParser.parseTrackSegment(currentNode);
					if (trackSegment != null) {
						if (track.getTrackSegments() == null) {
							track.setTrackSegments(new ArrayList<TrackSegment>());
						}
						track.getTrackSegments().add(trackSegment);
					}
				}
			}
		}

		return track;
	}
}
