package GameEngine;

import java.awt.Point;
import java.util.ArrayList;

public class CheckerBoard {

	private Piece[][] pieces;
	private ArrayList<Piece> piecesOnBoard, lightCapturedPieces, darkCapturedPieces;
	
	public CheckerBoard() {
		pieces = new Piece[8][8];
		
		int n_lightPieces = 0;
		int n_darkPieces = 0;
		for(int y = 0; y < pieces.length; y++){
			for(int x = 0; x < pieces[y].length; x++){
				if(((y % 2 == 0 && x % 2 == 0) || (y % 2 == 1 && x % 2 == 1)) && y != 3 && y != 4){
					if(n_lightPieces < 12){
						pieces[x][y] = new Piece(x, y,Color.LIGHT);
						n_lightPieces++;
					}
					else if(n_darkPieces < 12){
						pieces[x][y] = new Piece(x, y, Color.DARK);
						n_darkPieces++;
					}
					else pieces[x][y] = null;
				}
				else pieces[x][y] = null;
			}
		}
		
		piecesOnBoard = convertPiecesToArray();
		lightCapturedPieces = new ArrayList<>();
		darkCapturedPieces  = new ArrayList<>();
	}
	
	private ArrayList<Piece> convertPiecesToArray(){
		ArrayList<Piece> temp = new ArrayList<>();
		
		for(int y = 0; y < pieces.length; y++){
			for(int x = 0; x < pieces[y].length; x++){
				if(pieces[y][x] != null) temp.add(pieces[y][x]);
			}
		}
		return temp;
	}
	
	public Piece getPiece(int x, int y){
		return pieces[x][y];
	}
	
	public ArrayList<Piece> getPiecesOnBoard(){
		return piecesOnBoard;
	}
	
	public ArrayList<Piece> getLightCapturedPieces(){
		return lightCapturedPieces;
	}
	
	public ArrayList<Piece> getDarkCapturedPieces(){
		return darkCapturedPieces;
	}
	
	public void executeMove(Move m){
		pieces[m.getSelectedPiece().getX()][m.getSelectedPiece().getY()] = null;
		
		ArrayList<Point> destinationPoints = m.getDestinationPoints();
		
		for(int i = 0; i < destinationPoints.size(); i++){
			pieces[destinationPoints.get(i).x][destinationPoints.get(i).y] = m.getSelectedPiece();
			m.getSelectedPiece().changePosition(destinationPoints.get(i).x, destinationPoints.get(i).y);
			
			if(destinationPoints.size() > 1){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Gui.Gui.repaintScreen();
			}
		}
		
		m.getSelectedPiece().setSelected(false);
	}
}
