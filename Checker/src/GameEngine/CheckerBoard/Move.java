package GameEngine.CheckerBoard;

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
		return (selectedPiece.equals(m.getSelectedPiece()) && destinationPoints.equals(m.getDestinationPoints()));
	}
	
	public Boolean equals_player(Move m){
		if(!selectedPiece.equals(m.getSelectedPiece())) return false;
		
		for(int i = 0; i < m.getDestinationPoints().size() && i < destinationPoints.size(); i++)
			if(!m.getDestinationPoints().get(i).equals(destinationPoints.get(i))) return false;
		
		return true;
	}
	
	public Move clone(){
		ArrayList<Point> clonedDestinationPoints = new ArrayList<>();
		for(Point p : destinationPoints)
			clonedDestinationPoints.add((p == null)? null : (Point) p.clone());
		return new Move(selectedPiece.clone(), clonedDestinationPoints);
	}
	
	public String toString(){
		String s = selectedPiece.getPosition().toString();
		for(Point p : destinationPoints)s += " -> " + p.toString();
		return s;
	}
}
