package GameEngine;

import java.util.ArrayList;

public abstract class Player {
	
	protected String name;
	protected Color color_player;
	protected ArrayList<Move> movesWithJumps;
	
	public Player(Color color_player){
		this.color_player = color_player;
	}
	
	public Boolean containsMove(Move m){
		for(Move moveFromList : movesWithJumps)
			if(moveFromList.equals(m))
				return true;
		
		return movesWithJumps.size() == 0;
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
