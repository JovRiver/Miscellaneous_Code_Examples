/** Program to calculate row-major dot product using multi-threading */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Edmondson_Slaybaugh {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		// Holds each of the threads I create
		ArrayList<Thread> threads = new ArrayList<>();

		// Two ways to grab the file with the matrices. Either through console or directly
		File file = new File(args[0]);
		//File file = new File(".\\src\\Matrices.txt");

		Scanner input = new Scanner(file);

		// Grab the first line in the file and split on space		
		String line = input.nextLine();
		String[] dimensions = line.split(" ");
		
		// Set variables for the rows and columns of both matrices
		int RowA = 0;
		int ColA = 0;
		int RowB = 0;
		int ColB = 0;
		
		try {
			// Assign the values on the first line to be the Rows and Columns for each matrix
			RowA = Integer.parseInt(dimensions[0]);
			ColA = Integer.parseInt(dimensions[1]);

			RowB = Integer.parseInt(dimensions[2]);
			ColB = Integer.parseInt(dimensions[3]);
		
			// If matrix A column size does not equal matrix B column size, row major dot product is not possible
			if (ColA != ColB) {
				System.out.print("Incompatible Matrices");
			}
			else {
				// Initialize each matrix with the values found above
				int[][] matrixA = new int[RowA][ColA];
				int[][] matrixB = new int[RowB][ColB];
				int[][] matrixC = new int[RowA][RowB];

				// Skip line in file (should be empty)
				line = input.nextLine();
				
				// Loop through each line corresponding to matrixA in the file
				for (int i = 0; i < RowA; i++) {
					// Grab the next line in the file and split on double space
					line = input.nextLine();
					String[] elementsA = line.split("  ");

					// Loop through each column corresponding to matrixA in the file
					for (int j = 0; j < ColA; j++) {
						// Assign values from the file to each cell in matrixA
						matrixA[i][j] = Integer.parseInt(elementsA[j]);
					}
				}

				// Skip line in file (should be empty)
				line = input.nextLine();

				// Loop through each line corresponding to matrixB in the file
				for (int i = 0; i < RowB; i++) {
					// Grab the next line in the file and split on double space
					line = input.nextLine();
					String[] elementsB = line.split("  ");

					// Loop through each column corresponding to matrixB in the file
					for (int j = 0; j < ColB; j++) {
						// Assign values from the file to each cell in matrixB
						matrixB[i][j] = Integer.parseInt(elementsB[j]);
					}
				}
				
				// Create RowA * RowB threads to calculate each cell of matrixC
				for (int i = 0; i < RowA; i++) {
					for (int j = 0; j < RowB; j++) { 
						Thread thr = new Thread(new DotProduct(matrixA, matrixB, matrixC, i, j, ColA));
						threads.add(thr);
					}
				}
				
				// Loop through each thread and start
				for (Thread thr : threads) {
					thr.start();
				}			
				// Loop through each thread and join
				for (Thread thr : threads) {
					// join() will force main thread to wait for each thread to finish before continuing
					thr.join();
				}
				
				System.out.print("\n Product Matrix\n");
				// Print matrix C
				for (int i = 0; i < RowA; i++) {
					for (int j = 0; j < RowB; j++) {
						System.out.printf("%3s ", matrixC[i][j]);
					}
					System.out.println();
				}
			}
		}
		catch (Exception e) {
			System.out.printf("\nError parsing file, invalid file formatting detected.\nProgram Terminating...\n");
		}
		input.close(); // close scanner
	}
}

// DotProduct class to handle threaded calculations
class DotProduct implements Runnable {
	private int[][] A;
	private int[][] B;
	private int[][] C;
	private int RowA;	// value for row in C (matrixC)
	private int RowB; 	// value for column in C (matrixC)
	private int ColA;	// value for number of operations for each cell in matrix C
	
	public DotProduct(int[][] matrixA, int[][] matrixB, int[][] matrixC, int RowA, int RowB, int ColA) {
		this.A = matrixA;
		this.B = matrixB;
		this.C = matrixC;
		this.RowA = RowA;
		this.RowB = RowB;
		this.ColA = ColA;
	}
	@Override
	public void run() {
		try {
			// Print out each thread as it starts calculating
			System.out.printf("Thread<%d,%d> starts calculation\n", RowA, RowB);
		  	// Matrix multiplication (row major dot product)
			for (int i = 0; i < ColA; i++) {
				C[RowA][RowB] += A[RowA][i] * B[RowB][i];
			}
			// Print out each thread as it finishes calculating
			System.out.printf("Thread<%d,%d> returns <%d>\n", RowA, RowB, C[RowA][RowB]);
		}
		catch (Exception e) {
			System.out.printf("Error in Thread<%d,%d>\n", RowA, RowB);
		}
	}
}