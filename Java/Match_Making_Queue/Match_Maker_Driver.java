//Match_Maker_Driver to test and run the program and to print out the results in a separate Game_Log.txt file

import java.io.FileWriter;

public class Match_Maker_Driver {

	public static void main(String[] args) throws Exception {

		runMatchMaker();
	}
	
//	runMatchMaker method to run the whole program
	public static void runMatchMaker() throws Exception {
	
	//	create both the matchMaker class and the Game class
		Match_Maker matchMaker = new Match_Maker();
		Game gameHolder = new Game();
		
	//	FileWriters to write the final strings to Game_Log.txt and Queue_Log.txt files
		FileWriter game_Writer = new FileWriter("src\\match_maker_queue\\Game_Log.txt");
		FileWriter queue_Writer = new FileWriter("src\\match_maker_queue\\Queue_Log.txt");
		
	//	while loop to keep running through the program until 10 games are finished
		while (gameHolder.getNumGamesFinished() < 10) {
		//	creates new players to add to the queues
			matchMaker.runMatchMaker();
		//	queue_Writer keeps track of queue progression from start to finish
			queue_Writer.write(matchMaker.toString());
		//	calls the checkQueues method to check if any of the queues has 10 or more players
			matchMaker.checkQueues(gameHolder);
		//	run the games and check for any game completions
			gameHolder.runGame();
		}
	//	write the final game log showing all games
		game_Writer.write(gameHolder.toString());
	//	close both FileWriters
		game_Writer.close();
		queue_Writer.close();
	}
}