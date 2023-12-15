package com.ericcanull.fxbinarytree.tree;

/**
 * Defines an exception that is thrown when tree exception occurs.
 */
@SuppressWarnings("serial")
public final class TreeException extends RuntimeException {
	
	/**
	 * Constructs an object with specific message
	 * @param message A string literal specifying the details of this exception
	 */
	public TreeException(String message) {
		super(message);
	}
}
