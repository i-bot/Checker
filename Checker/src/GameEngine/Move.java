package GameEngine;

import java.awt.Point;
import java.util.ArrayList;

public class Move {

	private Piece selectedPiece;
	private ArrayList<Point> destinationPoints;
	
	public Move(Piece selectedPiece, Point destinationPoint) {
		this.selectedPiece = selectedPiece;
		
		destinationPoints = new ArrayList<>();
		destinationPoints.add(destinationPoint);
	}

	public Move(Piece selectedPiece, ArrayList<Point> destinationPoints) {
		this.selectedPiece = selectedPiece;
		this.destinationPoints = destinationPoints;
	}
	
	public Piece getSelectedPiece(){
		return selectedPiece;
	}
	
	public ArrayList<Point> getDestinationPoints(){
		return destinationPoints;
	}
	
	public Boolean equals(Move m){
		return (selectedPiece == m.getSelectedPiece() && destinationPoints.equals(m.getDestinationPoints()));
	}
}
