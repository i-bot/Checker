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
	
	public void addDestinationPoint(Point destinationPoint){
		destinationPoints.add(destinationPoint);
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
	
	@SuppressWarnings("unchecked")
	public Move clone(){
		return new Move(selectedPiece.clone(), (ArrayList<Point>) destinationPoints.clone());
	}
	
	public String toString(){
		String s = selectedPiece.getPosition().toString();
		for(Point p : destinationPoints)s += " -> " + p.toString();
		return s;
	}
}
