// Match_Maker class to generate players, assign ranks, and assign them to their respective queues

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Match_Maker extends Game{

		private Queue<Player> goldQueue = new LinkedList<Player>();
		private Queue<Player> silverQueue = new LinkedList<Player>();
		private Queue<Player> bronzeQueue = new LinkedList<Player>();
		private Queue<Player> player_Roster;
		
		private int count = 1;
		private int numPlayers;

	public void runMatchMaker() {
	//	randomly generate number of players to be added during each pass of the program
		numPlayers = (int)(Math.random() * 40 + 1);
	
	//	loop through all the new players, assigning them names, ranks, and queues
		for (int i = 0; i < numPlayers; i++) {
			int rank = (int)(Math.random() * 400);
			Player p = new Player(randomName(), rank);
			
		//	if/else statements to assign new players to their respective queues
			if (rank < 200) {
				bronzeQueue.add(p);
			}
			else if (rank < 300) {
				silverQueue.add(p);
			}
			else {
				goldQueue.add(p);
			}
		}
	}
	
//	checkQueues method to check if each queue has more than 10 players
//	and to add them to a new game if they do
	public void checkQueues(Game g) {
		if (goldQueue.size() > 9) {
		//	initialize player_Roster for grouping of 10 players to be added to a new game
			player_Roster = new LinkedList<Player>();
			for (int j = 0; j < 10; j++) {
				player_Roster.add(goldQueue.remove());
			}
			g.addGame(player_Roster);
		}
	
		if (silverQueue.size() > 9) {
		//	reinitialize player_Roster for next grouping
			player_Roster = new LinkedList<Player>();
			for (int j = 0; j < 10; j++) {
				player_Roster.add(silverQueue.remove());
			}
			g.addGame(player_Roster);
		}
		
		if (bronzeQueue.size() > 9) {
		//	reinitialize player_Roster for next grouping
			player_Roster = new LinkedList<Player>();
			for (int j = 0; j < 10; j++) {
				player_Roster.add(bronzeQueue.remove());
			}
			g.addGame(player_Roster);
		}
	}

//	random names for the program to choose from
	public static String randomName() {
		String[] names = {"Larker", "Samwhich", "Chad", "Carthi", "Nomia", "Duo", "Sekrio", "Darpi", "Cambri", "Supmio",
							"Mithrok", "Cannila", "Saro", "Lupinai", "Gartin", "Liphin", "Dario", "Harker", "Coop", "Damage",
							"Oinri", "Qwantum", "Karro", "Gwenn", "Asfault", "Make", "Jinto", "Warinius", "Sam", "Ghost",
							"Spiker", "Juin", "Lament", "Horie", "Maci", "Lopin", "Jerio", "Triniti", "Werner", "Foo",
							"Oonis", "Harris", "Mack", "Likier", "Mordin", "Favo", "Bilka", "Wisher", "Slate", "Darris",
							"Joan", "Forest", "Xylo", "Zane", "Grani", "Dif", "Lows", "Qwerty", "Asker", "Schtick"};
		
		return (names[(int)(Math.random() * 60)]);
	}

//	toString to format Match_Maker strings as such
	
/** 
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
								   PROGRAM PASS: 1
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------


		[Gold Queue]				[Silver Queue]					[Bronze Queue]		  
   		(Size: 6)					(Size: 9)						(Size: 11)			  
__________________________________________________________________________________________
1 |		Id:Cannila, Rank:351    |	Id:Chad, Rank:247          |	Id:Liphin, Rank:113   |
__________________________________________________________________________________________
2 |		Id:Cambri, Rank:347     |	Id:Darpi, Rank:290         |	Id:Make, Rank:143     |
__________________________________________________________________________________________
3 |		Id:Chad, Rank:367       |	Id:Coop, Rank:276          |	Id:Spiker, Rank:190   |
__________________________________________________________________________________________
4 |		Id:Juin, Rank:360       |	Id:Juin, Rank:277          |	Id:Coop, Rank:130     |
__________________________________________________________________________________________
5 |		Id:Dario, Rank:316      |	Id:Lament, Rank:239        |	Id:Spiker, Rank:5     |
__________________________________________________________________________________________
6 |		Id:Oinri, Rank:326      |	Id:Coop, Rank:283          |	Id:Lopin, Rank:187    |
__________________________________________________________________________________________
7 |								|	Id:Dario, Rank:270         |	Id:Maci, Rank:182     |
__________________________________________________________________________________________
8 |								|	Id:Qwantum, Rank:272       |	Id:Qwantum, Rank:79   |
__________________________________________________________________________________________
9 |								|	Id:Sekrio, Rank:207        |	Id:Make, Rank:80      |
__________________________________________________________________________________________
10|								|							   |	Id:Karro, Rank:85     |
__________________________________________________________________________________________
11|								|							   |	Id:Damage, Rank:31    |
__________________________________________________________________________________________
12|								|							   |						  |
__________________________________________________________________________________________
*/
	
	@Override
	public String toString() {
		StringBuilder sr = new StringBuilder();
	
	//	PRORGRAM PASS header and count variable to keep track of the number of times the program has run
		sr.append(String.format("------------------------------------------------------------------------------------------\n"
							  + "------------------------------------------------------------------------------------------\n"
							  + "								   PROGRAM PASS: %d\n"
							  + "------------------------------------------------------------------------------------------\n"
							  + "------------------------------------------------------------------------------------------\n\n\n", count++));
		
	//	creates the list for gold, silver, and bronze queues and their respective sizes
		sr.append(String.format("\t\t[Gold Queue]\t\t\t\t[Silver Queue]\t\t\t\t\t[Bronze Queue]\t\t  \n  "
							  + " \t\t(Size: %d)\t\t\t\t\t(Size: %d)\t\t\t\t\t\t(Size: %d)\t\t\t  \n"
							  + "__________________________________________________________________________________________\n",
								goldQueue.size(), silverQueue.size(), bronzeQueue.size()));
		
	//	boolean check value to check for non-empty queues and i value to track player number
		boolean check = true;
		int i = 1;
		
	//	Iterators to iterate over each queue
		Iterator<Player> gold_Iter = goldQueue.iterator();
		Iterator<Player> silver_Iter = silverQueue.iterator();
		Iterator<Player> bronze_Iter = bronzeQueue.iterator();
		
	//	while loop that only runs if there is at least one non-empty queue
		while (check) {
			
		//	initialize check to false for each pass of the while loop, it will be changed if
		//	any one of the queues is non-empty
			check = false;
			sr.append(String.format("%-2d|", i++));
		
		//	check if goldQueue has a value for the Iterator
			if (gold_Iter.hasNext()) {
				sr.append(String.format("\t\t%-24s|", gold_Iter.next()));
				check = true;
			}
		//	else statement to provide blank space in case of no values for the goldQueue column
			else
				sr.append("\t\t\t\t\t\t\t\t|");
			
		//	check if silverQueue has a value for the Iterator
			if (silver_Iter.hasNext()) {
				sr.append(String.format("\t%-27s|", silver_Iter.next()));
				check = true;
			}
		//	else statement to provide blank space in case of no values for the silverQueue column
			else
				sr.append("\t\t\t\t\t\t\t   |");
			
		//	check if bronzeQueue has a value for the Iterator
			if (bronze_Iter.hasNext()) {
				sr.append(String.format("\t%-22s|", bronze_Iter.next()));
				check = true;
			}
		//	else statement to provide blank space in case of no values for the bronzeQueue column
			else {
				sr.append("\t\t\t\t\t\t  |");
			}
			sr.append("\n__________________________________________________________________________________________\n");
		}
		sr.append("\n\n");
		return String.format(sr.toString());
	}
}