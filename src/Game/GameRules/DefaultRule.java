package Game.GameRules;

import java.awt.Point;
import java.util.ArrayList;

import Game.CheckerBoard.CheckerBoard;
import Game.CheckerBoard.Color;
import Game.CheckerBoard.Move;
import Game.CheckerBoard.Piece;
import Game.CheckerBoard.Piece.PieceType;

public class DefaultRule extends Rule{

	public DefaultRule(CheckerBoard currentCheckerBoard) {
		super(currentCheckerBoard);
	}

	@Override
	public ArrayList<Move> getMovesWithJumps(Color color) {
		ArrayList<Move> movesWithJumps = new ArrayList<>();

		for(Piece currentPiece : currentCheckerBoard.getPiecesOnBoard())
			if(color == currentPiece.getPieceColor())
				movesWithJumps.addAll(getMovesWithJumps(currentPiece));

		return movesWithJumps;
	}

	@Override
	public ArrayList<Move> getMovesWithJumps(Piece currentPiece) {
		return computeMovesWithJumps(currentPiece, currentPiece.getPosition(), new ArrayList<Point>());
	}

	private ArrayList<Move> computeMovesWithJumps(Piece currentPiece, Point currentPosition, ArrayList<Point> destinationPoints){
		ArrayList<Move> movesWithJumps = new ArrayList<>();
		
		if(currentCheckerBoard.getPiece(currentPosition) == null){
			System.out.println("DefaultRule.computeMovesWithJumps()" + currentPosition);
		}
		
		for(ArrayList<Point> t_points : getAllPossiblePositions(currentCheckerBoard.getPiece(currentPosition), 2)){
			@SuppressWarnings("unchecked")
			ArrayList<Point> currentDestinationPoints = (ArrayList<Point>) destinationPoints.clone();
			for(Point t_p : t_points){
				Move m = new Move((destinationPoints.size() == 0)? currentPiece : currentCheckerBoard.getPiece(destinationPoints.get(destinationPoints.size() - 1)), t_p);
				if(isJump(m)){
					currentDestinationPoints.add(t_p);
					DefaultRule t_defaultRule = (DefaultRule) clone(m);
					ArrayList<Move> t_moves = t_defaultRule.computeMovesWithJumps(currentPiece, t_p, currentDestinationPoints);
					if(t_moves.size() > 0)
						movesWithJumps.addAll(t_moves);
					else movesWithJumps.add(new Move(currentPiece, currentDestinationPoints));
					break;
				}
			}
		}

		return movesWithJumps;
	}

	@Override
	public ArrayList<Move> getNormalMoves(Color color) {
		ArrayList<Move> normalMoves = new ArrayList<>();

		for(Piece currentPiece : currentCheckerBoard.getPiecesOnBoard())
			if(color == currentPiece.getPieceColor())
				normalMoves.addAll(getNormalMoves(currentPiece));

		return normalMoves;
	}

	@Override
	public ArrayList<Move> getNormalMoves(Piece currentPiece) {
		return computeNormalMoves(currentPiece);
	}

	private ArrayList<Move> computeNormalMoves(Piece currentPiece){
		ArrayList<Move> normalMoves = new ArrayList<>();

		for(ArrayList<Point> t_points : getAllPossiblePositions(currentPiece, 1))
			for(Point t_p : t_points)
				if(isNormalMove(new Move(currentPiece, t_p)))
					normalMoves.add(new Move(currentPiece, t_p));

		return normalMoves;
	}

