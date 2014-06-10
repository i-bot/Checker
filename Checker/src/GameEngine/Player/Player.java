package GameEngine.Player;

import java.util.ArrayList;

import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Color;
import GameEngine.CheckerBoard.Move;
import GameEngine.GameRules.Rule;

public abstract class Player {
	
	protected String name;
	protected Color color;
	protected ArrayList<Move> movesWithJumps;
	
	public abstract void clear();
	public abstract Boolean containsMove(Move m);
	public abstract Move getAndExecuteNextMove(CheckerBoard checkerBoard, Rule rule) throws InterruptedException;
	public abstract void handleEnemyMove(Move move_enemy);
	
	public Player(Color color){
		this.color = color;
		movesWithJumps = new ArrayList<>();
	}

	public void setMovesWithJumps(ArrayList<Move> movesWithJumps){
		System.out.println("Player.setMovesWithJumps()" + movesWithJumps.size());
		this.movesWithJumps = movesWithJumps;
	}

	public Move getMoveForRemovingPiece(){
		return movesWithJumps.get((int) (Math.random() * movesWithJumps.size()));
	}
	
	public String getName(){
		return name;
	}
	
	public Color getColor(){
		return color;
	}

	public Move getRandomMove(Rule rule){
		ArrayList<Move> allPossibleMoves = rule.getMovesWithJumps(color);
		allPossibleMoves.addAll(rule.getNormalMoves(color));
		return allPossibleMoves.get((int) (Math.random() * allPossibleMoves.size()));
	}
}
