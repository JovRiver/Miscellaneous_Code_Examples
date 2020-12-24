/*
 *	An ROT encoder using ROT 7
 */

import java.io.FileWriter;
import java.util.ArrayList;

public class G2_Encoder {

	public static void main(String[] args) throws Exception {

		ArrayList<String> arrays = createMessages();
		encodeMessages(arrays);
	}
	// Encodes our messages using ROT 7 scheme and writes to G2_Plaintext.txt before encryption and
	// to G2_Ciphertext.txt after encryption
	private static void encodeMessages(ArrayList<String> arrays) throws Exception {
		StringBuilder sr1 = new StringBuilder();
		StringBuilder sr2 = new StringBuilder();
		
		for (int i = 0; i < 5; i++) {
			sr1.append(arrays.get(i) + "\n");
		}
	
		// Write messages to a file before performing ROT encryption
		FileWriter plainWriter = new FileWriter("src\\encode_decode\\G2_Plaintext.txt");
		plainWriter.write(sr1.toString() + "\n");
		
		// Loop through each message
		for (int i = 0; i < 5; i++) {
			String[] tokens = arrays.get(i).split("");

			for (String s : tokens) {
				char c = s.charAt(0);
			
				if (c == ' ')
					sr2.append(c);
				
				// Check each character and set it to its corresponding ROT character
				else if (c >= 'a' && c <= 'z') {
					c += 7;
					if (c > 'z') {
						c -= 26;
					}
					sr2.append(c);
				}
				else if (c >= 'A' && c <= 'Z') {
					c += 7;
					if (c > 'Z') {
						c -= 26;
					}
					sr2.append(c);
				}
			}
			sr2.append("\n");
		}
	
		// Write encrypted messages to a file
		FileWriter cipherWriter = new FileWriter("src\\encode_decode\\G2_Ciphertext.txt");
		cipherWriter.write(sr2.toString());
		plainWriter.close();
		cipherWriter.close();
	}
	
	// Creates an array of messages to be used by the rest of the code
	private static ArrayList<String> createMessages() {
		ArrayList<String> array = new ArrayList<>();
		
		String message1 = "Congratulations on deciphering the message woo";
		String message2 = "I bet this one was not that hard";
		String message3 = "Guess what I had for dinner last night";
		String message4 = "Cuckoo cachoo and that means you";
		String message5 = "The time hath come";
		
		array.add(message1);
		array.add(message2);
		array.add(message3);
		array.add(message4);
		array.add(message5);
		
		return array;
	}
}