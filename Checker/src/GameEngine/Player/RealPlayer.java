package GameEngine.Player;

import java.awt.Point;
import java.util.ArrayList;

import Engine.MenuHandler;
import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Color;
import GameEngine.CheckerBoard.Move;
import GameEngine.CheckerBoard.Piece;
import GameEngine.GameRules.Rule;
import KeyInput.Mouse;
import KeyInput.MousePoint;

public class RealPlayer extends Player{

	private Piece selectedPiece;
	private Move currentMove;
	private Boolean canChangeSelectedPiece;

	public RealPlayer(String name, Color color_player) {
		super(color_player);
		this.name = name;

		clear();
	}

	public void handleSelecetedPiece(Piece selectedPiece){
		if(this.selectedPiece == null){
			this.selectedPiece = selectedPiece;
			selectedPiece.setSelected(true);
		}
		else if(this.selectedPiece == selectedPiece){
			selectedPiece.setSelected(false);
			unselectPiece();
		}
		else {
			this.selectedPiece.setSelected(false);
			this.selectedPiece = selectedPiece;
			selectedPiece.setSelected(true);
		}
	}

	public Move handleDestinationPoint(Point destinationPoint){
		currentMove = new Move(selectedPiece, destinationPoint);
		return currentMove;
	}

	public Move getCurrentMove(){
		return currentMove;
	}

	public Boolean hasPieceSelected(){
		return selectedPiece != null;
	}

	public Boolean canChangeSelectedPiece(){
		return canChangeSelectedPiece;
	}

	public void setSelectedPieceForMultipleJumps(Piece piece){
		selectedPiece = piece;
		piece.setSelected(true);
		canChangeSelectedPiece = false;
	}

	public void unselectPiece(){
		selectedPiece = null;
		canChangeSelectedPiece = true;
	}

	public void clear(){
		unselectPiece();
		currentMove = null;
		movesWithJumps = new ArrayList<>();
	}

	@Override
	public Boolean containsMove(Move m) {
		for(Move moveFromList : movesWithJumps)
			if(moveFromList.equals_player(m))
				return true;

		return movesWithJumps.size() == 0;
	}

	@Override
	public void executeNextMove(CheckerBoard checkerBoard, Rule rule, Player enemy) throws InterruptedException {
		Boolean executedMove = false;

		while(!executedMove){
			for(MousePoint mp : Mouse.getMouseList())
				if(MenuHandler.getGameMenu().getGameScreen().contains(mp))
					executedMove = handleMouseInput(MenuHandler.getGameMenu().getGameScreen().convert(mp), checkerBoard, rule, enemy);

			Mouse.resetMouseList();

			Thread.sleep(50);
		}		
	}

	private Boolean handleMouseInput(Point p, CheckerBoard checkerBoard, Rule rule, Player enemy) throws InterruptedException{
		Piece clickedPiece = checkerBoard.getPiece(p);
		Move currentMove = null;
		if(clickedPiece != null && clickedPiece.getPieceColor() == color_player && canChangeSelectedPiece())
			handleSelecetedPiece(clickedPiece);
		else if(clickedPiece == null && hasPieceSelected())
			currentMove = handleDestinationPoint(p);

		if(currentMove != null){
			Boolean normalMove = rule.isNormalMove(currentMove);
			if(checkerBoard.executeMove(currentMove, rule, this)){
				clear();
				enemy.handleEnemyMove(currentMove);
				if(rule.canJump(currentMove.getSelectedPiece()) && !normalMove){
					setSelectedPieceForMultipleJumps(currentMove.getSelectedPiece());
					setMovesWithJumps(rule.getMovesWithJumps(currentMove.getSelectedPiece()));
				}
				else {	
					return true;
				}
			}
		}

		Gui.Gui.repaintScreen();
		return false;
	} 

	@Override
	public void handleEnemyMove(Move move_enemy) {
		System.out.println("RealPlayer.handleEnemyMove()");
	}
}
