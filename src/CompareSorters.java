package edu.iastate.cs228.hw2;

/**
 *  
 * @author Autrin Hakimi
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 
import java.io.FileInputStream;


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		PointScanner[] scanners = new PointScanner[4];
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		Scanner scnr = new Scanner(System.in);
		Random generator = new Random();
		while(true) {
			System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
			int input = scnr.nextInt();

			if(input == 1) { // random integers
				System.out.println("Enter number of random integers: ");
				int numOfrandNums = scnr.nextInt();
				Point[] randPoints = generateRandomPoints(numOfrandNums, generator);
				scanners[0] = new PointScanner(randPoints, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(randPoints, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(randPoints, Algorithm.MergeSort);
				scanners[3] = new PointScanner(randPoints, Algorithm.QuickSort);
			}
			else if(input == 2) { // file input
				System.out.println("Points from a file");
				System.out.print("File name: ");
				String fileName = scnr.next();
				FileInputStream file = new FileInputStream(fileName);
				Scanner scnrFile = new Scanner(file);
				if(scnrFile.hasNext()) {
					scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
					scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
					scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
					scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
				}
			}
			else if(input == 3 ) { // exit
				break;
			}
			else {
				break;  //nothing was mentioned about this, so I just added else. I know that I can combine this
				// with the last else if statement. This way is easy to understand that I followed 
				// the instructors' and file instructions.
			}
			for(int i = 0; i < 4; i++) {
				scanners[i].scan();
				scanners[i].writeMCPToFile();
			}
			System.out.println("algorithm         size  time (ns)");
			System.out.println("----------------------------------");
			for(int i = 0; i < 4; i++) {
				System.out.println(scanners[i].stats());
			}
		}
	}
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if(numPts < 1) {
			throw new IllegalArgumentException();
		}
		Point[] p = new Point[numPts];
		int x = 0;
		int y = 0;
		for(int i = 0; i < numPts; i++) {
			x = rand.nextInt(101) - 50;
			y = rand.nextInt(101) - 50;
			p[i] = new Point(x, y);
		}
		return p; 
	}
}
