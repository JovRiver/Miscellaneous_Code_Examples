import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

// This program reads in a dat file of TCP connections, splits them between senders, and
// creates a separate file of each senders traffic

public class Process_TCPProbe_rs {

	public static void main(String[] args) {
		// read_File handles reading the file and calling write_File to write
		// each senders traffic to the appropriate file
		read_File(args);
	}
	
	// This method will read in the dat file
	private static void read_File(String[] args) {
		int i = 0;
		// Try catch block for attempting to read the file
		try {
			// File takes first argument as file
			File file = new File(args[0]);
			Scanner input = new Scanner(file);
			
			// Create StringBuilders to build each senders traffic report
			StringBuilder build_Sender_1 = new StringBuilder();
			StringBuilder build_Sender_2 = new StringBuilder();
			StringBuilder build_Sender_3 = new StringBuilder();
			
			i = 1;
			
			// Create variables to hold each senders unique id
			String sender_1 = args[1].substring(args[1].indexOf("_") + 1, args[1].lastIndexOf("_"));
			String sender_2 = args[2].substring(args[2].indexOf("_") + 1, args[2].lastIndexOf("_"));
			String sender_3 = args[3].substring(args[3].indexOf("_") + 1, args[3].lastIndexOf("_"));
			
			// Grab first line and split by space
			String line = input.nextLine();
			String[] elements = line.split(" ");
			
			i = 2;
			
			// Grab the receivers window size (found at position 10 on each line in dat file)
			String receive_Wnd = elements[10];
			
			// Continue to loop through while the file has more lines
			while (input.hasNext()) {				
				
				// Check if the last element in the current elements array is equal to receive window size.
				// I use this to check if the last line is full or broken
				int size = elements.length;
				
				if (elements[size - 1].equals(receive_Wnd)) {
					
					// Grab the current senders id
					String sender = elements[1].substring(elements[1].indexOf(":") + 1);
					
					// Check the current sender against each known sender and add the current line
					// traffic with all spaces replaced by commas to the appropriate StringBuilder
					
					// Sender 1
					if (sender.equals(sender_1)) {
						build_Sender_1.append(line.replace(" ", ",") + "\n");
					}
					
					// Sender 2
					else if (sender.equals(sender_2)) {
						build_Sender_2.append(line.replace(" ", ",") + "\n");
					}
					
					// Sender 3
					else if (sender.equals(sender_3)) {
						build_Sender_3.append(line.replace(" ", ",") + "\n");
					}
				}
				
				// Grab the next line and split it by space
				line = input.nextLine();
				elements = line.split(" ");
			}
			
			// Convert each StringBuilder to a string
			String builder_1 = build_Sender_1.toString();
			String builder_2 = build_Sender_2.toString();
			String builder_3 = build_Sender_3.toString();
			
			// write_File will take args and StringBuilder strings and write the string
			// contents to separate files for each sender
			write_File(args, builder_1, builder_2, builder_3);
			
			System.out.println("I worked correctly!");
			
			// Close input resource
			input.close();
		}
		// Catch any thrown exceptions
		catch (Exception e) {
			System.out.println("\nError with File location or File format:\n" + e + " | " + i);
		}
	}
	
	// Write each senders traffic to their own file
	private static void write_File(String[] args, String builder_1, String builder_2, String builder_3) {
		// Try catch block for safe measure
		try {		
			// Create the FileWriters to write each senders traffic to their own file
			FileWriter write_Sender_1 = new FileWriter(args[1]);
			FileWriter write_Sender_2 = new FileWriter(args[2]);
			FileWriter write_Sender_3 = new FileWriter(args[3]);
		
			// Write to each file
			write_Sender_1.write(builder_1);
			write_Sender_2.write(builder_2);
			write_Sender_3.write(builder_3);
		
			// Close all FileWriter resources
			write_Sender_1.close();
			write_Sender_2.close();
			write_Sender_3.close();
		}
		// Catch any thrown exceptions
		catch (Exception e) {
			System.out.println("\nError with supplied arguments:\n" + e);
		}
	}
}