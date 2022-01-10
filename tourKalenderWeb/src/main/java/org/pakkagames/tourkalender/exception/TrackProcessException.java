package org.pakkagames.tourkalender.exception;

/**
 * @since TourKalender 1.0.0
 */
public class TrackProcessException extends RuntimeException {

    public TrackProcessException(String message) {
        super(message);
    }

    /**
     * Construct a {@code StorageFileNotFoundException} with the specified detail message
     *
     * @param message the detail message
     * @param cause   of the exception
     */
    public TrackProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
