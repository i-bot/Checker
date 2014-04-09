package GameEngine.Player;

import java.util.ArrayList;

import GameEngine.CheckerBoard.Color;
import GameEngine.CheckerBoard.Move;

public abstract class Player {
	
	protected String name;
	protected Color color_player;
	protected ArrayList<Move> movesWithJumps;
	
	public abstract void clear();
	public abstract Boolean containsMove(Move m);
	
	public Player(Color color_player){
		this.color_player = color_player;
		movesWithJumps = new ArrayList<>();
	}

	public void setMovesWithJumps(ArrayList<Move> movesWithJumps){
		this.movesWithJumps = movesWithJumps;
	}

	public Move getMoveForRemovingPiece(){
		return movesWithJumps.get((int) (Math.random() * movesWithJumps.size()));
	}
	
	public String getName(){
		return name;
	}
	
	public Color getColor_Player(){
		return color_player;
	}
}
