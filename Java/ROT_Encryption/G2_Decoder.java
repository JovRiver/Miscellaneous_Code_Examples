/*
 * ROT decoder
*/

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class G2_Decoder {

	public static void main(String[] args) throws Exception {

		decodeMessageTrials();
		decodeMessage();
	}
	
	private static void decodeMessage() throws Exception {
		ArrayList<File> files = getFiles();
		
		// This loops through each of the files and uses the appropriate decoder scheme to decrypt the message
		// and writes it to a text file
		for (File f : files) {
			if (f.equals(getFiles().get(0)))
				getMessage(f, 13);
			else if (f.equals(getFiles().get(1)))
				getMessage(f, 19);
			else if (f.equals(getFiles().get(2)))
				getMessage(f, 9);
			else if (f.equals(getFiles().get(3)))
				getMessage(f, 7);
			else if (f.equals(getFiles().get(4)))
				getMessage(f, 17);
			else if (f.equals(getFiles().get(5)))
				getMessage(f, 23);
		}
	}
	
	private static void getMessage(File f, int i) throws Exception {
		Scanner input = new Scanner(f);
		String fileName = f.getName().substring(0, 2);
		StringBuilder sr = new StringBuilder("[" + fileName + " Plain Text] | Either ROT +" + (26 - i) + " or ROT -" + i + " Encryption Scheme\n");
	
		// Loop through each line in the current file
		while (input.hasNext()) {
			String line = input.nextLine();
			String[] tokens = line.split("");
		
			// Check for and ignore blank lines
			if (line.length() == 0)
				continue;
			
			// Loop through each token and perform appropriate ROT scheme on characters
			for (String s : tokens) {
				char c = s.charAt(0);
				if (c == ' ') {
					sr.append(' ');
				}
				
				// Check each character and determine its corresponding ROT character
				else if (c >= 'a' && c <= 'z') {
					c += i;
					if (c > 'z') {
						c -= 26;
					}
					sr.append(c);
				}
				else if (c >= 'A' && c <= 'Z') {
					c += i;
					if (c > 'Z') {
						c -= 26;
					}
					sr.append(c);
				}
			}
			sr.append("\n");
		}

		// Create file and write decrypted message to it
		FileWriter plainWriter = new FileWriter("src\\encode_decode\\" + fileName + "_Plaintext.txt");
		plainWriter.write(sr.toString());
		plainWriter.close();
		input.close();
	}

	// This calls the rotDecoder method to brute force each message until the right scheme is found
	private static void decodeMessageTrials() throws Exception {
		ArrayList<File> files = getFiles();
		
		rotDecoder(files);
	}
	// Brute force all of the ROT schemes
	private static void rotDecoder(ArrayList<File> files) throws Exception {
		int j = 0;
		// Loop through every file in files array
		for (File f : files) {
			Scanner input = new Scanner(f);
		
			// Header for each file set
			System.out.printf("[NEXT FILE GROUP] FILE: %d\n\n", ++j);
			int k = 1;
			
			// Loop through each line in the current file
			while (input.hasNext()) {
				
				// String and String array to break apart each character in order to make ROT changes
				String line = input.nextLine();
				String[] tokens = line.split("");
				
				// Check for and ignore blank lines
				if (line.length() == 0)
					continue;
				
				// Header for each line set
				System.out.printf("-----------------\n"
						+ "[FILE %d, LINE: %d]\n"
						+ "-----------------\n\n", j, k);
				
				// Perform every possible ROT scheme on current line
				for (int i = 0; i < 26; i++) {
					StringBuilder sr = new StringBuilder("[ROT: " + i + "] - ");
					for (String s : tokens) {
						char c = s.charAt(0);
							if (c == ' ') {
								sr.append(' ');
							}
						
							// Check each character and determine its corresponding ROT character
							else if (c >= 'a' && c <= 'z') {
								c += i;
								if (c > 'z') {
									c -= 26;
								}
								sr.append(c);
							}
							else if (c >= 'A' && c <= 'Z') {
								c += i;
								if (c > 'Z') {
									c -= 26;
								}
								sr.append(c);
							}
						}
					
						// Print out each line to manually check for correct decryption
						System.out.println(sr.toString() + "\n");
					}
				k++;
			}
		input.close();
		}
	}
	// Creates a files array and fills it with all available files for the rest of the code to use
	private static ArrayList<File> getFiles() {
		ArrayList<File> files = new ArrayList<>();
		File f1 = new File("src\\encode_decode\\G1_Ciphertext.txt");
		File f2 = new File("src\\encode_decode\\G2_Ciphertext.txt");
		File f3 = new File("src\\encode_decode\\G5_Ciphertext.txt");
		File f4 = new File("src\\encode_decode\\G7_Ciphertext.txt");
		File f5 = new File("src\\encode_decode\\G8_Ciphertext.txt");
		File f6 = new File("src\\encode_decode\\G9_Ciphertext.txt");
		
		files.add(f1);
		files.add(f2);
		files.add(f3);
		files.add(f4);
		files.add(f5);
		files.add(f6);
		
		return files;
	}
}