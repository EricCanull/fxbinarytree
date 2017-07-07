package shape;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Creates a circle object with methods for controlling it's point locale,
 * background color, border color, font color, and number id.
 * @author Eric Canull
 * @version 1.0
 */
public final class Circle {
	
	/**
	 * The font for the numbers inside the circle.
	 */
	final Font font =  Font.font("Cooper Black", FontWeight.LIGHT, 16);
	final FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(font);
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
	 * Creates a circle object with methods for controlling it's point locale,
	 * background color, border color, font color, search key.
	 * @param searchKey a <code>Integer</code> search key for searching and deleting within an index.
	 */
	public Circle(Integer searchKey) {
		this.searchKey = searchKey;
		this.backgroundColor = Color.web("#FCFCFC");
	}
	
	/**
	 * 
	 * @param searchKey a integer id number for searching and deleting from an index.
	 * @param point a Cartesian coordinate using x and y float numbers.
	 */
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
		gc.setStroke(getFontColor());
		gc.setFont(font);
		gc.strokeText(getKey(),
				 point.getX() - (fm.computeStringWidth(getKey()) / 2),
				 point.getY() + (fm.getAscent() / 4));
	}

	private String getKey() {
		return Integer.toString(getSearchKey());
	}
	/**
	 * Get the search key number.
	 * @return A integer of the circle index value. 
	 */
	public Integer getSearchKey() {
		return this.searchKey;
	}
	
	/**
	 * Gets the border color.
	 * @return A color for the circle border
	 */
	public Color getBorderColor() {
		return borderColor;
	}
	
	/**
	 * Sets the border color.
	 * @param borderColor
	 */
	private void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	/**
	 * Gets the point coordinates.
	 * @return 
	 */
	public Point2D getPoint() {
		return point;
	}
	
	/**
	 * Sets the point coordinates.
	 * @param point
	 */
	public void setPoint(Point2D point) {
		this.point = point;
	}
	
	/**
	 * Gets the background color.
	 * @return
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	/**
	 * Sets the background color.
	 * @param color 
	 */
	private void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}
	
	/**
	 * Gets the circle radius.
	 * @return
	 */
	public int getRadius() {
		return RADIUS;
	}
	
	/**
	 * Gets the font color.
	 * @return
	 */
	public Color getFontColor() {
		return this.fontColor;
	}
	
	/**
	 * Sets the font color.
	 * @param fontColor
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
		if (highlight == true) {
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
