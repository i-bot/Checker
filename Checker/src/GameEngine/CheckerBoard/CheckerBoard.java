package GameEngine.CheckerBoard;

import java.awt.Point;
import java.util.ArrayList;

import GameEngine.GameEngine;
import GameEngine.GameRules.Rule;

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
						pieces[x][y] = new Piece(x, y, Color.LIGHT);
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
		darkCapturedPieces = new ArrayList<>();
	}

	private CheckerBoard(Piece[][] pieces, ArrayList<Piece> piecesOnBoard, ArrayList<Piece> lightCapturedPieces, ArrayList<Piece> darkCapturedPieces){
		this.pieces = pieces;
		this.piecesOnBoard = piecesOnBoard;
		this.lightCapturedPieces = lightCapturedPieces;
		this.darkCapturedPieces = darkCapturedPieces;
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

	public Piece getPiece(Point p){
		return pieces[p.x][p.y];
	}

	public void updatePiecesOnBoard(){
		piecesOnBoard = convertPiecesToArray();
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

	public Boolean executeMove(Move m){
		Rule currentRule = GameEngine.getCurrentRule();

		if(currentRule.checkMove(m)){
			m.getSelectedPiece().setSelected(false);

			if(!GameEngine.getCurrentPlayer().containsMove(m)){
				Piece pieceToRemove = GameEngine.getCurrentPlayer().getMoveForRemovingPiece().getSelectedPiece();
				pieces[pieceToRemove.getX()][pieceToRemove.getY()] = null;
				if(pieceToRemove.getPieceColor() == Color.LIGHT)
					darkCapturedPieces.add(pieceToRemove);
				if(pieceToRemove.getPieceColor() == Color.DARK)
					lightCapturedPieces.add(pieceToRemove);
				updatePiecesOnBoard();
				currentRule.updateCurrentCheckerBoard();

				if(pieceToRemove.equals(m.getSelectedPiece()))
					return true;
			}

			Piece p = m.getSelectedPiece();
			ArrayList<Point> destinationPoints = m.getDestinationPoints();

			for(Point destinationPoint : destinationPoints){
				if(currentRule.isJump(new Move(p, destinationPoint))){
					Piece capturedPiece = pieces[destinationPoint.x - (destinationPoint.x - p.getX()) / Math.abs(destinationPoint.x - p.getX())] 
							[destinationPoint.y - (destinationPoint.y - p.getY()) / Math.abs(destinationPoint.y - p.getY())];
					if(p.getPieceColor() == Color.LIGHT)
						lightCapturedPieces.add(capturedPiece);
					if(p.getPieceColor() == Color.DARK)
						darkCapturedPieces.add(capturedPiece);
					pieces[capturedPiece.getX()][capturedPiece.getY()] = null;
				}

				pieces[p.getX()][p.getY()] = null;

				pieces[destinationPoint.x][destinationPoint.y] = p;
				p.changePosition(destinationPoint.x, destinationPoint.y);

				updatePiecesOnBoard();
				currentRule.updateCurrentCheckerBoard();

				if(destinationPoints.size() > 1){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Gui.Gui.repaintScreen();
				}
			}

			if(m.getSelectedPiece().getPieceColor() == Color.LIGHT && m.getSelectedPiece().getY() == 7){
				m.getSelectedPiece().makeToKing();
				updatePiecesOnBoard();
				currentRule.updateCurrentCheckerBoard();
			}
			if(m.getSelectedPiece().getPieceColor() == Color.DARK && m.getSelectedPiece().getY() == 0){
				m.getSelectedPiece().makeToKing();
				updatePiecesOnBoard();
				currentRule.updateCurrentCheckerBoard();
			}

			updatePiecesOnBoard();
			currentRule.updateCurrentCheckerBoard();

			return true;
		}

		return false;
	}

	public void executeTestMove(Point start, Point end){
		Rule currentRule = GameEngine.getCurrentRule();
		Move m = new Move(pieces[start.x][start.y], end);

		if(currentRule.checkMove(m)){

			Piece p = m.getSelectedPiece();
			ArrayList<Point> destinationPoints = m.getDestinationPoints();

			for(Point destinationPoint : destinationPoints){
				if(currentRule.isJump(m)){
					Piece capturedPiece = pieces[destinationPoint.x - (destinationPoint.x - p.getX()) / Math.abs(destinationPoint.x - p.getX())] 
							[destinationPoint.y - (destinationPoint.y - p.getY()) / Math.abs(destinationPoint.y - p.getY())];
					if(p.getPieceColor() == Color.LIGHT)
						lightCapturedPieces.add(capturedPiece);
					if(p.getPieceColor() == Color.DARK)
						darkCapturedPieces.add(capturedPiece);
					pieces[capturedPiece.getX()][capturedPiece.getY()] = null;
				}

				pieces[p.getX()][p.getY()] = null;

				pieces[destinationPoint.x][destinationPoint.y] = p;
				p.changePosition(destinationPoint.x, destinationPoint.y);

				updatePiecesOnBoard();
			}
		}

		if(m.getSelectedPiece().getPieceColor() == Color.LIGHT && m.getSelectedPiece().getY() == 7){
			m.getSelectedPiece().makeToKing();
		}
		if(m.getSelectedPiece().getPieceColor() == Color.DARK && m.getSelectedPiece().getY() == 0){
			m.getSelectedPiece().makeToKing();
		}

	}

	public CheckerBoard clone(){
		ArrayList<Piece> clonedPiecesOnBoard = new ArrayList<>();
		for(Piece p : piecesOnBoard)
			clonedPiecesOnBoard.add((p == null)? null : (Piece) p.clone());

		ArrayList<Piece> clonedLightCapturedPieces = new ArrayList<>();
		for(Piece p : lightCapturedPieces)
			clonedLightCapturedPieces.add((p == null)? null : (Piece) p.clone());

		ArrayList<Piece> clonedDarkCapturedPieces = new ArrayList<>();
		for(Piece p : darkCapturedPieces)
			clonedDarkCapturedPieces.add((p == null)? null : (Piece) p.clone());

		Piece[][] t_pieces = new Piece[pieces.length][pieces[0].length];
		for(int x = 0; x < pieces.length; x++)
			for(int y = 0; y < pieces[x].length; y++){
				t_pieces[x][y] = (pieces[x][y] != null)? pieces[x][y].clone() : null;
			}

		return new CheckerBoard(t_pieces, clonedPiecesOnBoard, clonedLightCapturedPieces, clonedDarkCapturedPieces);
	}
}