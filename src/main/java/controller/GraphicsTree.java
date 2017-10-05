package controller;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import tree.*;
import shape.*;
import java.util.Objects;

/**
 * Draws the tree and updates the graphics to display according to the
 * searching, inserting, deleting, and traversal options.
 * @author Eric Canull
 * @version 1.0
 */
@SuppressWarnings("serial")
public final class GraphicsTree extends Canvas {

	/**
	 * The initial input values for the tree.
	 */
	private static final Integer[] NUMBERS_ARRAY = { 50, 25, 30, 12, 10, 75, 70, 80, 110 };

	private BinarySearchTree tree;  	// The BST
	private TreeIterator treeIterator;  // The BST Iterator
	private Circle insertCircle;        // Insert circle 
	private int maxTreeHeight; 			// Max tree height;

	/**
	 * Draws the tree and updates the graphics to display according to the
	 * searching, inserting, deleting, and traversal options.
	 */
	public GraphicsTree() {

		widthProperty().addListener(evt -> drawTree());
		heightProperty().addListener(evt -> drawTree());

		createTree();
	}
	
	/**
     * Changes the tree rendered by this panel.
     */
    public void setTree(BinarySearchTree root) {  tree = root; }

	/**
	 * Creates the initial binary search tree with the default values
	 * in the numbers array.
	 */
	public void createTree() {

		tree = new BinarySearchTree(); // Create an empty tree
		setMaxTreeHeight(7); 		   // Set the default max tree height 

		for (Integer number : NUMBERS_ARRAY) {
			Circle circle = new Circle(number);
			tree.insertItem(circle);
		}

		drawTree();
	}

	/**
	 * Set the max tree height.
	 * @param size a <code>Integer</code> number for the tree max size
	 * @return An <code>Integer</code> max tree size
	 */
	private int setMaxTreeHeight(int size) {
		this.maxTreeHeight = size;
		return size;
	}

	/**
	 * Searches for an search key number in the tree. If the number is found the tree will
	 * be repainted to show the path. If the number cannot be found a notification
	 * message will be displayed.
	 * 
	 * @param searchKey a <code>Integer</code> number for finding a tree.TreeNode
	 */
	public void search(Integer searchKey) {

		// Try to search for a number.
		try { 
			tree.retrieveItem(searchKey); // number was found
		} catch (NullPointerException e) { // Not found
			//JOptionPane.showMessageDialog(null, searchKey + " was not found.");
			tree.setResetColor(tree.root); // Reset color
		}

		drawTree();
	}

	/**
	 * Prints the tree traversal order to the upper left-hand
	 * side of the screen.
	 * @return outputString
	 */
	public String printTree() {

		// Traversal text output string
		StringBuilder outputString = new StringBuilder();

		// Add the next tree iterator to the output
		while (treeIterator.hasNext()) {
			outputString.append(treeIterator.next()).append(" ");
		}

		return outputString.toString(); // return the output string
	}

	/**
	 * Retrieves the pre-order traversal option.
	 */
	public void setPreorder() {
		treeIterator = new TreeIterator(tree);
		treeIterator.setPreorder();
	}

	/**
	 * Retrieves the in-order traversal option.
	 */
	public void setInorder() {
		treeIterator = new TreeIterator(tree);
		treeIterator.setInorder();
	}

	/**
	 * Retrieves the post-order traversal option.
	 */
	public void setPostorder() {
		treeIterator = new TreeIterator(tree);
		treeIterator.setPostorder();
	}

	/**
	 * Inserts a circle into the tree. If the tree height reaches the max height
	 * displays an notification request to change the max height.
	 * @param searchKey a <code>integer</code> number to insert in the tree
	 */
	public void insert(Integer searchKey) {
		insertCircle = new Circle(searchKey);
		tree.insertItem(insertCircle);
		int heightOption = 2;
		drawTree();


	//	 If the height of the tree equals max height
		if (tree.getHeight(tree.getRoot()) == maxTreeHeight) {
			// Display option message

			Alert alert = new Alert(Alert.AlertType.WARNING, "Reached max height. Would you like to increase the height?",
					ButtonType.YES);
			alert.showAndWait()
					.filter(response -> response == ButtonType.YES)
					.ifPresent(response -> alert.close());
		}
//			heightOption = JOptionPane.showConfirmDialog(null,
//					"Reached max height. Would you like to increase the height?", " Height Adjust",
//					JOptionPane.YES_NO_OPTION);

			// Option 'Yes'selected: Change the height
			if (heightOption == 0) {
//				String height = JOptionPane.showInputDialog("Enter a new height between 7 and 10.");
				heightOption = 7;
				try {
					int newHeight = heightOption;
//					int newHeight = Integer.parseInt(heightOption);
					// Confirm height input is within acceptable range
					if (newHeight > tree.getHeight(tree.getRoot()) && newHeight <= 10) {
						setMaxTreeHeight(newHeight);
					} else {
						throw new TreeException("tree.TreeException on change height.");
					}

					// Error occurred: Reverse changes and exit
				} catch (NumberFormatException | TreeException e) {
					Alert alert = new Alert(Alert.AlertType.WARNING, "Input error. The height was not changed.",
							ButtonType.OK);
					alert.showAndWait()
							.filter(response -> response == ButtonType.OK)
							.ifPresent(response -> alert.close());

					tree.deleteItem(insertCircle.getSearchKey()); // Remove the inserted circle
					tree.setResetColor(tree.root); 				  // Reset highlight flag for all nodes
				}

				// Option 'No' selected: Reverse changes and exit
			} else if (heightOption == 1) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The number was not inserted.",
						ButtonType.OK);
				alert.showAndWait()
						.filter(response -> response == ButtonType.OK)
						.ifPresent(response -> alert.close());


