package org.pakkagames.tourkalender.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.pakkagames.tourkalender.exception.StorageFileNotFoundException;
import org.pakkagames.tourkalender.gpx.domain.GPX;
import org.pakkagames.tourkalender.gpx.domain.Link;
import org.pakkagames.tourkalender.gpx.domain.Track;
import org.pakkagames.tourkalender.gpx.service.GPXParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

	final static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	GPXParserService GPXParserService;

	@PostMapping(value = "/uploadFiles")
	public String handleFileUploadJqueryFiler(@RequestParam("files") ArrayList<MultipartFile> file, RedirectAttributes redirectAttributes) {

		LOGGER.info("file is null?: {}", (file != null));
		LOGGER.info("file.get(0).getOriginalFilename: {}", file.get(0).getOriginalFilename());
		LOGGER.info("file.size(): {}", file.size());
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.get(0).getOriginalFilename() + "!");

		GPX gpx = new GPX();
		try {
			gpx = GPXParserService.parseGPXTour(file.get(0).getInputStream());
		}
		catch (IOException e) {
			LOGGER.info("IOException on file {}", file.get(0).getOriginalFilename());
			e.printStackTrace();
		}
		catch (Exception e) {
			LOGGER.info("Exception on file {}", file.get(0).getOriginalFilename());
			e.printStackTrace();
		}

		LOGGER.info("gpx.getVersion():  {}", gpx.getVersion());
		LOGGER.info("gpx.getTracks().size():  {}", gpx.getTracks().size());

		Iterator<Track> tracks = gpx.getTracks().iterator();
		while (tracks.hasNext()) {
			Track track = tracks.next();
			LOGGER.info("track.getName():  {}", track.getName());
		}

		LOGGER.info("gpx.getMetadata().toString():  {}", gpx.getMetadata().toString());

		Iterator<Link> links = gpx.getMetadata().getLinks().iterator();
		while (links.hasNext()) {
			Link link = links.next();
			LOGGER.info("link.getHref():  {}", link.toString());
		}


		return "redirect:/";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