	private ArrayList<ArrayList<Point>> getAllPossiblePositions(Piece piece_jump, int space){
		ArrayList<ArrayList<Point>> allPoints = new ArrayList<>();
		ArrayList<Point> points = new ArrayList<>();
		if(piece_jump == null){
			System.out.println("DefaultRule.getAllPossiblePositions()");
		}
		for(Point t_p = new Point(piece_jump.getX() + space, piece_jump.getY() + space); (piece_jump.getPieceColor() == Color.LIGHT || piece_jump.getPieceType() == PieceType.KING) && 
				t_p.x < 8 && t_p.y < 8; t_p = new Point(t_p.x + 1, t_p.y + 1)){
			points.add(t_p);
			if(piece_jump.getPieceType() == PieceType.MAN) break;
		}
		if(points.size() > 0){
			allPoints.add(points);
			points = new ArrayList<>();
		}

		for(Point t_p = new Point(piece_jump.getX() - space, piece_jump.getY() + space); (piece_jump.getPieceColor() == Color.LIGHT || piece_jump.getPieceType() == PieceType.KING) && 
				t_p.x >= 0 && t_p.y < 8; t_p = new Point(t_p.x - 1, t_p.y + 1)){
			points.add(t_p);
			if(piece_jump.getPieceType() == PieceType.MAN) break;
		}
		if(points.size() > 0){
			allPoints.add(points);
			points = new ArrayList<>();
		}


		for(Point t_p = new Point(piece_jump.getX() + space, piece_jump.getY() - space); (piece_jump.getPieceColor() == Color.DARK || piece_jump.getPieceType() == PieceType.KING) && 
				t_p.x < 8 && t_p.y >= 0; t_p = new Point(t_p.x + 1, t_p.y - 1)){
			points.add(t_p);
			if(piece_jump.getPieceType() == PieceType.MAN) break;
		}
		if(points.size() > 0){
			allPoints.add(points);
			points = new ArrayList<>();
		}

		for(Point t_p = new Point(piece_jump.getX() - space, piece_jump.getY() - space); (piece_jump.getPieceColor() == Color.DARK || piece_jump.getPieceType() == PieceType.KING) && 
				t_p.x >= 0 && t_p.y >= 0; t_p = new Point(t_p.x - 1, t_p.y - 1)){
			points.add(t_p);
			if(piece_jump.getPieceType() == PieceType.MAN) break;
		}
		if(points.size() > 0){
			allPoints.add(points);
			points = new ArrayList<>();
		}

		return allPoints;
	}

	@Override
	public Boolean checkMove(Move m) {
		return isJump(m) || isNormalMove(m);
	}

	@Override
	public Boolean canJump(Piece piece) {
		if(piece.getPieceType() == Piece.PieceType.MAN){
			if(piece.getPieceColor() == Color.LIGHT){
				if(isJump_Light_Man(piece.getPosition(), new Point(piece.getX() + 2, piece.getY() + 2)))
					return true;
				if(isJump_Light_Man(piece.getPosition(), new Point(piece.getX() - 2, piece.getY() + 2)))
					return true;
			}
			else if(piece.getPieceColor() == Color.DARK){
				if(isJump_Dark_Man(piece.getPosition(), new Point(piece.getX() + 2, piece.getY() - 2)))
					return true;
				if(isJump_Dark_Man(piece.getPosition(), new Point(piece.getX() - 2, piece.getY() - 2)))
					return true;
			}
		}
		else if(piece.getPieceType() == Piece.PieceType.KING){
			for(Point t_p = new Point(piece.getX() + 2, piece.getY() + 2); t_p.x < 8 && t_p.y < 8; t_p.x++, t_p.y++)
				if(isJump_King(piece.getPosition(), t_p, piece.getPieceColor()))
					return true;

			for(Point t_p = new Point(piece.getX() - 2, piece.getY() + 2); t_p.x >= 0 && t_p.y < 8; t_p.x--, t_p.y++)
				if(isJump_King(piece.getPosition(), t_p, piece.getPieceColor()))
					return true;


			for(Point t_p = new Point(piece.getX() + 2, piece.getY() - 2); t_p.x < 8 && t_p.y >= 0; t_p.x++, t_p.y--)
				if(isJump_King(piece.getPosition(), t_p, piece.getPieceColor()))
					return true;

			for(Point t_p = new Point(piece.getX() - 2, piece.getY() - 2); t_p.x >= 0 && t_p.y >= 0; t_p.x--, t_p.y--)
				if(isJump_King(piece.getPosition(), t_p, piece.getPieceColor()))
					return true;
		}

		return false;
	}

