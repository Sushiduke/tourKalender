package org.pakkagames.tourkalender.gpx.service;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.pakkagames.tourkalender.gpx.domain.Fix;
import org.pakkagames.tourkalender.gpx.domain.GPX;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/**
 * Interface that contains all service methods handling with parsing GPX representations.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */
public interface GPXParserService {

	public GPX parseGPXTour(InputStream input) throws Exception;

	public Date getNodeValueAsDate(Node node) throws DOMException, ParseException;

	public Double getNodeValueAsDouble(Node node);

	public Fix getNodeValueAsFixType(Node node);

	public Integer getNodeValueAsInteger(Node node);

	public String getNodeValueAsString(Node node);

	public ArrayList<ExtensionParserService> getExtensionParsers();
}
