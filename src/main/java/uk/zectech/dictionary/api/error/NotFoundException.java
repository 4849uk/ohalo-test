package uk.zectech.dictionary.api.error;

/**
 * Utility exception used to signify an entity not found case. This exception is
 * caught by controller advice and is translated into a 404 NOT_FOUND response.
 * 
 * @author Michael Conway
 *
 */
public class NotFoundException extends RuntimeException {

	/** The serial version ID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public NotFoundException() {
		// No operation
	}

}
