package GameEngine;

import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Move;
import GameEngine.GameRules.DefaultRule;
import GameEngine.GameRules.Rule;
import GameEngine.Player.AIPlayer;
import GameEngine.Player.Player;
import KeyInput.Mouse;

public class GameEngine extends Thread{

	private Game game;
	private Rule rule;
	private CheckerBoard checkerBoard;
	private Boolean gameOver;

	public void init(Game game){
		this.game = game;
		game.changeCurrentPlayer(game.getPlayer1());
		checkerBoard = new CheckerBoard();
		rule = new DefaultRule(checkerBoard);
		gameOver = false;
		
		if(game.getPlayer1() instanceof AIPlayer && !((AIPlayer) game.getPlayer1()).load()) System.err.println(game.getPlayer1() + " didn't load a valid AI");
		if(game.getPlayer2() instanceof AIPlayer && !((AIPlayer) game.getPlayer2()).load()) System.err.println(game.getPlayer2() + " didn't load a valid AI");	
	}

	public synchronized Game getCurrentGame(){
		return game;
	}

	public synchronized Rule getCurrentRule(){
		return rule;
	}

	public synchronized CheckerBoard getCurrentCheckerBoard(){
		return checkerBoard;
	}

	public synchronized Player getCurrentPlayer(){
		return game.getCurrentPlayer(); 
	} 

	private void handleNextMove(Player currentPlayer) throws InterruptedException{		
		currentPlayer.setMovesWithJumps(rule.getMovesWithJumps(currentPlayer.getColor_Player()));
		Move m = currentPlayer.getAndExecuteNextMove(checkerBoard, rule);
		
		Move t_m = m.clone();
		game.changeCurrentPlayer((currentPlayer == game.getPlayer1())? game.getPlayer2() : game.getPlayer1());
		getCurrentPlayer().handleEnemyMove(t_m);
	}

	@Override
	public void run() {
		Mouse.resetMouseList();

		while(!isInterrupted() && !gameOver){
			try {
				handleNextMove(game.getCurrentPlayer());
				Gui.Gui.repaintScreen();
				gameOver = isGameOver();
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
	
	public synchronized Boolean isGameOver(){
		return checkerBoard.isGameOver(rule, game.getCurrentPlayer().getColor_Player()) != null;
	}
	
	public synchronized Player getWinner(){
		return game.getPlayerByColor(checkerBoard.isGameOver(rule, game.getCurrentPlayer().getColor_Player()));
	}
}
