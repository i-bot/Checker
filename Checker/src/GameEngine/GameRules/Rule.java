package GameEngine.GameRules;

import java.util.ArrayList;

import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Color;
import GameEngine.CheckerBoard.Move;
import GameEngine.CheckerBoard.Piece;

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
	public abstract ArrayList<Move> getOpponentsMovesForJumpingSelectedPiece(Move m);
	public abstract ArrayList<Move> getOpponentsMovesForJumpingSelectedPieceOfLastMove(ArrayList<Move> moves);
	public abstract Rule clone(CheckerBoard changedCheckerBoard);
	public abstract Rule clone();
	
	public Rule(CheckerBoard originalCheckerBoard){
		this.originalCheckerBoard = originalCheckerBoard;
		updateCurrentCheckerBoard();
	}

	public void updateCurrentCheckerBoard(){
		currentCheckerBoard = originalCheckerBoard.clone();
	}
}
