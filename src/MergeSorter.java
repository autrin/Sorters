package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;	
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *  
 * @author	Autrin Hakimi
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{

	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "mergesort";

	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		
		mergeSortRec(points);
	}


	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int n = pts.length; //length
		
		if(n <= 1) {
			return;
		}
		int midIndex = n/2; // the middle index
		Point[] left = new Point[midIndex];
		Point[] right = new Point[n - midIndex];
		for(int i = 0; i < midIndex; i++) {
			left[i] = new Point(pts[i]);
		} // where is midindex??
		int counter = 0;
		for(int i = midIndex; i < n; i++) {
			right[counter] = new Point(pts[i]);
			counter++;
		}
		mergeSortRec(left);
		mergeSortRec(right);
		Point[] temp = merge(left, right);
		for(int i = 0; i < pts.length; i++) {
			pts[i] = temp[i];
		}

	}


	/**
	 * merge the left and right half together and add them to D
	 * @param left
	 * @param right
	 * @return D
	 */
	private Point[] merge(Point[] left, Point[] right) {
		int p = left.length; //the size of the left half points
		int q = right.length; // the size of right half points
		Point[] D = new Point[p+q];
		int i= 0;
		int j = 0;
		int k = 0;
		while(i < p && j < q) {
			if(left[i].compareTo(right[j]) <= 0) {
				D[k] = left[i];
				i++;
				k++;
			}
			else {
				D[k] =right[j];
				j++;
				k++;
			}	
		}

		if(i >= p) {
			while(j<q) {
				D[k] = right[j];
				 j++; 
				 k++;
			}
		}
		else {
			while(i<p) {
				D[k] = left[i];
				 i++; 
				 k++;
			}
		}

		return D;
	}

}
