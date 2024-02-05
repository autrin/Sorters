package edu.iastate.cs228.hw2;

/**
 * 
 * @author Autrin Hakimi
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * @author Autrin Hakimi
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; // stores points

	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	// the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    // the type of sorting algorithm

	protected long scanTime; // execution time in nanoseconds. 

	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		sortingAlgorithm  = algo;
		points = new Point[pts.length];
		for(int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
	}


	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException //made public for testing
	{
		sortingAlgorithm = algo;
		FileInputStream file = new FileInputStream(inputFileName);
		Scanner scnr = new Scanner(file);
		//		
		ArrayList<Point> ptsList = new ArrayList<>();
		try {
			while(scnr.hasNextInt()) {
				ptsList.add(new Point(scnr.nextInt(),scnr.nextInt()));
			}
		}
		catch (NoSuchElementException e) {
			throw new InputMismatchException();
		}
		points = new Point[ptsList.size()];
		points = ptsList.toArray(points);
	}


	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		AbstractSorter aSorter; 

		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		if(sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.QuickSort) {
			aSorter = new QuickSorter(points);
		}
		else if(sortingAlgorithm == Algorithm. SelectionSort) {
			aSorter = new SelectionSorter(points);
		}
		else {
			return;
		}	
		aSorter.setComparator(0); // by x
		long beginX = System.nanoTime(); // the start of time execution of x
		aSorter.sort();
		//test x:
		//		Point[] arrNew = new Point[points.length];
		//		aSorter.getPoints(arrNew);
		//		System.out.println(Arrays.toString(arrNew));

		long endX = System.nanoTime(); // the end of time execution of k
		int mcpX = aSorter.getMedian().getX();
		aSorter.setComparator(1); // by y
		long beginY = System.nanoTime(); // the start of time execution of y
		aSorter.sort();
		//test y:
		//		aSorter.getPoints(arrNew);
		//		System.out.println(Arrays.toString(arrNew)); //test

		long endY = System.nanoTime(); // the end of time execution of y
		int mcpY = aSorter.getMedian().getY();
		medianCoordinatePoint = new Point(mcpX, mcpY);
		//		System.out.println(medianCoordinatePoint); test
		scanTime = (endX - beginX) + (endY - beginY);
	}


	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{

		return String.format("%-18s", sortingAlgorithm.toString()) + String.format("%-6d", points.length) +
				String.format("%-10d", scanTime) ;

	}


	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: " + medianCoordinatePoint; 

	}


	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		// TODO 
		try {
			File myObj = new File("newFile.txt");
			//			if (myObj.createNewFile()) {
			//				System.out.println("File created: " + myObj.getName());
			//			} else {
			//				System.out.println("File already exists.");
			//			}
			FileWriter myWriter = new FileWriter("newFile.txt");
			myWriter.write(toString());
			myWriter.close();
			//			System.out.println("Successfully wrote to the file.");
		} catch (Exception e) {
			//			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}	




}
