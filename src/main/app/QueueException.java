/**
 * Defines an exception that is thrown when a queue exception occurs.
 */
@SuppressWarnings("serial")
public final class QueueException extends RuntimeException {

	/**
	 * Constructs an object with specific message
	 * @param message A string literal specifying the details of this exception
	 */
	public QueueException(String message) {
		super(message);
	}
}
