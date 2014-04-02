package GameEngine;

import java.awt.Point;

import GameRules.DefaultRule;
import GameRules.Rule;

public class GameEngine {

	private static Game game;
	private static Rule rule;
	private static CheckerBoard checkerBoard;

	public static void init(Game game){
		GameEngine.game = game;
		checkerBoard = new CheckerBoard();
		rule = new DefaultRule(checkerBoard);
		changePlayer(game.getPlayer2());
	}
	
	public static void restart(){
		init(game);
	}

	public static Game getCurrentGame(){
		return game;
	}

	public static Rule getCurrentRule(){
		return rule;
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
			if(selectedPiece != null && selectedPiece.getPieceColor() == currentPlayer.getColor_Player() && currentPlayer.canChangeSelectedPiece())
				currentPlayer.handleSelecetedPiece(selectedPiece);
			else if(selectedPiece == null && currentPlayer.hasPieceSelected())
				currentMove = currentPlayer.handleDestinationPoint(new Point(x, y));

			if(currentMove != null){
				Boolean normalMove = rule.isNormalMove(currentMove);
				if(checkerBoard.executeMove(currentMove)){
					currentPlayer.clear();
					if(rule.canJump(currentMove.getSelectedPiece()) && !normalMove){
						currentPlayer.setSelectedPieceForMultipleJumps(currentMove.getSelectedPiece());
						currentPlayer.setMovesWithJumps(rule.getMovesWithJumps(currentMove.getSelectedPiece()));
					}
					else {
						changePlayer(currentPlayer);
					}
				}
			}
		}

		Gui.Gui.repaintScreen();
	} 

	private static void changePlayer(Player currentPlayer){
		game.changeCurrentPlayer((currentPlayer == game.getPlayer1())? game.getPlayer2() : game.getPlayer1());
		if(game.getCurrentPlayer() instanceof RealPlayer){
			System.out.println("GameEngine.changePlayer(): currentPlayer instanceof RealPlayer");

		}
		else if(game.getCurrentPlayer() instanceof AIPlayer){
			System.out.println("GameEngine.changePlayer(): currentPlayer instanceof AIPlayer");
			changePlayer(game.getCurrentPlayer());
		}		

		game.getCurrentPlayer().setMovesWithJumps(rule.getMovesWithJumps(game.getCurrentPlayer().getColor_Player()));
		for(Move m : rule.getMovesWithJumps(game.getCurrentPlayer().getColor_Player()))
			System.out.println("GameEngine.changePlayer(): " + m.toString());
	}
}
