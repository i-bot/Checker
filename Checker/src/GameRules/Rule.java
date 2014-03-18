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
	
	public Rule(CheckerBoard originalCheckerBoard){
		this.originalCheckerBoard = originalCheckerBoard;
		updateCurrentCheckerBoard();
	}
	
	protected abstract ArrayList<Move> al_m_getMovesWithJumps(Color color);
	protected abstract Boolean b_checkMove(Player currentPlayer, Move m);
	protected abstract Boolean b_canJump(Piece piece);
	protected abstract Boolean b_isNormalMove(Move m);
	protected abstract Boolean b_isJump(Move m);
	
	public ArrayList<Move> getMovesWithJumps(Color color){
		ArrayList<Move> temp = al_m_getMovesWithJumps(color);
		return temp;
	}

	public Boolean checkMove(Player currentPlayer, Move m){
		Boolean temp = b_checkMove(currentPlayer, m);
		return temp;
	}
	
	public Boolean canJump(Piece piece){
		Boolean temp = b_canJump(piece);
		return temp;
	}
	
	public Boolean isNormalMove(Move m){
		Boolean temp = b_isNormalMove(m);
		return temp;
	}
	
	public Boolean isJump(Move m){
		Boolean temp = b_isJump(m);
		return temp;
	}
	
	public void updateCurrentCheckerBoard(){
		currentCheckerBoard = originalCheckerBoard.clone();
	}
}
