package com.ericcanull.fxbinarytree.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Iterates through the tree using the java.util.Iterator<E> interface.
 * @author Eric Canull
 * @version 1.0
 */
public final class TreeIterator implements Iterator<Integer> {
	
	private BinaryTreeBasis binaryTree;
	private TreeNode currentNode;
	private LinkedList<TreeNode> queue;
	
	/**
	 * Iterates through the tree using the java.util.Iterator<E> interface.
	 * @param binaryTree the abstract binary tree class
	 */
	public TreeIterator(BinaryTreeBasis binaryTree) {
		this.binaryTree = binaryTree;
		currentNode = null;
		queue = new LinkedList<>();
	}
	
	/**
	 * Determines if there are elements in the queue. 
	 * @Return true if the iteration has more elements
	 */
	public boolean hasNext() {
		return !queue.isEmpty();
	}
	
	/**
	 * Gets the next element in the queue.
	 * @return An <code>Integer</code> numbered search key.
	 */
	public Integer next() throws NoSuchElementException {
		try {
			currentNode = queue.remove();
			return currentNode.rootCircle.getSearchKey();
		} catch (QueueException e) {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Unsupported remove operation. Throws an exception when invoked.
	 */
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Sets the tree traversal to in-order
	 */
	public void setPreorder() {
		queue.clear();
		preorder(binaryTree.root);
	}
	
	/**
	 * Recursively traverses the tree in-order
	 * @param treeNode A tree with nodes
	 */
	private void preorder(TreeNode treeNode) {
		if (treeNode != null) {
			queue.add(treeNode);
			preorder(treeNode.leftCircle);
			preorder(treeNode.rightCircle);
		}
	}
	
	/**
	 * Sets the tree traversal to in-order
	 */
	public void setInorder() {
		queue.clear();
		inorder(binaryTree.root);
	}
	
	/**
	 * Recursively traverses the tree in-order
	 * @param treeNode A tree with nodes
	 */
	private void inorder(TreeNode treeNode) {
		if (treeNode != null) {
			inorder(treeNode.leftCircle);
			queue.add(treeNode);
			inorder(treeNode.rightCircle);
		}
	}
	
	/**
	 * Sets the tree to traverse in post-order
	 */
	public void setPostorder() {
		queue.clear();
		postorder(binaryTree.root);
	}
	
	/**
	 * Recursively traverses the tree post-order
	 * @param treeNode A tree with nodes
	 */
	private void postorder(TreeNode treeNode) {
		if (treeNode != null) {
			postorder(treeNode.leftCircle);
			postorder(treeNode.rightCircle);
			queue.add(treeNode);
		}
	}
}