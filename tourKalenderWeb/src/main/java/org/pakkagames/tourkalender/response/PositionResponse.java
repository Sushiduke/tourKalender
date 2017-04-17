package org.pakkagames.tourkalender.response;

import java.io.Serializable;
import java.util.List;

import org.pakkagames.tourkalender.domain.Position;

public class PositionResponse implements Serializable {

	private static final long serialVersionUID = -8373166378246629816L;

	private List<Position> positions;

	public List<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
}
