/* 
 *  This is a simple program to read in a message and to perform ROT encryption on
 *  the message, using a value entered by the user as the ROT shift number.
 */ 

#include <stdio.h>

int main() {
    // Problem assumes input no larger than 80
    char message[80];

    // Count to check for string length, shiftNum for number of characters to shift
    int i, count, shiftNum;

    // Read in the message to be encrypted, scanf formatting accepts the message as a line
    printf("Enter message to be encrypted: ");
    scanf("%[^\n]", message);

    printf("Enter shift amount (1-25): ");
    scanf("%d", &shiftNum);
    
    count = 0;
    
    // Loop through and count the number of characters in the message
    for (i = 0; i < 80; i++) {
        // If there is a char then increment count by 1
        if (message[i] != 0)
            count++;
        // Otherwise a null character is found (end of message), break out of the loop
        else
            break;
    }

    // Loop through every character in the message array
    for (i = 0; i < count; i++) {
        // Check whether the current char in the array is between a and z
        if (message[i] >= 'a' && message[i] <= 'z') {
            // Shift char by the amount specified by shiftNum, wrap around alphabet if it goes past z
            message[i] = ((message[i] - 'a') + shiftNum) % 26 + 'a';
        }
        // Check whether the current char in the array is between A and Z
        else if (message[i] >= 'A' && message[i] <= 'Z') {
            // Shift char by the amount specified by shiftNum, wrap around alphabet if it goes past Z
            message[i] = ((message[i] - 'A') + shiftNum) % 26 + 'A';
        }
    }
    // Print out final shifted message
    printf("%s\n", message);
    
    return 0;
}