				tree.deleteItem(insertCircle.getSearchKey()); // Remove the inserted circle
				tree.setResetColor(tree.root);                // Reset highlight flag for all nodes



		} else {

		}
	}

	/**
	 * Deletes a number from the tree. If the number is not able to be deleted display
	 * a notification message.
	 * @param searchKey <code>integer</code> number to delete from the tree
	 */
	public void delete(Integer searchKey) {
		try {
			tree.deleteItem(searchKey);
		} catch (TreeException e) {
			//JOptionPane.showMessageDialog(null, "Unable to delete " + searchKey);
		}

		drawTree();
	}

	/**
	 * Deletes all the nodes in the tree.
	 */
	public void makeEmpty() {
		tree.makeEmpty();
		maxTreeHeight = 6;
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Draws the binary tree on the component.
	 */
	protected void drawTree() {
		double width = getWidth();
		double height = getHeight();

		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, width, height);

		// If the tree is not empty; draw the lines and circles
		if (tree.root != null) {
		int treeHeight = tree.getHeight(tree.root);
			
			// Get the tree height
			drawTree(gc, tree.getRoot(), 0, this.getWidth(), 0, this.getHeight() / treeHeight);
			drawCircles(gc, tree.getRoot(), 0, this.getWidth(), 0, this.getHeight() / treeHeight);
		}
	}
	
	/**
	 * Draws the lines recursively until there are no more tree nodes.
	 * @param gc graphics2D class for extending drawing tools
	 * @param treeNode a tree with <code>integer</code> index numbers
	 * @param xMin the minimum width to draw on the component
	 * @param xMax the minimum width to draw on the component
	 * @param yMin the maximum width to draw on the component
	 * @param yMax the maximum height to draw on the component
	 */
	protected void drawTree(GraphicsContext gc, TreeNode treeNode, double xMin, double xMax, double yMin, double yMax) {

		Point2D linePoint1; 	// Point_1
		Point2D linePoint2;   // Point_2
		Line newLine = new Line();  // Blank line
		
		// If left node is not null then draw a line to it
		if (treeNode.leftCircle != null) {
			newLine.setHighlighter(false);
			
			if (treeNode.leftCircle.highlightFlag) {
				newLine.setHighlighter(true);
			}
			
			// Determine the start and end points of the line
			linePoint1 = new Point2D(((xMin + xMax) / 2), yMin + yMax / 2);
			linePoint2 = new Point2D(((xMin + (xMin + xMax) / 2) / 2), yMin + yMax + yMax / 2);
			newLine.setPoint(linePoint1, linePoint2);// Set the points
			newLine.draw(gc);// Draw the line
			
			// Recurse left circle nodes
			drawTree(gc, treeNode.leftCircle, xMin, (xMin + xMax) / 2, yMin + yMax, yMax);
		}

		// If right node is not null then draw a line to it
		if (treeNode.rightCircle != null) {
			newLine.setHighlighter(false);
			
			// Color the line if the tree circle is flagged for color 
			if (treeNode.rightCircle.highlightFlag) {
				newLine.setHighlighter(true);
			}
	
			// Determine the start and end points of the line
			linePoint1 = new Point2D((xMin + xMax) / 2, yMin + yMax / 2);
			linePoint2 = new Point2D((xMax + (xMin + xMax) / 2) / 2, yMin + yMax + yMax / 2);
			newLine.setPoint(linePoint1, linePoint2);
			newLine.draw(gc);// Draw the line
		
			// Recurse right circle nodes
			drawTree(gc, treeNode.rightCircle, (xMin + xMax) / 2, xMax, yMin + yMax, yMax);
		}
	}

	/**
	 * Draws circles for every root, parent and child tree nodes.
	 * @param gc graphics2D class for expanding the drawing tools
	 * @param treeNode a tree with <code>Integer</code> index numbers
	 * @param xMin the minimum width to draw on the component
	 * @param xMax the maximum width to draw on the component
	 * @param yMin the minimum height to draw on the component
	 * @param yMax the maximum height to draw on the component
	 */
	public void drawCircles(GraphicsContext gc, TreeNode treeNode, double xMin, double xMax, double yMin, double yMax) {

		// Create a new point
		Point2D point = new Point2D((xMin + xMax) / 2, yMin + yMax / 2);

		// treeNodes are flagged for highlight: Search and insertion nodes
		if (treeNode.highlightFlag || Objects.equals(treeNode.rootCircle, insertCircle)) {
			insertCircle = null;		    // Reset insert circle
			treeNode.highlightFlag = false; // Reset highlight flag
			treeNode.rootCircle.setHighlighter(true); // Highlight turned on
			treeNode.rootCircle.setPoint(point); 	

			// default no highlight
		} else {
			treeNode.rootCircle.setHighlighter(false); // Highlight turned off
			treeNode.rootCircle.setPoint(point);
		}

		// Draw the circle
		treeNode.rootCircle.draw(gc);

		// Recurse left circles
		if (treeNode.leftCircle != null) {
			drawCircles(gc, treeNode.leftCircle, xMin, (xMin + xMax) / 2, yMin + yMax,	yMax);
		}

		// Recurse right circles
		if (treeNode.rightCircle != null) {
			drawCircles(gc, treeNode.rightCircle, (xMin + xMax) / 2, xMax, yMin + yMax, yMax);
		}
	}

	public void clearCanvas() {
		getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
}
	

