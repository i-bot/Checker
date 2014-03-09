package GameEngine;

import java.awt.Point;

public class GameEngine {

	private static Game game;
	private static CheckerBoard checkerBoard;

	public static void init(Game game){
		GameEngine.game = game;
		checkerBoard = new CheckerBoard();
	}
	
	public static Game getCurrentGame(){
		return game;
	}
	
	public static CheckerBoard getCurrentCheckerBoard(){
		return checkerBoard;
	}
	
	public static Player getCurrentPlayer(){
		return game.getCurrentPlayer(); 
	} 
	
	public static void handleMouseInput(int x, int y){
		if(getCurrentPlayer() instanceof RealPlayer){
			RealPlayer currentPlayer = (RealPlayer) getCurrentPlayer();
			Piece selectedPiece = checkerBoard.getPiece(x, y);
			Move currentMove = null;
			if(selectedPiece != null && selectedPiece.getPieceColor() == currentPlayer.getColor_Player())
				currentPlayer.handleSelecetedPiece(selectedPiece);
			else if(selectedPiece == null && currentPlayer.hasPieceSelected())
				currentMove = currentPlayer.handleDestinationPoint(new Point(x, y));
			
			if(currentMove != null)
				checkerBoard.executeMove(currentMove);
		}
		
		Gui.Gui.repaintScreen();
	} 
}
