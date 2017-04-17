package org.pakkagames.tourkalender.gpx.service;

import org.pakkagames.tourkalender.gpx.domain.Email;
import org.pakkagames.tourkalender.shared.GPXConstant;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Service
public class EmailParserServiceImpl implements EmailParserService {

	public Email parseEmail(Node node) {
		if (node == null) {
			return null;
		}

		Email email = new Email(null, null);

		NamedNodeMap attrs = node.getAttributes();
		for (int idx = 0; idx < attrs.getLength(); idx++) {
			Node attr = attrs.item(idx);
			if (GPXConstant.ATTR_ID.equals(attr.getNodeName())) {
				email.setId(attr.getNodeValue());
			}
			else if (GPXConstant.ATTR_DOMAIN.equals(attr.getNodeName())) {
				email.setDomain(attr.getNodeValue());
			}
		}

		return email;
	}
}
