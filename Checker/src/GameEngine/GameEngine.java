package GameEngine;

import java.awt.Point;

import Engine.MenuHandler;
import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Move;
import GameEngine.CheckerBoard.Piece;
import GameEngine.GameRules.DefaultRule;
import GameEngine.GameRules.Rule;
import GameEngine.Player.AIPlayer;
import GameEngine.Player.Player;
import GameEngine.Player.RealPlayer;
import KeyInput.Mouse;
import KeyInput.MousePoint;

public class GameEngine extends Thread{

	private static Game game;
	private static Rule rule;
	private static CheckerBoard checkerBoard;

	public void init(Game game){
		GameEngine.game = game;
		game.changeCurrentPlayer(game.getPlayer1());
		checkerBoard = new CheckerBoard();
		rule = new DefaultRule(checkerBoard);
		if(game.getPlayer1() instanceof AIPlayer && !((AIPlayer) game.getPlayer1()).load()) System.err.println(game.getPlayer1() + " didn't load a valid AI");
		if(game.getPlayer2() instanceof AIPlayer && !((AIPlayer) game.getPlayer2()).load()) System.err.println(game.getPlayer2() + " didn't load a valid AI");	
	}

	public Game getCurrentGame(){
		return game;
	}

	public Rule getCurrentRule(){
		return rule;
	}

	public CheckerBoard getCurrentCheckerBoard(){
		return checkerBoard;
	}

	public Player getCurrentPlayer(){
		return game.getCurrentPlayer(); 
	} 

	private Boolean handleMouseInput(Point p) throws InterruptedException{
		if(getCurrentPlayer() instanceof RealPlayer){
			RealPlayer currentPlayer = (RealPlayer) getCurrentPlayer();
			Piece selectedPiece = checkerBoard.getPiece(p);
			Move currentMove = null;
			if(selectedPiece != null && selectedPiece.getPieceColor() == currentPlayer.getColor_Player() && currentPlayer.canChangeSelectedPiece())
				currentPlayer.handleSelecetedPiece(selectedPiece);
			else if(selectedPiece == null && currentPlayer.hasPieceSelected())
				currentMove = currentPlayer.handleDestinationPoint(p);

			if(currentMove != null){
				Boolean normalMove = rule.isNormalMove(currentMove);
				if(checkerBoard.executeMove(currentMove, rule, currentPlayer)){
					currentPlayer.clear();
					if(rule.canJump(currentMove.getSelectedPiece()) && !normalMove){
						currentPlayer.setSelectedPieceForMultipleJumps(currentMove.getSelectedPiece());
						currentPlayer.setMovesWithJumps(rule.getMovesWithJumps(currentMove.getSelectedPiece()));
					}
					else {	
						return true;
					}
				}
			}
		}

		Gui.Gui.repaintScreen();
		return false;
	} 

	private void getAndExecuteNextMove(Player currentPlayer) throws InterruptedException{
		currentPlayer.clear();
		currentPlayer.setMovesWithJumps(rule.getMovesWithJumps(currentPlayer.getColor_Player()));

		Gui.Gui.repaintScreen();

		if(currentPlayer instanceof RealPlayer){
			Boolean realPlayerExecutedMove = false;

			while(!realPlayerExecutedMove){
				for(MousePoint mp : Mouse.getMouseList())
					if(MenuHandler.getGameMenu().getGameScreen().contains(mp))
						realPlayerExecutedMove = handleMouseInput(MenuHandler.getGameMenu().getGameScreen().convert(mp));

				Mouse.resetMouseList();

				Thread.sleep(50);
			}
			currentPlayer.setMovesWithJumps(rule.getMovesWithJumps(currentPlayer.getColor_Player()));
		}
		else if(currentPlayer instanceof AIPlayer){
			long end = System.currentTimeMillis() + 1000;
			checkerBoard.executeMove(((AIPlayer) currentPlayer).getNextMove(checkerBoard.clone(), rule.clone(), currentPlayer.getColor_Player()), rule, currentPlayer);
			while(System.currentTimeMillis() < end)
				Thread.sleep(1);
			
		}

		game.changeCurrentPlayer((currentPlayer == game.getPlayer1())? game.getPlayer2() : game.getPlayer1());
	}

	@Override
	public void run() {
		Mouse.resetMouseList();

		while(!isInterrupted()){
			try {
				getAndExecuteNextMove(game.getCurrentPlayer());
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
}
