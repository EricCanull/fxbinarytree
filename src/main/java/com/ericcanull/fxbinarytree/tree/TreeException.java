package com.ericcanull.fxbinarytree.tree;

/**
 * Defines an exception (thrown when a tree exception occurs).
 */
public final class TreeException extends RuntimeException {
	
	/**
	 * Constructs an object with a specific message
	 * @param message A string literal specifying the details of this exception
	 */
	public TreeException(String message) {
		super(message);
	}
}
