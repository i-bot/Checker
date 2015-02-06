package Game;

import java.awt.Point;

import Game.CheckerBoard.CheckerBoard;
import Game.CheckerBoard.Move;
import Game.GameRules.DefaultRule;
import Game.GameRules.Rule;
import Game.Player.Player;

public class GameLoop extends Thread{

	private Game game;
	private Rule rule;
	private CheckerBoard checkerBoard;

	public void init(Game game){
		this.game = game;
		
		game.changeCurrentPlayer(game.getLightPlayer());
		
		checkerBoard = new CheckerBoard();
		rule = new DefaultRule(checkerBoard);
	}

	public void addClickedFieldToCheckerBoard(Point p){
		checkerBoard.addClickField(p);
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
		while(!isInterrupted() && !isGameOver()){
			try {
				handleNextMove(game.getCurrentPlayer());
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
	
	public synchronized Boolean isGameOver(){
		return checkerBoard.isGameOver(rule.clone(), game.getCurrentPlayer().getColor()) != null;
	}
	
	public synchronized Player getWinner(){
		return game.getPlayerByColor(checkerBoard.isGameOver(rule, game.getCurrentPlayer().getColor()));
	}
}
