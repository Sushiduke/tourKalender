package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Track;
import org.w3c.dom.Node;

public interface TrackParserService {

	public Track parseTrack(Node node) throws Exception;
}
