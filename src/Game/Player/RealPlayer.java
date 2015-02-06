package Game.Player;

import java.awt.Point;
import java.util.ArrayList;

import Game.CheckerBoard.CheckerBoard;
import Game.CheckerBoard.Color;
import Game.CheckerBoard.Move;
import Game.CheckerBoard.Piece;
import Game.GameRules.Rule;

public class RealPlayer extends Player{

	private Piece selectedPiece;
	private Move t_Move;
	private Boolean canChangeSelectedPiece;

	public RealPlayer(String name, Color color_player) {
		super(color_player);
		this.name = name;
		t_Move = null;

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
		return new Move(selectedPiece, destinationPoint);
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
		movesWithJumps = new ArrayList<>();
	}

	@Override
	public Boolean containsMove(Move m) {
		for(Move moveFromList : movesWithJumps){
			if(checkEqualMovesForRealPlayer(moveFromList, m))
				return true;
		}
		
		return movesWithJumps.size() == 0;
	}

	public Boolean checkEqualMovesForRealPlayer(Move wholeMove, Move partMove){
		ArrayList<Point> PointsOfWholeMove = new ArrayList<>();
		PointsOfWholeMove.add(wholeMove.getSelectedPiece().getPosition());
		PointsOfWholeMove.addAll(wholeMove.getDestinationPoints());
		
		ArrayList<Point> PointsOfPartMove = new ArrayList<>();
		PointsOfPartMove.add(partMove.getSelectedPiece().getPosition());
		PointsOfPartMove.addAll(partMove.getDestinationPoints());
				
		return PointsOfWholeMove.containsAll(PointsOfPartMove);
	}
	
	@Override
	public Move getAndExecuteNextMove(CheckerBoard checkerBoard, Rule rule) throws InterruptedException {
		t_Move = null;
		Boolean finished = false;

		while(!finished){
			for(Point p : checkerBoard.getClickedFields())
					finished = handleMouseInput(p, checkerBoard, rule);

			checkerBoard.resetClickedFields();

			Thread.sleep(50);
		}

		return t_Move;
	}

	private Boolean handleMouseInput(Point p, CheckerBoard checkerBoard, Rule rule) throws InterruptedException{
		Piece clickedPiece = checkerBoard.getPiece(p);
		Move currentMove = null;
		if(clickedPiece != null && clickedPiece.getPieceColor() == getColor() && canChangeSelectedPiece())
			handleSelecetedPiece(clickedPiece);
		else if(clickedPiece == null && hasPieceSelected())
			currentMove = handleDestinationPoint(p);

		if(currentMove != null && rule.checkMove(currentMove)){
			Boolean normalMove = rule.isNormalMove(currentMove);
			
			if(t_Move == null) t_Move = currentMove.clone();
			else t_Move.getDestinationPoints().add(currentMove.getDestinationPoints().get(currentMove.getDestinationPoints().size() - 1));
			
			checkerBoard.executeMoveWithRepainting(currentMove, rule, this);
			clear();

			if(rule.canJump(currentMove.getSelectedPiece()) && !normalMove){
				setSelectedPieceForMultipleJumps(currentMove.getSelectedPiece());
				setMovesWithJumps(rule.getMovesWithJumps(currentMove.getSelectedPiece()));
			}
			else return true;
		}

		return false;
	} 

	@Override
	public void handleEnemyMove(Move move_enemy) {
		System.out.println("RealPlayer.handleEnemyMove(): Enemy made this move: " + move_enemy);
	}
}
