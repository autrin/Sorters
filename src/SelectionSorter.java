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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "selection sort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		int i = 0;
		int j = 0;
		int indexSmallest = 0;	   
		for (i = 0; i < points.length - 1; ++i) {	      
			// Find index of smallest remaining element
			indexSmallest = i;
			for (j = i + 1; j < points.length; ++j) {         
				if ( points[j].compareTo(points[indexSmallest]) == -1) {
					indexSmallest = j;
				}
			}      
			// Swap numbers[i] and numbers[indexSmallest]
			swap(i, indexSmallest);
		}	
	
	}	
}
