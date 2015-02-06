package Game.Player;

import java.util.ArrayList;
import java.util.Random;

import Game.CheckerBoard.CheckerBoard;
import Game.CheckerBoard.Color;
import Game.CheckerBoard.Move;
import Game.GameRules.Rule;

public abstract class Player {
	
	private Random random, random_removing;
	
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
		
		random = new Random(2);
		random_removing = new Random(2);
	}

	public void setMovesWithJumps(ArrayList<Move> movesWithJumps){
		this.movesWithJumps = movesWithJumps;
	}

	public Move getMoveForRemovingPiece(){
		return movesWithJumps.get((int) (random_removing.nextDouble() * movesWithJumps.size()));
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
		return allPossibleMoves.get((int) (random.nextDouble() * allPossibleMoves.size()));
	}
}
