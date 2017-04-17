package org.pakkagames.tourkalender.gpx.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BaseGPX {

	protected final SimpleDateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	protected final ArrayList<ExtensionParserService> extensionParsers = new ArrayList<ExtensionParserService>();

	/**
	 * Adds a new extension parser to be used when parsing a gpx steam
	 *
	 * @param parser an instance of a {@link ExtensionParserService} implementation
	 */
	public void addExtensionParser(ExtensionParserService parser) {
		this.extensionParsers.add(parser);
	}

	/**
	 * Removes an extension parser previously added
	 *
	 * @param parser an instance of a {@link ExtensionParserService} implementation
	 */
	public void removeExtensionParser(ExtensionParserService parser) {
		this.extensionParsers.remove(parser);
	}
}
