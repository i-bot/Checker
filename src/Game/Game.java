package Game;

import Game.CheckerBoard.Color;
import Game.Player.Player;

public class Game {
	
	private Player lightPlayer, darkPlayer, currentPlayer;
	
	public Game(Player lightPlayer, Player darkPlayer) {
		this.lightPlayer = lightPlayer;
		this.darkPlayer = darkPlayer;
		currentPlayer = lightPlayer;
	}

	public void setLightPlayer(Player lightPlayer) {
		this.lightPlayer = lightPlayer;
	}
	
	public Player getLightPlayer() {
		return lightPlayer;
	}

	public void setDarkPlayer(Player darkPlayer) {
		this.darkPlayer = darkPlayer;
	}
	
	public Player getDarkPlayer() {
		return darkPlayer;
	}

	public void changeCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Player getPlayerByColor(Color color){
		return (lightPlayer.getColor() == color)? lightPlayer : ((darkPlayer.getColor() == color)? darkPlayer : null);
	}
	
	public Player getOpponentOfCurrentPlayer(){
		return (currentPlayer == lightPlayer)? darkPlayer : lightPlayer;
	}
}
