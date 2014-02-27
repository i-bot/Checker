package GameEngine;

import AIs.AI;

public class Player {

	private AI ai;
	
	public Player() {
		ai = null;
	}
	
	public Player(AI ai){
		this.ai = ai;
	}
	
	public AI getAI(){
		return ai;
	}

}
