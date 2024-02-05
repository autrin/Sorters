package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;	
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author	Autrin Hakimi
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		// TODO 
		super(pts);
		algorithm = "quicksort";
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO 
		quickSortRec(0, points.length - 1);

	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		// TODO
		if (first >= last) {
			return;
		}
		int lowEndIndex = partition(first, last);		   
		quickSortRec(first, lowEndIndex);
		quickSortRec(lowEndIndex + 1, last);

	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		// TODO 
		int midpoint = first + (last - first) / 2;
		Point pivot = points[midpoint];
		boolean done = false;
		while (!done) {
			// Increment first while points[first] < pivot
			while (points[first].compareTo(pivot) == -1) {
				first += 1;
			}
			// Decrement last while pivot < points[last]
			while (pivot.compareTo(points[last]) == -1) {
				last -= 1;
			}
			// If zero or one elements remain, then all numbers are 
			// partitioned. Return last.
			if (first >= last) {
				done = true;
			}
			else {
				// Swap points[first] and points[last]
				swap(first, last);
				// Update first and last
				first += 1;
				last -= 1;
			}
		}
		return last;
	}		
	
	// Other private methods if needed ...
}
