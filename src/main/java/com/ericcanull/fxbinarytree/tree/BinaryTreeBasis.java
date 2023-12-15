package com.ericcanull.fxbinarytree.tree;

import com.ericcanull.fxbinarytree.shape.Circle;

/**
 * An abstract base class for a binary tree.
 * @author Eric Canull
 */
public abstract class BinaryTreeBasis {

	/**
	 * Inherited by the concrete BST class.
	 */
	public TreeNode root;
	
	/**
	 * An abstract base class for the BST.
	 */
	public BinaryTreeBasis() {
		root = null;
	}
	
	/**
	 * An abstract base class for the BST.
	 * @param rootCircle a shape.Circle object with a tree search key
	 */
	public BinaryTreeBasis(Circle rootCircle) {
		root = new TreeNode(rootCircle);
	}
	
	/**
	 * Checks if the tree is empty.
	 * @return <code>true</code> if the tree has items
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Makes the tree empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Gets the root item.
	 * @return A shape.Circle object representing the tree root
	 * @throws TreeException if the tree is empty
	 */
	public TreeNode getRoot() throws TreeException {
		if (root == null) {
			throw new TreeException("tree.TreeException: Empty Tree");
		}
		
		return root;
	}
}