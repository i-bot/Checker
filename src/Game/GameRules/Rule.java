package Game.GameRules;

import java.util.ArrayList;

import Game.CheckerBoard.CheckerBoard;
import Game.CheckerBoard.Color;
import Game.CheckerBoard.Move;
import Game.CheckerBoard.Piece;

public abstract class Rule {

	private CheckerBoard originalCheckerBoard;
	protected CheckerBoard currentCheckerBoard;
	
	public abstract ArrayList<Move> getMovesWithJumps(Color color);
	public abstract ArrayList<Move> getNormalMoves(Color color);
	public abstract ArrayList<Move> getMovesWithJumps(Piece currentPiece);
	public abstract ArrayList<Move> getNormalMoves(Piece currentPiece);
	public abstract Boolean checkMove(Move m);
	public abstract Boolean canJump(Piece piece);
	public abstract Boolean isNormalMove(Move m);
	public abstract Boolean isJump(Move m);	
	public abstract Boolean canBeMadeToKing(Piece piece);
	public abstract ArrayList<Move> getOpponentsMovesForJumpingPiece(Piece piece);
	public abstract Integer getDistanceToBorder(Border border, Piece piece);
	public abstract Rule clone(Move m);
	public abstract Rule clone(ArrayList<Move> moves);
	public abstract Rule clone(CheckerBoard changedCheckerBoard);
	public abstract Rule clone();
	
	public Rule(CheckerBoard originalCheckerBoard){
		this.originalCheckerBoard = originalCheckerBoard;
		resetCheckerBoard();
	}

	public void resetCheckerBoard(){
		currentCheckerBoard = originalCheckerBoard.clone();
	}
	
	public enum Border {
		WEST, EAST, OWN_BASELINE, OPPONENTS_BASELINE;
	}
}
