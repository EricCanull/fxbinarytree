package com.ericcanull.fxbinarytree.shape;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Creates a line object with methods for controlling it's point locale,
 * color, and size.
 * @author Eric Canull
 * @version 1.0
 */
public final class Line {
	
	/**
	 * Default color is the panel foreground.
	 */
	private static final Color UI_DEFAULT_COLOR = Color.rgb(99, 99, 99);
	
	/**
	 * Highlight color for searching and inserting.
	 */
	private static final Color HIGHLIGHT_COLOR = Color.rgb(49, 116, 222);
	
	// The circle attributes
	private Point2D point, point2;
	private Color color;
	
	/**
	 * Creates a line object with methods for controlling it's point locale,
	 * background color, border color, font color, and number id.
	 */
	public Line() {
		this.color = UI_DEFAULT_COLOR;
	}
	
	/**
	 * 
	 * @param point a Cartesian coordinate using x and y float numbers.
	 */
	public Line(Point2D point, Point2D point2) {
		this.point = point;
		this.point2 = point;
		this.color = UI_DEFAULT_COLOR;
	}

	/**
	 * Draws the line at a specified position
	 * @param gc The graphics object to use for drawing to a component
	 */
	public void draw(GraphicsContext gc) {
	//	g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gc.setLineWidth(4); // Sets the width of the lines

		// Fill the line
		gc.setStroke(color);
		gc.strokeLine(point.getX(), point.getY(), point2.getX(), point2.getY());
	}
	
	/**
	 * Gets the point coordinates.
	 * @return A point 
	 */
	public Point2D getPoint() {
		return point;
	}
	
	/**
	 * Sets the point coordinates.
	 * @param point A Cartesian coordinate
	 */
	public void setPoint(Point2D point, Point2D point2) {
		this.point = point;
		this.point2 = point2;
	}
	
	/**
	 * Gets the point coordinates.
	 * @return A Cartesian coordinate
	 */
	public Point2D getPoint2() {
		return point2;
	}
	
	/**
	 * Sets the line to use highlighted colors if the parameter 
	 * is true.
	 * @param highlight a boolean value for switching on/off highlighted colors
	 */
	public void setHighlighter(boolean highlight) {
		if (highlight) {
			this.color = HIGHLIGHT_COLOR;
		} else {
			this.color = UI_DEFAULT_COLOR;
		}
	}

	/**
	 * Overrides the default toString method and gets the String representation
	 * of a line.
	 * @return A String representation of the line
	 */
	@Override
	public String toString() {
		
		return " (x,y) = ("  + point.getX() + ", " + point.getY() + ")"
			+  " (x,y) = ("  + point2.getX() + ", " + point2.getY()+ ")";
	}
}
