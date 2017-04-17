package org.pakkagames.tourkalender.service;

import org.pakkagames.tourkalender.gpx.domain.Track;

public interface TrackCalculationService {

	public Double calculateDistance(Track track);

	public Double calculateDistanceClassic(Track track);
}
