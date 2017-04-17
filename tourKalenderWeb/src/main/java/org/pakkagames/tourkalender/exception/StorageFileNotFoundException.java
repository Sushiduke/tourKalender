package org.pakkagames.tourkalender.exception;

/**
 * 
 * @author Josch
 * @since TourKalender 1.0.0
 */
public class StorageFileNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1227761523963697495L;

	public StorageFileNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Construct a {@code StorageFileNotFoundException} with the specified detail message
	 * @param msg the detail message
	 * @param cause of the exception
	 */
	public StorageFileNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
