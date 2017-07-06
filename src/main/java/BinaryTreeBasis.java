/**
 * An abstract base class for a binary tree.
 * @author Eric Canull
 */
public abstract class BinaryTreeBasis {

	/**
	 * Inherited by the concrete BST class.
	 */
	protected TreeNode root;
	
	/**
	 * An abstract base class for the BST.
	 */
	public BinaryTreeBasis() {
		root = null;
	}
	
	/**
	 * An abstract base class for the BST.
	 * @param rootCircle
	 * @Overload Default constructor
	 */
	public BinaryTreeBasis(Circle rootCircle) {
		root = new TreeNode(rootCircle, null, null);
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
	 * @return A Circle object representing the tree root
	 * @throws TreeException
	 */
	public TreeNode getRoot() throws TreeException {
		if (root == null) {
			throw new TreeException("TreeException: Empty Tree");
		}
		
		return root;
	}
	
	/**
	 * Sets the root item. 
	 * @param newCircle a Circle object with a tree search key
	 */
	public abstract void setRootItem(Circle newCircle);

}