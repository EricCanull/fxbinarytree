package tree;

import shape.Circle;

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
	 * @param leftCircle a left tree circle
	 * @param rightCircle a right tree circle
	 */
	public TreeNode(Circle rootCircle, TreeNode leftCircle, TreeNode rightCircle) {
		this.rootCircle = rootCircle;
		this.leftCircle = null;
		this.rightCircle = null;
	}

}
