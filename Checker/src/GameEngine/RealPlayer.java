package GameEngine;

import java.awt.Point;
import java.util.ArrayList;

public class RealPlayer extends Player{

	private Piece selectedPiece;
	private Move currentMove;
	private ArrayList<Move> movesWithJumps;

	public RealPlayer(String name, Color color_player) {
		super(color_player);
		this.name = name;
		
		clear();
	}

	public void setMovesWithJumps(ArrayList<Move> movesWithJumps){
		this.movesWithJumps = movesWithJumps;
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
	
	public void unselectPiece(){
		selectedPiece = null;
	}
	
	public void clear(){
		unselectPiece();
		currentMove = null;
		movesWithJumps = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}
}
