package org.pakkagames.tourkalender.gpx.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.pakkagames.tourkalender.gpx.domain.Fix;
import org.pakkagames.tourkalender.gpx.domain.GPX;
import org.pakkagames.tourkalender.gpx.domain.Metadata;
import org.pakkagames.tourkalender.gpx.domain.Route;
import org.pakkagames.tourkalender.gpx.domain.Track;
import org.pakkagames.tourkalender.gpx.domain.WayPoint;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Implementation of the GPXParserService.
 * 
 * @author jog
 * @since TourKalender 1.0.0
 */
@Service
public class GPXParserServiceImpl implements GPXParserService {

	@Autowired
	MetadataParserService metadataService;

	@Autowired
	WayPointParserService waypointParser;

	@Autowired
	RouteParserService routeService;

	@Autowired
	TrackParserService trackService;

	protected final SimpleDateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private ArrayList<ExtensionParserService> extensionParsers = new ArrayList<ExtensionParserService>();

	/**
	 * Parses a stream containing GPX data
	 *
	 * @param input the input stream
	 * @return {@link GPX} object containing parsed data, or null if no gpx data was found in the seream
	 * @throws Exception
	 */
	public GPX parseGPXTour(InputStream input) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(input);
		Node firstChild = doc.getFirstChild();
		if (firstChild != null && GPXConstant.NODE_GPX.equals(firstChild.getNodeName())) {
			GPX gpx = new GPX();

			NamedNodeMap attrs = firstChild.getAttributes();

			for (int idx = 0; idx < attrs.getLength(); idx++) {
				Node attr = attrs.item(idx);
				if (GPXConstant.ATTR_VERSION.equals(attr.getNodeName())) {
					gpx.setVersion(attr.getNodeValue());
				}
				else if (GPXConstant.ATTR_CREATOR.equals(attr.getNodeName())) {
					gpx.setCreator(attr.getNodeValue());
				}
			}

			NodeList nodes = firstChild.getChildNodes();
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node currentNode = nodes.item(idx);
				if (GPXConstant.NODE_EXTENSIONS.equals(currentNode.getNodeName())) {
					for (ExtensionParserService parser : this.getExtensionParsers()) {
						Object data = parser.parseExtensions(currentNode);
						gpx.addExtensionData(parser.getId(), data);
					}
				}
				else if (GPXConstant.NODE_METADATA.equals(currentNode.getNodeName())) {
					Metadata metadata = metadataService.parseMetadata(currentNode);
					if (metadata != null) {
						gpx.setMetadata(metadata);
					}
				}
				else if (GPXConstant.NODE_RTE.equals(currentNode.getNodeName())) {
					Route route = routeService.parseRoute(currentNode);
					if (route != null) {
						gpx.addRoute(route);
					}
				}
				else if (GPXConstant.NODE_TRK.equals(currentNode.getNodeName())) {
					Track track = trackService.parseTrack(currentNode);
					if (track != null) {
						gpx.addTrack(track);
					}
				}
				else if (GPXConstant.NODE_WPT.equals(currentNode.getNodeName())) {
					WayPoint waypoint = waypointParser.parseWaypoint(currentNode);
					if (waypoint != null) {
						gpx.addWaypoint(waypoint);
					}
				}
			}
			return gpx;
		}
		else {
			throw new IllegalAccessException("File does not contain tag <gpx>. The file is not in valid GPX format.");
		}
	}

	public Date getNodeValueAsDate(Node node) throws DOMException, ParseException {
		Date val = null;
		try {
			val = xmlDateFormat.parse(node.getFirstChild().getNodeValue().replaceAll("([0-9\\-T]+:[0-9]{2}:[0-9.+]+):([0-9]{2})", "$1$2"));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public Double getNodeValueAsDouble(Node node) {
		return Double.parseDouble(node.getFirstChild().getNodeValue());
	}

	public Fix getNodeValueAsFixType(Node node) {
		return Fix.returnType(node.getFirstChild().getNodeValue());
	}

	public Integer getNodeValueAsInteger(Node node) {
		return Integer.parseInt(node.getFirstChild().getNodeValue());
	}

	public String getNodeValueAsString(Node node) {
		if (node == null) {
			return null;
		}

		Node child = node.getFirstChild();
		if (child == null) {
			return null;
		}
		return child.getNodeValue();
	}

	/**
	 * Adds a new extension parser to be used when parsing a gpx steam
	 *
	 * @param parser an instance of a {@link ExtensionParserService} implementation
	 */
	public void addExtensionParser(ExtensionParserService parser) {
		this.getExtensionParsers().add(parser);
	}

	/**
	 * Removes an extension parser previously added
	 *
	 * @param parser an instance of a {@link ExtensionParserService} implementation
	 */
	public void removeExtensionParser(ExtensionParserService parser) {
		this.getExtensionParsers().remove(parser);
	}

	public ArrayList<ExtensionParserService> getExtensionParsers() {
		return extensionParsers;
	}

	public void setExtensionParsers(ArrayList<ExtensionParserService> extensionParsers) {
		this.extensionParsers = extensionParsers;
	}
}