	public Boolean isNormalMove(Move m){
		if(m.getDestinationPoints().size() == 1){
			Point p_start = m.getSelectedPiece().getPosition(); 
			Point p_end = m.getDestinationPoints().get(0);

			if(Math.abs(p_start.x - p_end.x) != Math.abs(p_start.y - p_end.y))
				return false;

			if(m.getSelectedPiece().getPieceType() == Piece.PieceType.MAN){
				if(m.getSelectedPiece().getPieceColor() == Color.LIGHT && isNormalMove_Light_Man(p_start, p_end))
					return true;
				if(m.getSelectedPiece().getPieceColor() == Color.DARK && isNormalMove_Dark_Man(p_start, p_end))
					return true;
			}
			if(m.getSelectedPiece().getPieceType() == Piece.PieceType.KING){
				if(isNormalMove_King(p_start, p_end))
					return true;
			}
		}
		return false;
	}

	public Boolean isJump(Move move){
		Move m = move.clone();
		Point lastPosition = m.getSelectedPiece().getPosition();

		for(Point p : m.getDestinationPoints()){
			if(Math.abs(lastPosition.x - p.x) != Math.abs(lastPosition.y - p.y))
				return false;
			if(m.getSelectedPiece().getPieceType() == Piece.PieceType.MAN){
				if(m.getSelectedPiece().getPieceColor() == Color.LIGHT && !isJump_Light_Man(lastPosition, p))
					return false;
				if(m.getSelectedPiece().getPieceColor() == Color.DARK && !isJump_Dark_Man(lastPosition, p))
					return false;				
			}
			if(m.getSelectedPiece().getPieceType() == Piece.PieceType.KING){
				if(!isJump_King(lastPosition,p, m.getSelectedPiece().getPieceColor()))
					return false;	
			}			
			if(canBeMadeToKing(p, m.getSelectedPiece().getPieceColor())) m.getSelectedPiece().makeToKing();

			lastPosition = p;
		}

		return true;
	}

	public Boolean isJump_Light_Man(Point startPosition, Point endPosition){
		Point p_enemy = new Point(startPosition.x + (endPosition.x - startPosition.x) / 2, startPosition.y + (endPosition.y - startPosition.y) / 2);
		return (endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && 
				Math.abs(endPosition.x - startPosition.x) == 2 && endPosition.y - startPosition.y == 2 && 
				currentCheckerBoard.getPiece(endPosition) == null && currentCheckerBoard.getPiece(p_enemy) != null && 
				currentCheckerBoard.getPiece(p_enemy).getPieceColor() == Color.DARK);
	}

	public Boolean isNormalMove_Light_Man(Point startPosition, Point endPosition){
		return (endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && 
				Math.abs(endPosition.x - startPosition.x) == 1 && endPosition.y - startPosition.y == 1 && 
				currentCheckerBoard.getPiece(endPosition) == null);
	}

	public Boolean isJump_Dark_Man(Point startPosition, Point endPosition){
		Point p_enemy = new Point(startPosition.x + (endPosition.x - startPosition.x) / 2, startPosition.y + (endPosition.y - startPosition.y) / 2);
		return (endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && 
				Math.abs(endPosition.x - startPosition.x) == 2 && endPosition.y - startPosition.y == -2 && 
				currentCheckerBoard.getPiece(endPosition) == null && currentCheckerBoard.getPiece(p_enemy) != null && 
				currentCheckerBoard.getPiece(p_enemy).getPieceColor() == Color.LIGHT);
	}

	public Boolean isNormalMove_Dark_Man(Point startPosition, Point endPosition){
		return (endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && 
				Math.abs(endPosition.x - startPosition.x) == 1 && endPosition.y - startPosition.y == -1 && 
				currentCheckerBoard.getPiece(endPosition) == null);
	}

