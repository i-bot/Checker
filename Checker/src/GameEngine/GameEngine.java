package GameEngine;

import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Move;
import GameEngine.GameRules.DefaultRule;
import GameEngine.GameRules.Rule;
import GameEngine.Player.Player;
import KeyInput.Mouse;

public class GameEngine extends Thread{

	private Game game;
	private Rule rule;
	private CheckerBoard checkerBoard;

	public void init(Game game){
		this.game = game;
		
		game.changeCurrentPlayer(game.getLightPlayer());
		
		checkerBoard = new CheckerBoard();
		rule = new DefaultRule(checkerBoard);
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
		currentPlayer.setMovesWithJumps(rule.getMovesWithJumps(currentPlayer.getColor()));
		Move m = currentPlayer.getAndExecuteNextMove(checkerBoard, rule);
		
		Move t_m = m.clone();
		game.changeCurrentPlayer(game.getOpponentOfCurrentPlayer());
		getCurrentPlayer().handleEnemyMove(t_m);		
	}

	@Override
	public void run() {
		Mouse.resetMouseList();

		while(!isInterrupted() && !isGameOver()){
			try {
				handleNextMove(game.getCurrentPlayer());
				Gui.Gui.repaintScreen();
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
	
	public synchronized Boolean isGameOver(){
		return checkerBoard.isGameOver(rule, game.getCurrentPlayer().getColor()) != null;
	}
	
	public synchronized Player getWinner(){
		return game.getPlayerByColor(checkerBoard.isGameOver(rule, game.getCurrentPlayer().getColor()));
	}
}
