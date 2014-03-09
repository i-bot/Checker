package GameEngine;

import AIs.AI;

public class AIPlayer extends Player{

	private AI ai;
	
	public AIPlayer(AI ai, Color color_player) {
		super(color_player);
		
		this.ai = ai;
		name = ai.getName();
	}

	public AI getAI(){
		return ai;
	}
}
