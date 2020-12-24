// Simple Player class to create players for the rest of the program

public class Player {

	protected String playerName;
	protected int playerRank;
	
	public Player(String name, int rank) {
		this.playerName = name;
		this.playerRank = rank;
	}
	
	public int getRank() {
		return playerRank;
	}

	@Override
	public String toString() {
		return String.format("Id:%s, Rank:%d", playerName, playerRank);
	}
}