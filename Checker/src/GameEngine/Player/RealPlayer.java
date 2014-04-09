package GameEngine.Player;

import java.awt.Point;
import java.util.ArrayList;

import GameEngine.CheckerBoard.Color;
import GameEngine.CheckerBoard.Move;
import GameEngine.CheckerBoard.Piece;

public class RealPlayer extends Player{

	private Piece selectedPiece;
	private Move currentMove;
	private Boolean canChangeSelectedPiece;

	public RealPlayer(String name, Color color_player) {
		super(color_player);
		this.name = name;
		
		clear();
	}
	
	public void handleSelecetedPiece(Piece selectedPiece){
		if(this.selectedPiece == null){
			this.selectedPiece = selectedPiece;
			selectedPiece.setSelected(true);
		}
		else if(this.selectedPiece == selectedPiece){
			selectedPiece.setSelected(false);
			unselectPiece();
		}
		else {
			this.selectedPiece.setSelected(false);
			this.selectedPiece = selectedPiece;
			selectedPiece.setSelected(true);
		}
	}
	
	public Move handleDestinationPoint(Point destinationPoint){
		currentMove = new Move(selectedPiece, destinationPoint);
		return currentMove;
	}
	
	public Move getCurrentMove(){
		return currentMove;
	}
	
	public Boolean hasPieceSelected(){
		return selectedPiece != null;
	}
	
	public Boolean canChangeSelectedPiece(){
		return canChangeSelectedPiece;
	}
	
	public void setSelectedPieceForMultipleJumps(Piece piece){
		selectedPiece = piece;
		piece.setSelected(true);
		canChangeSelectedPiece = false;
	}
	
	public void unselectPiece(){
		selectedPiece = null;
		canChangeSelectedPiece = true;
	}
	
	public void clear(){
		unselectPiece();
		currentMove = null;
		movesWithJumps = new ArrayList<>();
	}

	@Override
	public Boolean containsMove(Move m) {
		for(Move moveFromList : movesWithJumps)
			if(moveFromList.equals_player(m))
				return true;
		
		return movesWithJumps.size() == 0;
	}
}
