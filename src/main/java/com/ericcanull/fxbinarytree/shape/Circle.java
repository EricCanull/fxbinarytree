package com.ericcanull.fxbinarytree.shape;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * Creates a circle object with methods for controlling its point locale,
 * background color, border color, font color, and number id.
 * @author Eric Canull
 * @version 1.0
 */
public final class Circle {
	
	/**
	 * The font for the numbers inside the circle.
	 */
	final Font font =  Font.font("Cooper Black", FontWeight.BOLD, 16);

	/**
	 * The radius of the circle.
	 */
	public static final int RADIUS = 26;
	
	/**
	 * The search key for searching and deleting circles.
	 */
	private final Integer searchKey;
	
	// The circle attributes
	private Point2D point;
	private Color backgroundColor;
	private Color borderColor;
	private Color fontColor;

	/**
	 * Creates a circle object with methods for controlling its point locale,
	 * background color, border color, font color, search key.
	 * @param searchKey a <code>Integer</code> search key for searching and deleting within an index.
	 */
	public Circle(Integer searchKey) {
		this.searchKey = searchKey;
		this.backgroundColor = Color.web("#FCFCFC");
	}
	
	/**
	 * 
	 * @param searchKey an integer id number for searching and deleting from an index.
	 * @param point a Cartesian coordinate using x and y float numbers.
	 */
	@SuppressWarnings("unused")
	public Circle(Integer searchKey, Point2D point) {
		this.searchKey = searchKey;
		this.point = point;
		this.backgroundColor = Color.rgb(49, 116, 222);
		this.setBorderColor(Color.rgb(99, 99, 99));
		this.fontColor = Color.web("#FCFCFC");
		
	}

	/**
	 * Draws the circle at a new position
	 * @param gc The graphics object to use for drawing to a component
	 */
	public void draw(GraphicsContext gc) {
		//(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gc.setLineWidth(3); // Sets the width of the lines

		// Create a circle
		gc.setFill(backgroundColor);
		gc.fillOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);

		// Outline the circle border
		gc.setStroke(borderColor);
		gc.strokeOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);

		// Draw the id number inside the circle
		gc.setFont(font);
		gc.setFill(getFontColor());

		Text text = new Text(getKey());
		text.setFont(font);
		double width = text.getLayoutBounds().getWidth();

		gc.fillText(getKey(),
					point.getX() - (width / 2),
					point.getY() + (font.getSize() / 4)); // approximate centering for the height
	}

	private String getKey() {
		return Integer.toString(getSearchKey());
	}
	/**
	 * Get the search key number.
	 * @return An integer of the circle index value.
	 */
	public Integer getSearchKey() {
		return this.searchKey;
	}

	/**
	 * Set the border color of the Circle.
	 *
	 * @param borderColor The color to set as the border color.
	 */
	private void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * Sets the point coordinates.
	 * @param point A Cartesian coordinate
	 */
	public void setPoint(Point2D point) {
		this.point = point;
	}

	/**
	 * Sets the background color.
	 * @param color  A color object
	 */
	private void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}

	/**
	 * Gets the font color.
	 * @return A color object
	 */
	public Color getFontColor() {
		return this.fontColor;
	}
	
	/**
	 * Sets the font color.
	 * @param fontColor A color object
	 */
	private void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	
	/**
	 * Sets the circle to use highlighted colors if the parameter 
	 * is true.
	 * @param highlight a boolean value for switching on/off highlighted colors
	 */
	public void setHighlighter(boolean highlight) {
		if (highlight) {
			setFontColor(Color.rgb(49, 116, 222));
			setBackgroundColor(Color.rgb(155, 244, 167));
			setBorderColor(Color.rgb(49, 116, 222));
	
		} else {
			setFontColor(Color.web("#FCFCFC"));
			setBackgroundColor(Color.rgb(49, 116, 222));
			setBorderColor(Color.rgb(99, 99, 99));
		}
	}

	/**
	 * Overrides the default toString method and gets the String representation
	 * of a circle.
	 * @return A String representation of the circle object.
	 */
	@Override
	public String toString() {
		
		return "Search Key# " + searchKey  + 
				" (x,y) = ("  + point.getX() + ", " + point.getY() + ")";
	}
}
