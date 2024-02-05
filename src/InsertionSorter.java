package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;	
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author	Autrin Hakimi
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "insertion sort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		int i = 0; //denotes the starting position of the current element in the unsorted part
		int j = 0; //keeps track of the index of the current element being inserted into the sorted part
		for (i = 1; i < points.length; ++i) {
			j = i;
			// Insert points[i] into sorted part
			// stopping once points[i] in correct position
			while (j > 0 && points[j].compareTo(points[j - 1]) == -1) {	         
				// Swap numbers[j] and numbers[j - 1]
				swap(j, j-1);
				--j;
			}
		}
	}		
}
