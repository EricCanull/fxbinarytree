package tree;

import java.util.Objects;
import shape.Circle;

/**
 * Binary search tree. 
 * Inherits isEmpty(), makeEmpty(), getRootItem(), and the
 * use of the constructors from tree.BinaryTreeBasis Assumption: A tree contains at
 * most one item with a given search key at any time.
 * 
 * @author Eric Canull
 * @version 1.0
 */
public final class BinarySearchTree extends BinaryTreeBasis {

	/**
	 * Binary search tree.
	 */
	public BinarySearchTree() {
		this.root = null;
	}

	/**
	 * Binary search tree.
	 * @param rootCircle
	 * @Overload Default constructor
	 */
	public BinarySearchTree(Circle rootCircle) {
		super(rootCircle);
	}

	/**
	 * Inserts a new circle into the tree.
	 * @param newCircle
	 */
	public void insertItem(Circle newCircle) {
		root = insertItem(root, newCircle);
	}
	
	/**
	 * Inserts a new circle into the tree.
	 * @param tNode a tree node
	 * @param newCircle a new circle
	 * @return 
	 * @Overload insertItem() 
	 */
	protected TreeNode insertItem(TreeNode tNode, Circle newCircle) {
		TreeNode newSubtree;
		
		if (tNode == null) {
			tNode = new TreeNode(newCircle, null, null);
			return tNode;
		}
		
		Circle nodeItem = tNode.rootCircle;

		if(Objects.equals(newCircle.getSearchKey(), nodeItem.getSearchKey())) {
		    return tNode;
        }

		if (newCircle.getSearchKey() < nodeItem.getSearchKey()) {
			newSubtree = insertItem(tNode.leftCircle, newCircle);
			tNode.leftCircle = newSubtree;
			return tNode;
		}
		
		newSubtree = insertItem(tNode.rightCircle, newCircle);
		tNode.rightCircle = newSubtree;
		return tNode;
	}
	
	/**
	 * Retrieves a circle from the tree.
	 * @param searchKey a unique identifying value
	 * @return An integer search key number
	 */
	public Integer retrieveItem(Integer searchKey) {
		return retrieveItem(root, searchKey);
	}
	
	
	/**
	 * Searches for a circle from the tree.
	 * @param tNode a tree node 
	 * @param searchKey a unique identifying value
	 * @return An integer search key number
	 * @Overload retrieveItem()
	 */
	protected Integer retrieveItem(TreeNode tNode, Integer searchKey) {
		Integer treeItem;
		if (tNode == null) {
			treeItem = null;

		} else {
			tNode.highlightFlag = true;
			Circle nodeItem = tNode.rootCircle;
			if (Objects.equals(searchKey, nodeItem.getSearchKey())) {
				tNode.highlightFlag = true;
				treeItem = tNode.rootCircle.getSearchKey();
			} else if (searchKey < nodeItem.getSearchKey()) {
				tNode.leftCircle.highlightFlag = true;
				treeItem = retrieveItem(tNode.leftCircle, searchKey);
			} else {
				tNode.rightCircle.highlightFlag = true;
				treeItem = retrieveItem(tNode.rightCircle, searchKey);
			}
		}

		return treeItem;
	}
	
	/**
	 * Deletes a circle from the tree.
	 * @param searchKey a unique identifying value
	 * @throws TreeException if search key cannot be located.
	 */
	public void deleteItem(Integer searchKey) throws TreeException {
		root = deleteItem(root, searchKey);
	}
	
	/**
	 * Deletes a circle from the tree.
	 * @param tNode a tree node 
	 * @param searchKey a unique identifying value
	 * @return A tree.TreeNode from within the tree
	 * @Overload deleteItem()
	 */
	protected TreeNode deleteItem(TreeNode tNode, Integer searchKey) {
		TreeNode newSubtree;
		
		if (tNode == null) {
			throw new TreeException("tree.TreeException: Item not found");
		}
		
		Circle nodeItem = tNode.rootCircle;
		if (Objects.equals(searchKey, nodeItem.getSearchKey())) {
			tNode = deleteNode(tNode);

		} else if (searchKey < nodeItem.getSearchKey()) {
			newSubtree = deleteItem(tNode.leftCircle, searchKey);
			tNode.leftCircle = newSubtree;
		}

		else {
			newSubtree = deleteItem(tNode.rightCircle, searchKey);
			tNode.rightCircle = newSubtree;
		}

		return tNode;
	}
	
	/**
	 * Helper method finds and replaces a deleted node. 
	 * @param tNode A tree.TreeNode from within the tree
	 * @return A tree.TreeNode from within the tree
	 */
	protected TreeNode deleteNode(TreeNode tNode) {

		Circle replacementItem;

		if ((tNode.leftCircle == null) && (tNode.rightCircle == null)) {
			return null;
		}

		else if (tNode.leftCircle == null) {
			return tNode.rightCircle;
		}

		else if (tNode.rightCircle == null) {
			return tNode.leftCircle;
		} else {

			replacementItem = findLeftmost(tNode.rightCircle);
			tNode.rootCircle = replacementItem;
			tNode.rightCircle = deleteLeftmost(tNode.rightCircle);
			return tNode;
		}
	}

	/**
	 * Helper method for searching and deleting left-side nodes.
	 * @param tNode
	 * @return
	 */
	protected Circle findLeftmost(TreeNode tNode) {
		if (tNode.leftCircle == null) {
			return tNode.rootCircle;
		}
		return findLeftmost(tNode.leftCircle);
	}
	
	/**
	 * Helper method for searching and deleting right-side nodes.
	 * @param tNode
	 * @return
	 */
	protected TreeNode deleteLeftmost(TreeNode tNode) {
		if (tNode.leftCircle == null) {
			return tNode.rightCircle;
		}
		tNode.leftCircle = deleteLeftmost(tNode.leftCircle);
		return tNode;
	}
	
	/**
	 * Resets the color to the default.
	 * @param tNode
	 */
	public void setResetColor(TreeNode tNode) {
		 resetColor(tNode);
	}
	
	
	/**
	 * Resets the color to the default.
	 * @param tNode A node in the tree
	 * @Overload
	 */
	protected void resetColor(TreeNode tNode) {
		if (tNode != null) {
			tNode.highlightFlag = false;

			if (tNode.leftCircle != null) {
				tNode.leftCircle.highlightFlag = false;
			}

			if (tNode.rightCircle != null) {
				tNode.rightCircle.highlightFlag = false;
			}
			resetColor(tNode.leftCircle);
			resetColor(tNode.rightCircle);
		}
	}

	/**
	 * Gets the height of the tree
	 * @param root
	 * @return
	 */
	public int getHeight(TreeNode root) {
		if (root == null)
			return 0;
		return Math.max(getHeight(root.leftCircle), getHeight(root.rightCircle)) + 1;
	}
	
	/**
	 * Gets the size of the tree
	 * @param root
	 * @return
	 */
	public int getSize(TreeNode root) {
		if (root == null)
			return 0;
		return (getSize(root.leftCircle) + getSize(root.rightCircle)) + 1;
	}
	
	@Override
	public void setRootItem(Circle newItem) {
		root = new TreeNode(newItem, null, null);
	}
}
