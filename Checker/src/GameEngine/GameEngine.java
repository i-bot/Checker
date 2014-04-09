package GameEngine;

import java.awt.Point;

import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Move;
import GameEngine.CheckerBoard.Piece;
import GameEngine.GameRules.DefaultRule;
import GameEngine.GameRules.Rule;
import GameEngine.Player.AIPlayer;
import GameEngine.Player.Player;
import GameEngine.Player.RealPlayer;

public class GameEngine {

	private static Game game;
	private static Rule rule;
	private static CheckerBoard checkerBoard;

	public static void init(Game game){
		GameEngine.game = game;
		checkerBoard = new CheckerBoard();
		rule = new DefaultRule(checkerBoard);
		if(game.getPlayer1() instanceof AIPlayer && !((AIPlayer) game.getPlayer1()).load()) System.err.println(game.getPlayer1() + " didn't load a valid AI");
		if(game.getPlayer2() instanceof AIPlayer && !((AIPlayer) game.getPlayer2()).load()) System.err.println(game.getPlayer2() + " didn't load a valid AI");	
	}
	
	public static void restart(){
		init(game);
	}

	public static void start(){
		changePlayer(game.getPlayer2());
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
		currentPlayer = (currentPlayer == game.getPlayer1())? game.getPlayer2() : game.getPlayer1();
		currentPlayer.clear();
		
		game.changeCurrentPlayer(currentPlayer);
		Gui.Gui.repaintScreen();
		
		if(currentPlayer instanceof RealPlayer){			
			currentPlayer.setMovesWithJumps(rule.getMovesWithJumps(currentPlayer.getColor_Player()));
		}
		else if(currentPlayer instanceof AIPlayer){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			checkerBoard.executeMove(((AIPlayer) currentPlayer).getNextMove(checkerBoard.clone(), rule.clone(), currentPlayer.getColor_Player()));
			

			currentPlayer.setMovesWithJumps(rule.getMovesWithJumps(currentPlayer.getColor_Player()));
			
			changePlayer(currentPlayer);
		}		
	}
}
