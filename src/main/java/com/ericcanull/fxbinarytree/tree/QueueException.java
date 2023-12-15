package com.ericcanull.fxbinarytree.tree;

/**
 * Defines an exception (thrown when a queue exception occurs).
 */
public final class QueueException extends RuntimeException {

	/**
	 * Constructs an object with a specific message
	 * @param message A string literal specifying the details of this exception
	 */
	public QueueException(String message) {
		super(message);
	}
}
