/* 
 *  This program is a simple game of battleship utilizing a single array of size 10 to hold a single
 *  ship of size 2. The user and "computer" will take turns guessing different positions in each others
 *  arrays until one or the other hits both sections of the enemie's ship. A current state of the board
 *  will be shown after each guess from the user and computer
 *
 *  (Note, there is no error checking regarding inputs from user)
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <time.h>
#include <stdbool.h>

void setPlayerBoard(char *ptr) {
    // Determines if the board is set correctly
    bool isBoardSet = false;

    // Positions of each hard point on our "Battleship"
    int pos1, pos2;

    // Continue looping until the board is set correctly
    do {
        // Prompt user for inputs
        printf("Enter 1st position (0-9): ");
        scanf("%d", &pos1);
        printf("Enter 2nd position (0-9): ");
        scanf("%d", &pos2);
            
        // Verify that the inputs are between 0-9 and are one space from eachother
        if ((pos1 >= 0 && pos1 < 10) && (pos2 >= 0 && pos2 < 10) && ((pos2 == pos1 + 1) || (pos2 == pos1 - 1))) {
            // Change the characters at both positions to S to indicate Ship
            *(ptr + pos1) = 'S';
            *(ptr + pos2) = 'S';
        
            // Board has now been set correctly, so we can move on
            isBoardSet = true;
            printf("Game Board set");
        }
        else {
            printf("Please ensure both positions are digits and are one space from each other.\n");
        }
    } while (isBoardSet == false);
}

void setComputerBoard(char *ptr) {
    // Set seed for rand() function to work correctly
    srand(time(0));
    // Generate random number from 0 to 9
    int rand_Num = rand() % 10;

    if (rand_Num != 9) {
        // Set S position at address *(ptr + rand_Num)
        *(ptr + rand_Num) = 'S';
        // Set S position at address *(ptr + rand_Num + 1)
        *(ptr + rand_Num + 1) = 'S';
    }
    else {
        // Set S position at address of *(ptr + rand_Num)
        *(ptr + rand_Num) = 'S';
        // Set S position at address of *(ptr + rand_Num - 1)
        *(ptr + rand_Num - 1) = 'S';
    }
}

int playGame(char *p_Ptr, char *c_Ptr) {
    // Boolean to keep track of when there is a winner
    bool winner = false;
    
    // 0 equals computer turn, 1 equals player turn
    int turn = 0;

    // Keep track of how many times each combatant successfully hit their target
    int player_Hit_Count = 0;
    int computer_Hit_Count = 0;

    // rand_Num is the variable for the computers index guess and guess is for the human guess
    int rand_Num, guess;

    // Set up the random seed to use for computer guess (rand_Num)
    srand(time(0));

    do {
        // turn == 0, computer turn
        if (turn == 0) {
            // Do-While to ensure that the computer does not re-pick an index it has already guessed
            do {
                // Generate a number between 0-9 to use as computer's guess
                rand_Num = rand() % 10;
            }
            // Check to make sure the current index has not already been guessed 
            while (*(p_Ptr + rand_Num) == 'H' || *(p_Ptr + rand_Num) == 'M');

            printf("\nComputer guesses: %d\n", rand_Num);

            // if value at address *(p_Ptr + rand_Num)in player_Board is S, then successful hit
            if (*(p_Ptr + rand_Num) == 'S') {
                printf("\nHIT!\n");
                // Change value on computer_Board to H
                *(p_Ptr + rand_Num) = 'H';
                computer_Hit_Count++;
            }
            else {
                printf("\nMISS!\n");
                // Change value on player_Board to M
                *(p_Ptr + rand_Num) = 'M';
            }

            // Print current state of human board
            printf("\nHuman Board:\n0 1 2 3 4 5 6 7 8 9\n");
            int i;
            for (i = 0; i < 10; i++) {
                printf("%c ", *(p_Ptr + i));
            }

            // Print current state of computer board
            printf("\n\nComputer Board:\n0 1 2 3 4 5 6 7 8 9\n");
            for (i = 0; i < 10; i++) {
                printf("%c ", *(c_Ptr + i));
            }

            // Check if computer has hit both S's on player_Board
            if (computer_Hit_Count == 2) {
                winner = true;
            }
            else {
                // Swap turns to human
                turn = 1;
            }
        }
        // Turn == 1, player turn
        else {
            // Keep track of whether human has entered a valid input
            bool valid = false;
            // Continue to loop until human enters a valid input
            do {
                printf("\n\nEnter guess: ");
                scanf("%d", &guess);

                // Check if their guess is between 0-9
                if (guess > 0 || guess < 9) {
                    valid = true;
                }
                else {
                    printf("Invalid input");
                }
            } while (valid == false);

            printf("\nYou guessed %d\n", guess);

            // If value at address *(c_Ptr + guess) in computer_Board is S, successful hit
            if (*(c_Ptr + guess) == 'S') {
                printf("\nHit!\n");
                // Change value in computer_Board to H
                *(c_Ptr + guess) = 'H';
                player_Hit_Count++;
            }
            else {
                printf("\nMiss!\n");
                // Change value in computer_Board to M
                *(c_Ptr + guess) = 'M';
            }

            // Print current state of human board
            printf("\nHuman Board:\n0 1 2 3 4 5 6 7 8 9\n");
            int i;
            for (i = 0; i < 10; i++) {
                printf("%c ", *(p_Ptr + i));
            }

            // Print current state of computer board
            printf("\n\nComputer Board:\n0 1 2 3 4 5 6 7 8 9\n");
            for (i = 0; i < 10; i++) {
                printf("%c ", *(c_Ptr + i));
            }
            printf("\n");

            // Check if player has hit both S's on computer_Board
            if (player_Hit_Count == 2) {
                winner = true;
            }
            else {
                // Swap turns to computer
                turn = 0;
            }
        }
    } while (winner == false);

    // Return value 0 or 1, 0 is computer win, 1 is player win
    return turn;
}

int main() {
    // Create pointers for player and computer to use
    char *player_Ptr, *computer_Ptr;

    // Initialize player and computer game board
    char player_Board[10] = {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*'};
    char computer_Board[10] = {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*'};

    // Set each pointer to the beginning of their respective boards
    player_Ptr = &player_Board[0];
    computer_Ptr = &computer_Board[0];

    // Create player board
    setPlayerBoard(player_Ptr);
    // Create computer board
    setComputerBoard(computer_Ptr);

    // Play game
    int winner = playGame(player_Ptr, computer_Ptr);

    if (winner == 0) {
        printf("\n\nComputer Wins!\n");
    }
    else {
        printf("\nHuman Wins!\n");
    }

    return 0;
}
