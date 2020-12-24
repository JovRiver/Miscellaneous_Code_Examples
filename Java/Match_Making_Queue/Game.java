//Ryan Slaybaugh, William Lee, Parker Harris
//Game class to add games and "run" them, to determine when they are finished

package match_maker_queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game {

	private ArrayList<Queue<Player>> activeGames = new ArrayList<>();
	private ArrayList<Queue<Player>> finishedGames = new ArrayList<>();
	private ArrayList<Queue<Player>> allGames = new ArrayList<>();
	private Queue<Player> finished_Game_Temp = new LinkedList<>();
	
//	runGame method to iterate through each game currently in the activeGames list and randomly determine if it
//	is finished. If it is, then it will be added to the finishedGames list and removed from the activeGames list
	public boolean runGame() {
		for (Queue<Player> qp : activeGames) {
		//	random number generated to determine if a game has been "finished"
			if ((int)(Math.random() * 15) >= 14) {
				finished_Game_Temp = new LinkedList<>();
				for (Player p : qp) {
					finished_Game_Temp.add(p);
				}
				finishedGames.add(finished_Game_Temp);
				activeGames.remove(qp);
				return true;
			}
		}
		return false;
	}

//	addGame method to add new games to the activeGames and allGames lists
	public void addGame(Queue<Player> game) {
		activeGames.add(game);
		allGames.add(game);
	}

//	getNumGamesFinished method to keep track of how many games have been "completed"
	public int getNumGamesFinished() {
		return finishedGames.size();
	}
	
//	getGameTier method to get the game tier (gold, silver, or bronze) for the toString method
	public String getGameTier(Queue<Player> game) {
		if (game.element().getRank() < 200)
			return "Bronze";
		else if (game.element().getRank() < 300)
			return "Silver";
		else
			return "Gold";
	}
	
//	toString method to format the Game string as such
/** 
______________________________________________________

GAME ADDED
______________________________________________________

[Game 1]
------------------
[Game Tier] Bronze
------------------------------
[Game Progress] Match Complete
------------------------------

		[Team 1]					[Team 2]
------------------------------------------------------
1	Id:Sekrio, Rank:138     |	Id:Triniti, Rank:51  |
2	Id:Oinri, Rank:69       |	Id:Warinius, Rank:43 |
3	Id:Gartin, Rank:1       |	Id:Chad, Rank:155    |
4	Id:Gwenn, Rank:98       |	Id:Supmio, Rank:176  |
5	Id:Maci, Rank:88        |	Id:Sekrio, Rank:54   |
------------------------------------------------------
*/
	@Override
	public String toString() {
		StringBuilder sr = new StringBuilder();
		int gameCount = 1;
		String progress;

		for (Queue<Player> qp : allGames) {
			
			if (finishedGames.contains(qp))
				progress = "Match Complete";
			else
				progress = "Match Pending";
			
			sr.append("______________________________________________________\n\n"
					+ "GAME ADDED\n"
					+ "______________________________________________________\n\n");
			sr.append(String.format("[Game %d]\n"
								+	"------------------\n"
								+ 	"[Game Tier] %6s\n"
								+ 	"------------------------------\n"
								+ 	"[Game Progress] %14s\n"
								+ 	"------------------------------\n\n"
								+ 	"\t\t[Team 1]\t\t\t\t\t[Team 2]\n"
								+ 	"------------------------------------------------------\n", gameCount++, getGameTier(qp), progress));
			for (int i = 0; i < 5; i++) {
				sr.append(String.format("%d\t%-24s|\t%-21s|\n", (i + 1), qp.remove(), qp.remove()));
			}
			sr.append("------------------------------------------------------\n\n\n");
					 //______________________________________________________
		}
		return String.format(sr.toString());
	}
}