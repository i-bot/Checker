package GameRules;

import java.awt.Point;
import java.util.ArrayList;

import GameEngine.*;

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
		ArrayList<Move> movesWithJumps = new ArrayList<>();

		if(canJump(currentPiece) && currentPiece.getPieceType() == Piece.PieceType.KING)
			movesWithJumps.addAll(computeMovesWithJumps_King(currentPiece, currentPiece.getPosition(), new ArrayList<Point>()));
		if(canJump(currentPiece) && currentPiece.getPieceType() == Piece.PieceType.MAN){
			if(currentPiece.getPieceColor() == Color.LIGHT)
				movesWithJumps.addAll(computeMovesWithJumps_Light_Man(currentPiece, currentPiece.getPosition(), new ArrayList<Point>()));
			if(currentPiece.getPieceColor() == Color.DARK)
				movesWithJumps.addAll(computeMovesWithJumps_Dark_Man(currentPiece, currentPiece.getPosition(), new ArrayList<Point>()));
		}

		return movesWithJumps;
	}

	private ArrayList<Move> computeMovesWithJumps_Light_Man(Piece currentPiece, Point currentPosition, ArrayList<Point> destinationPoints){
		ArrayList<Move> movesWithJumps = new ArrayList<>();

		@SuppressWarnings("unchecked")
		ArrayList<Point> destinationPointsForRightJumping = (ArrayList<Point>) destinationPoints.clone();
		Point t_point_right = new Point(currentPosition.x + 2, currentPosition.y + 2);
		if(isJump_Light_Man(currentPosition, t_point_right)){
			destinationPointsForRightJumping.add(t_point_right);
			movesWithJumps.add(new Move(currentPiece, destinationPointsForRightJumping));
			movesWithJumps.addAll(computeMovesWithJumps_Light_Man(currentPiece, t_point_right, destinationPointsForRightJumping));
		}

		@SuppressWarnings("unchecked")
		ArrayList<Point> destinationPointsForLeftJumping = (ArrayList<Point>) destinationPoints.clone();
		Point t_point_left = new Point(currentPosition.x - 2, currentPosition.y + 2);
		if(isJump_Light_Man(currentPosition, t_point_left)){
			destinationPointsForLeftJumping.add(t_point_left);
			movesWithJumps.add(new Move(currentPiece, destinationPointsForLeftJumping));
			movesWithJumps.addAll(computeMovesWithJumps_Light_Man(currentPiece, t_point_left, destinationPointsForLeftJumping));
		}

		return movesWithJumps;
	}

	private ArrayList<Move> computeMovesWithJumps_King(Piece currentPiece, Point currentPosition, ArrayList<Point> destinationPoints){
		ArrayList<Move> movesWithJumps = new ArrayList<>();
		CheckerBoard t_board = currentCheckerBoard.clone();

		@SuppressWarnings("unchecked")
		ArrayList<Point> destinationPointsForRightDownJumping = (ArrayList<Point>) destinationPoints.clone();
		for(Point t_p = new Point(currentPosition.x + 2, currentPosition.y + 2); t_p.x < 8 && t_p.y < 8; t_p.x++, t_p.y++)
			if(isJump_King(currentPosition, t_p)){
				destinationPointsForRightDownJumping.add(t_p);
				movesWithJumps.add(new Move(currentPiece, destinationPointsForRightDownJumping));
				currentCheckerBoard.executeTestMove((destinationPoints.size() == 0)? currentPiece.getPosition() : destinationPoints.get(destinationPoints.size() - 1), t_p);
				movesWithJumps.addAll(computeMovesWithJumps_King(currentPiece, t_p, destinationPointsForRightDownJumping));
				break;
			}
		currentCheckerBoard = t_board.clone();

		@SuppressWarnings("unchecked")
		ArrayList<Point> destinationPointsForLeftDownJumping = (ArrayList<Point>) destinationPoints.clone();
		for(Point t_p = new Point(currentPosition.x - 2, currentPosition.y + 2); t_p.x >= 0 && t_p.y < 8; t_p.x--, t_p.y++)
			if(isJump_King(currentPosition, t_p)){
				destinationPointsForLeftDownJumping.add(t_p);
				movesWithJumps.add(new Move(currentPiece, destinationPointsForLeftDownJumping));
				currentCheckerBoard.executeTestMove((destinationPoints.size() == 0)? currentPiece.getPosition() : destinationPoints.get(destinationPoints.size() - 1), t_p);
				movesWithJumps.addAll(computeMovesWithJumps_King(currentPiece, t_p, destinationPointsForLeftDownJumping));
				break;
			}
		currentCheckerBoard = t_board.clone();


		@SuppressWarnings("unchecked")
		ArrayList<Point> destinationPointsForRightUpJumping = (ArrayList<Point>) destinationPoints.clone();
		for(Point t_p = new Point(currentPosition.x + 2, currentPosition.y - 2); t_p.x < 8 && t_p.y >= 0; t_p.x++, t_p.y--)
			if(isJump_King(currentPosition, t_p)){
				destinationPointsForRightUpJumping.add(t_p);
				movesWithJumps.add(new Move(currentPiece, destinationPointsForRightUpJumping));
				currentCheckerBoard.executeTestMove((destinationPoints.size() == 0)? currentPiece.getPosition() : destinationPoints.get(destinationPoints.size() - 1), t_p);
				movesWithJumps.addAll(computeMovesWithJumps_King(currentPiece, t_p, destinationPointsForRightUpJumping));
				break;
			}
		currentCheckerBoard = t_board.clone();

		@SuppressWarnings("unchecked")
		ArrayList<Point> destinationPointsForLeftUpJumping = (ArrayList<Point>) destinationPoints.clone();
		for(Point t_p = new Point(currentPosition.x - 2, currentPosition.y - 2); t_p.x >= 0 && t_p.y >= 0; t_p.x--, t_p.y--)
			if(isJump_King(currentPosition, t_p)){
				destinationPointsForLeftUpJumping.add(t_p);
				movesWithJumps.add(new Move(currentPiece, destinationPointsForLeftUpJumping));
				currentCheckerBoard.executeTestMove((destinationPoints.size() == 0)? currentPiece.getPosition() : destinationPoints.get(destinationPoints.size() - 1), t_p);
				movesWithJumps.addAll(computeMovesWithJumps_King(currentPiece, t_p, destinationPointsForLeftUpJumping));
				break;
			}
		currentCheckerBoard = t_board.clone();

		return movesWithJumps;
	}

	private ArrayList<Move> computeMovesWithJumps_Dark_Man(Piece currentPiece, Point currentPosition, ArrayList<Point> destinationPoints){
		ArrayList<Move> movesWithJumps = new ArrayList<>();

		@SuppressWarnings("unchecked")
		ArrayList<Point> destinationPointsForRightJumping = (ArrayList<Point>) destinationPoints.clone();
		Point t_point_right = new Point(currentPosition.x + 2, currentPosition.y - 2);
		if(isJump_Dark_Man(currentPosition, t_point_right)){
			destinationPointsForRightJumping.add(t_point_right);
			movesWithJumps.add(new Move(currentPiece, destinationPointsForRightJumping));
			movesWithJumps.addAll(computeMovesWithJumps_Dark_Man(currentPiece, t_point_right, destinationPointsForRightJumping));
		}

		@SuppressWarnings("unchecked")
		ArrayList<Point> destinationPointsForLeftJumping = (ArrayList<Point>) destinationPoints.clone();
		Point t_point_left = new Point(currentPosition.x - 2, currentPosition.y - 2);
		if(isJump_Dark_Man(currentPosition, t_point_left)){
			destinationPointsForLeftJumping.add(t_point_left);
			movesWithJumps.add(new Move(currentPiece, destinationPointsForLeftJumping));
			movesWithJumps.addAll(computeMovesWithJumps_Dark_Man(currentPiece, t_point_left, destinationPointsForLeftJumping));
		}

		return movesWithJumps;
	}

	@Override
	public Boolean checkMove(Player currentPlayer, Move m) {
		return (m.getSelectedPiece().getPieceColor() == Color.LIGHT)? checkMoveForLightPiece(m) : checkMoveForDarkPiece(m);
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
			if(piece.getPieceColor() == Color.LIGHT){
				for(Point t_p = new Point(piece.getX() + 2, piece.getY() + 2); t_p.x < 8 && t_p.y < 8; t_p.x++, t_p.y++)
					if(isJump_King(piece.getPosition(), t_p))
						return true;

				for(Point t_p = new Point(piece.getX() - 2, piece.getY() + 2); t_p.x >= 0 && t_p.y < 8; t_p.x--, t_p.y++)
					if(isJump_King(piece.getPosition(), t_p))
						return true;


				for(Point t_p = new Point(piece.getX() + 2, piece.getY() - 2); t_p.x < 8 && t_p.y >= 0; t_p.x++, t_p.y--)
					if(isJump_King(piece.getPosition(), t_p))
						return true;

				for(Point t_p = new Point(piece.getX() - 2, piece.getY() - 2); t_p.x >= 0 && t_p.y >= 0; t_p.x--, t_p.y--)
					if(isJump_King(piece.getPosition(), t_p))
						return true;

			}
		}

		return false;
	}

	public Boolean isNormalMove(Move m){
		if(m.getSelectedPiece().getPieceType() == Piece.PieceType.MAN && m.getDestinationPoints().size() == 1){
			if(m.getSelectedPiece().getPieceColor() == Color.LIGHT && isNormalMove_Light_Man(m.getSelectedPiece().getPosition(), m.getDestinationPoints().get(0)))
				return true;
			if(m.getSelectedPiece().getPieceColor() == Color.DARK && isNormalMove_Dark_Man(m.getSelectedPiece().getPosition(), m.getDestinationPoints().get(0)))
				return true;
		}
		if(m.getSelectedPiece().getPieceType() == Piece.PieceType.KING && m.getDestinationPoints().size() == 1){
			if(isNormalMove_King(m.getSelectedPiece().getPosition(), m.getDestinationPoints().get(0)))
				return true;
		}

		return false;
	}

	public Boolean isJump(Move m){
		Point lastPosition = m.getSelectedPiece().getPosition();
		for(int i = 0; i < m.getDestinationPoints().size(); i++){
			if(m.getSelectedPiece().getPieceType() == Piece.PieceType.MAN){
				if(m.getSelectedPiece().getPieceColor() == Color.LIGHT && !isJump_Light_Man(lastPosition, m.getDestinationPoints().get(i)))
					return false;
				if(m.getSelectedPiece().getPieceColor() == Color.DARK && !isJump_Dark_Man(lastPosition, m.getDestinationPoints().get(i)))
					return false;
			}
			if(m.getSelectedPiece().getPieceType() == Piece.PieceType.KING){
				if(!isJump_King(lastPosition, m.getDestinationPoints().get(i)))
					return false;
				lastPosition = m.getDestinationPoints().get(i);
			}
		}

		return true;
	}

	private Boolean checkMoveForLightPiece(Move m){
		return (m.getSelectedPiece().getPieceType() == Piece.PieceType.MAN)? checkMoveForLightAndManPiece(m) : checkMoveForKingPiece(m);
	}

	private Boolean checkMoveForLightAndManPiece(Move m){
		Point lastPosition = m.getSelectedPiece().getPosition();
		for(int i = 0; i < m.getDestinationPoints().size(); i++){
			if(m.getDestinationPoints().get(i).y < lastPosition.y)
				return false;
			else if(Math.abs(lastPosition.x - m.getDestinationPoints().get(i).x) != Math.abs(lastPosition.y - m.getDestinationPoints().get(i).y))
				return false;
			else if(m.getDestinationPoints().size() > 1 && !isJump_Light_Man(lastPosition, m.getDestinationPoints().get(i)) && isNormalMove_Light_Man(lastPosition, m.getDestinationPoints().get(i)))
				return false;
			else if(m.getDestinationPoints().size() == 1 && !isJump_Light_Man(lastPosition, m.getDestinationPoints().get(i)) && !isNormalMove_Light_Man(lastPosition, m.getDestinationPoints().get(i)))
				return false;
			else lastPosition = m.getDestinationPoints().get(i);
		}

		return true;
	}

	public Boolean isJump_Light_Man(Point startPosition, Point endPosition){
		return (endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && Math.abs(endPosition.x - startPosition.x) == 2 && Math.abs(endPosition.y - startPosition.y) == 2 && currentCheckerBoard.getPiece(endPosition) == null && currentCheckerBoard.getPiece(startPosition.x + (endPosition.x - startPosition.x) / 2, startPosition.y + (endPosition.y - startPosition.y) / 2) != null && currentCheckerBoard.getPiece(startPosition.x + (endPosition.x - startPosition.x) / 2, startPosition.y + (endPosition.y - startPosition.y) / 2).getPieceColor() == Color.DARK);
	}

	public Boolean isNormalMove_Light_Man(Point startPosition, Point endPosition){
		return (endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && Math.abs(endPosition.x - startPosition.x) == 1 && Math.abs(endPosition.y - startPosition.y) == 1 && currentCheckerBoard.getPiece(endPosition) == null);
	}

	private Boolean checkMoveForDarkPiece(Move m){
		return (m.getSelectedPiece().getPieceType() == Piece.PieceType.MAN)? checkMoveForDarkAndManPiece(m) : checkMoveForKingPiece(m);
	}

	private Boolean checkMoveForDarkAndManPiece(Move m){
		Point lastPosition = m.getSelectedPiece().getPosition();
		for(int i = 0; i < m.getDestinationPoints().size(); i++){
			if(m.getDestinationPoints().get(i).y > lastPosition.y)
				return false;
			else if(Math.abs(lastPosition.x - m.getDestinationPoints().get(i).x) != Math.abs(lastPosition.y - m.getDestinationPoints().get(i).y))
				return false;
			else if(m.getDestinationPoints().size() > 1 && !isJump_Dark_Man(lastPosition, m.getDestinationPoints().get(i)) && isNormalMove_Dark_Man(lastPosition, m.getDestinationPoints().get(i)))
				return false;
			else if(m.getDestinationPoints().size() == 1 && !isJump_Dark_Man(lastPosition, m.getDestinationPoints().get(i)) && !isNormalMove_Dark_Man(lastPosition, m.getDestinationPoints().get(i)))
				return false;
			else lastPosition = m.getDestinationPoints().get(i);
		}

		return true;
	}

	public Boolean isJump_Dark_Man(Point startPosition, Point endPosition){
		return (endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && Math.abs(endPosition.x - startPosition.x) == 2 && Math.abs(endPosition.y - startPosition.y) == 2 && currentCheckerBoard.getPiece(endPosition) == null && currentCheckerBoard.getPiece(startPosition.x + (endPosition.x - startPosition.x) / 2, startPosition.y + (endPosition.y - startPosition.y) / 2) != null && currentCheckerBoard.getPiece(startPosition.x + (endPosition.x - startPosition.x) / 2, startPosition.y + (endPosition.y - startPosition.y) / 2).getPieceColor() == Color.LIGHT);
	}

	public Boolean isNormalMove_Dark_Man(Point startPosition, Point endPosition){
		return (endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && Math.abs(endPosition.x - startPosition.x) == 1 && Math.abs(endPosition.y - startPosition.y) == 1 && currentCheckerBoard.getPiece(endPosition) == null);
	}

	private Boolean checkMoveForKingPiece(Move m){
		Point lastPosition = m.getSelectedPiece().getPosition();
		for(int i = 0; i < m.getDestinationPoints().size(); i++){
			if(Math.abs(lastPosition.x - m.getDestinationPoints().get(i).x) != Math.abs(lastPosition.y - m.getDestinationPoints().get(i).y))
				return false;
			else if(m.getDestinationPoints().size() > 1 && !isJump_King(lastPosition, m.getDestinationPoints().get(i)) && isNormalMove_King(lastPosition, m.getDestinationPoints().get(i)))
				return false;
			else if(m.getDestinationPoints().size() == 1 && !isJump_King(lastPosition, m.getDestinationPoints().get(i)) && !isNormalMove_King(lastPosition, m.getDestinationPoints().get(i)))
				return false;
			else lastPosition = m.getDestinationPoints().get(i);
		}

		return true;
	}

	public Boolean isJump_King(Point startPosition, Point endPosition){
		if(Math.abs(endPosition.x - startPosition.x) >= 2 && Math.abs(endPosition.y - startPosition.y) >= 2){
			int t_x = (endPosition.x - startPosition.x) / Math.abs(endPosition.x - startPosition.x);
			int t_y = (endPosition.y - startPosition.y) / Math.abs(endPosition.y - startPosition.y);
			Point p_enemy = new Point(endPosition.x - t_x, endPosition.y - t_y);
			Boolean correctJump = true;
			if(endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && Math.abs(endPosition.x - startPosition.x) == Math.abs(endPosition.y - startPosition.y) && currentCheckerBoard.getPiece(endPosition) == null && currentCheckerBoard.getPiece(p_enemy) != null && currentCheckerBoard.getPiece(p_enemy).getPieceColor() == Color.DARK)
				for(int i = 1; correctJump && !p_enemy.equals(new Point(startPosition.x + i * t_x, startPosition.y + i * t_y)); i++)
					correctJump = currentCheckerBoard.getPiece(startPosition.x + i * t_x, startPosition.y + i * t_y) == null;
			else correctJump = false;
			return correctJump;
		}
		else return false;
	}

	public Boolean isNormalMove_King(Point startPosition, Point endPosition){
		if(Math.abs(endPosition.x - startPosition.x) >= 2 && Math.abs(endPosition.y - startPosition.y) >= 2){
			int t_x = (endPosition.x - startPosition.x) / Math.abs(endPosition.x - startPosition.x);
			int t_y = (endPosition.y - startPosition.y) / Math.abs(endPosition.y - startPosition.y);
			Boolean correctNormalMove = true;
			if(endPosition.x >= 0 && endPosition.x < 8 && endPosition.y >= 0 && endPosition.y < 8 && Math.abs(endPosition.x - startPosition.x) == Math.abs(endPosition.y - startPosition.y) && currentCheckerBoard.getPiece(endPosition) == null)
				for(int i = 1; correctNormalMove && !endPosition.equals(new Point(startPosition.x + i * t_x, startPosition.y + i * t_y)); i++)
					correctNormalMove = currentCheckerBoard.getPiece(startPosition.x + i * t_x, startPosition.y + i * t_y) == null;
			else correctNormalMove = false;
			return correctNormalMove;
		}
		else return false;
	}
}

