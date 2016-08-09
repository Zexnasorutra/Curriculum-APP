package work.arturo.curriculum.exception;

/**
 * Class for manage the application exceptions
 * 
 * @author Arturo
 *
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for message + exception
	 * 
	 * @param message
	 *            Error description
	 * @param exception
	 *            Error object
	 */
	public ApplicationException(String message, Throwable exception) {
		super(message, exception);
	}

	/**
	 * Constructor for only message
	 * 
	 * @param message
	 *            Error description
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * Constructor for only exception
	 * 
	 * @param exception
	 *            Error object
	 */
	public ApplicationException(Throwable exception) {
		super(exception);
	}
}
