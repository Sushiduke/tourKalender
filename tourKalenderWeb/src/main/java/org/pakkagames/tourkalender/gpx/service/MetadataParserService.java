package org.pakkagames.tourkalender.gpx.service;

import java.text.ParseException;

import org.pakkagames.tourkalender.gpx.domain.Metadata;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public interface MetadataParserService {

	public Metadata parseMetadata(Node node) throws DOMException, ParseException;
}
