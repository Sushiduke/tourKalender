package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.TrackSegment;
import org.w3c.dom.Node;

public interface TrackSegmentParserService {

	public TrackSegment parseTrackSegment(Node node) throws Exception;
}
