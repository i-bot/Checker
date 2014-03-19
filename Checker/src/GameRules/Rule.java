package GameRules;

import java.util.ArrayList;

import GameEngine.CheckerBoard;
import GameEngine.Color;
import GameEngine.Move;
import GameEngine.Piece;
import GameEngine.Player;

public abstract class Rule {

	private CheckerBoard originalCheckerBoard;
	protected CheckerBoard currentCheckerBoard;
	
	public abstract ArrayList<Move> getMovesWithJumps(Color color);
	public abstract ArrayList<Move> getMovesWithJumps(Piece currentPiece);
	public abstract Boolean checkMove(Player currentPlayer, Move m);
	public abstract Boolean canJump(Piece piece);
	public abstract Boolean isNormalMove(Move m);
	public abstract Boolean isJump(Move m);	
	
	public Rule(CheckerBoard originalCheckerBoard){
		this.originalCheckerBoard = originalCheckerBoard;
		updateCurrentCheckerBoard();
	}

	public void updateCurrentCheckerBoard(){
		currentCheckerBoard = originalCheckerBoard.clone();
	}
}