	public Boolean isJump_King(Point startPosition, Point endPosition, Color c_player){
		if(Math.abs(endPosition.x - startPosition.x) >= 2 && Math.abs(endPosition.y - startPosition.y) >= 2){
			int t_x = (endPosition.x - startPosition.x) / Math.abs(endPosition.x - startPosition.x);
			int t_y = (endPosition.y - startPosition.y) / Math.abs(endPosition.y - startPosition.y);
			Point p_enemy = new Point(endPosition.x - t_x, endPosition.y - t_y);
			Boolean correctJump = true;
			if(currentCheckerBoard.getPiece(endPosition) == null && currentCheckerBoard.getPiece(p_enemy) != null && 
					endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && 
					Math.abs(endPosition.x - startPosition.x) == Math.abs(endPosition.y - startPosition.y) && currentCheckerBoard.getPiece(p_enemy).getPieceColor() != c_player)
				for(int i = 1; correctJump && !p_enemy.equals(new Point(startPosition.x + i * t_x, startPosition.y + i * t_y)); i++)
					correctJump = currentCheckerBoard.getPiece(startPosition.x + i * t_x, startPosition.y + i * t_y) == null;
			else correctJump = false;

			return correctJump;
		}
		else return false;
	}

	public Boolean isNormalMove_King(Point startPosition, Point endPosition){
		if(Math.abs(endPosition.x - startPosition.x) >= 1 && Math.abs(endPosition.y - startPosition.y) >= 1){
			int t_x = (endPosition.x - startPosition.x) / Math.abs(endPosition.x - startPosition.x);
			int t_y = (endPosition.y - startPosition.y) / Math.abs(endPosition.y - startPosition.y);
			Boolean correctNormalMove = true;
			if(endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && 
					Math.abs(endPosition.x - startPosition.x) == Math.abs(endPosition.y - startPosition.y) && currentCheckerBoard.getPiece(endPosition) == null)
				for(int i = 1; correctNormalMove && !endPosition.equals(new Point(startPosition.x + i * t_x, startPosition.y + i * t_y)); i++)
					correctNormalMove = currentCheckerBoard.getPiece(startPosition.x + i * t_x, startPosition.y + i * t_y) == null;
			else correctNormalMove = false;

			return correctNormalMove;
		}
		else return false;
	}

	@Override
	public Boolean canBeMadeToKing(Piece piece) {
		return canBeMadeToKing(piece.getPosition(), piece.getPieceColor());
	}

	private Boolean canBeMadeToKing(Point position, Color color) {
		return ((color == Color.LIGHT && position.getY() == 7) || (color == Color.DARK && position.getY() == 0));
	}

	@Override
	public ArrayList<Move> getOpponentsMovesForJumpingPiece(Piece piece) {
		ArrayList<Move> allOpponentsMoves = getMovesWithJumps(Color.getOpponentsColor(piece.getPieceColor()));
		ArrayList<Move> opponentsMovesForJumpingPiece = new ArrayList<>();
		Point p_piece = piece.getPosition();

		for(Move opponentsMove : allOpponentsMoves){
			currentCheckerBoard.executeMove(opponentsMove, this);
			if(currentCheckerBoard.getPiece(p_piece) == null)
				opponentsMovesForJumpingPiece.add(opponentsMove);
			
			resetCheckerBoard();
		}

		return opponentsMovesForJumpingPiece;
	}

	@Override
	public Integer getDistanceToBorder(Border border, Piece piece) {
		switch (border) {
		case WEST:
			return piece.getX();
		case EAST:
			return 7 - piece.getX();
		case OWN_BASELINE:
			return (piece.getPieceColor() == Color.LIGHT)? piece.getY() : 7 - piece.getY();
		case OPPONENTS_BASELINE:
			return (piece.getPieceColor() == Color.DARK)? piece.getY() : 7 - piece.getY();
		default:
			return -1;
		}
	}

	@Override
	public Rule clone(Move m) {
		CheckerBoard changedCheckerBoard = currentCheckerBoard.clone();
		changedCheckerBoard.executeMove(m, this);
		return new DefaultRule(changedCheckerBoard);
	}	

	public Rule clone(ArrayList<Move> moves) {
		CheckerBoard changedCheckerBoard = currentCheckerBoard.clone();
		changedCheckerBoard.executeMoves(moves, this);
		return new DefaultRule(changedCheckerBoard);
	}

	@Override
	public Rule clone(CheckerBoard changedCheckerBoard) {
		return new DefaultRule(changedCheckerBoard);
	}	

	public DefaultRule clone(){
		resetCheckerBoard();
		return new DefaultRule(currentCheckerBoard.clone());
	}
}
