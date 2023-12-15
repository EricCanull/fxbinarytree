package com.ericcanull.fxbinarytree.tree;

import com.ericcanull.fxbinarytree.shape.Circle;

/**
 * A binary tree using circle objects.
 * @author Eric Canull
 * @version 1.0
 */
public class TreeNode {
	
	public Circle rootCircle;
	public TreeNode leftCircle;
	public TreeNode rightCircle;
	public boolean highlightFlag;
	
	/**
	 * A binary tree using circle objects.
	 * @param rootCircle a root tree circle
	 */
	public TreeNode( Circle rootCircle ) {
		this.rootCircle = rootCircle;
		this.leftCircle = null;
		this.rightCircle = null;
	}

}
