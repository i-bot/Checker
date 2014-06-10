package GameEngine.CheckerBoard;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import GameEngine.GameRules.Rule;
import GameEngine.Player.Player;

public class CheckerBoard {

	private Piece[][] pieces;
	private ArrayList<Piece> piecesOnBoard, lightCapturedPieces, darkCapturedPieces;

	//	will later be replaced by inputs from files
	private Dimension d_checkerBoard = new Dimension(8, 8);
	private int max_pieces = 12;

	public CheckerBoard() {
		int y_total = d_checkerBoard.height, x_total = d_checkerBoard.width;
		pieces = new Piece[y_total][x_total];

		int n_lightPieces = 0;
		for(int y = 0; y <= max_pieces / x_total * 2; y++){
			for(int x = 0; x < pieces[y].length; x++){
				if(((y % 2 == 0 && x % 2 == 0) || (y % 2 == 1 && x % 2 == 1)) && n_lightPieces < max_pieces){
					pieces[x][y] = new Piece(x, y, Color.LIGHT);
					n_lightPieces++;
				}
			}
		}

		int n_darkPieces = 0;
		for(int y = y_total - 1; y >= y_total - 1 - (max_pieces / x_total * 2); y--){
			for(int x = 0; x < pieces[y].length; x++){
				if(((y % 2 == 0 && x % 2 == 0) || (y % 2 == 1 && x % 2 == 1)) && n_darkPieces < max_pieces){
					pieces[x][y] = new Piece(x, y, Color.DARK);
					n_darkPieces++;
				}
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

	public Color isGameOver(Rule rule, Color c_currentPlayer){
		Color color_winner = (lightCapturedPieces.size() == max_pieces)? Color.DARK : ((darkCapturedPieces.size() == max_pieces)? Color.LIGHT : null);
		if(rule.getMovesWithJumps(c_currentPlayer).size() == 0 && rule.getNormalMoves(c_currentPlayer).size() == 0) 
			color_winner = (c_currentPlayer == Color.DARK)? Color.LIGHT : Color.DARK;
		return color_winner;
	}

	public Boolean executeMove(Move m, Rule currentRule, Player currentPlayer) throws InterruptedException{
		if(currentRule.checkMove(m)){		
			m.getSelectedPiece().setSelected(false);

			if(!currentPlayer.containsMove(m)){
				Piece pieceToRemove = currentPlayer.getMoveForRemovingPiece().getSelectedPiece();
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
				movePiece(p, destinationPoint, currentRule);
				
				currentRule.updateCurrentCheckerBoard();

				Gui.Gui.repaintScreen();

				if(destinationPoints.size() > 1){
					Thread.sleep(1000);
				}
			}

			return true;
		}

		return false;
	}

	public void executeTestMove(Point start, Point end, Rule currentRule){
		Move m = new Move(pieces[start.x][start.y], end);

		if(currentRule.checkMove(m)){
			Piece p = m.getSelectedPiece();
			ArrayList<Point> destinationPoints = m.getDestinationPoints();

			for(Point destinationPoint : destinationPoints){
				movePiece(p, destinationPoint, currentRule);
			}
		}
	}

	private void movePiece(Piece p, Point destinationPoint, Rule currentRule){
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

		if(currentRule.canBeMadeToKing(p)) p.makeToKing();

		updatePiecesOnBoard();
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