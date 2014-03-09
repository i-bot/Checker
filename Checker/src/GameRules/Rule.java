package GameRules;

import java.util.ArrayList;

import GameEngine.CheckerBoard;
import GameEngine.Move;

public abstract class Rule {

	protected CheckerBoard currentCheckerBoard;
	
	public Rule(CheckerBoard currentCheckerBoard){
		this.currentCheckerBoard = currentCheckerBoard;
	}
	
	public abstract ArrayList<Move> getMovesWithJumps();
	public abstract Boolean checkMove(Move m);

}
