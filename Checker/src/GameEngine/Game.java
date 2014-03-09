package GameEngine;

public class Game {
	private GameType gameType;
	private Player player1, player2, currentPlayer;
	
	public Game(GameType gameType, Player player1, Player player2) {
		this.gameType = gameType;
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	public Player getPlayer2() {
		return player2;
	}

	public void changeCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public enum GameType{
		LOCAL, NETWORK;
	}
	
}
