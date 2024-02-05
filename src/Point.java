package edu.iastate.cs228.hw2;

/**
 *  
 * @author Autrin Hakimi
 * This class represents the points
 */

public class Point implements Comparable<Point>
{
	private int x; // the x of the point
	private int y; //  the y of the point

	public static boolean xORy;  // compare x coordinates if xORy == true and y coordinates otherwise 
	// To set its value, use Point.xORy = true or false. 
	/**
	 * default constructor
	 */
	public Point()  
	{
		// x and y get default value 0
	}
	/**
	 * constructor creating a point given x and y
	 * @param x
	 * @param y
	 */
	public Point(int x, int y)
	{
		this.x = x;  
		this.y = y;   
	}
	/**
	 * copies the constructor. ( creates a copy the point)
	 * @param p
	 */
	public Point(Point p) { // copy constructor


		assert(p!=null);  // just to be safe
		this.x = p.getX();
		this.y = p.getY();
	}
	/**
	 * returns x
	 * @return x
	 */
	public int getX()   
	{
		return x;
	}

	/**
	 * returns y
	 * @return y
	 */
	public int getY()
	{
		return y;
	}

	/** 
	 * Set the value of the static instance variable xORy. 
	 * @param xORy
	 */
	public static void setXorY(boolean xORy)
	{
		Point.xORy = xORy;

	}

	/**
	 * checks to see if the current point is equal to the given point
	 * @return true or false
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}

		Point other = (Point) obj;
		return x == other.x && y == other.y;   
	}

	/**
	 * Compare this point with a second point q depending on the value of the static variable xORy 
	 * @param 	q 
	 * @return  -1  if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y))) 
	 *                || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))
	 * 		    0   if this.x == q.x && this.y == q.y)  
	 * 			1	otherwise 
	 */
	public int compareTo(Point q)
	{
		if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y)) ||
				(xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))) {
			return -1;
		}
		else if (this.x == q.x && this.y == q.y) {
			return 0;
		}
		else {
			return 1;
		}

	}


	/**
	 * Output a point in the standard form (x, y). 
	 */
	@Override
	public String toString() 
	{
		return "(" + getX() + ", " + getY() + ")"; 
	}
}
