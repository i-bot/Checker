package Game.CheckerBoard;

import java.awt.Point;

public class Piece{

	private Point position;
	private Boolean selected;
	private Color pieceColor;
	private PieceType pieceType;
	//private BufferedImage pieceIcon, pieceIcon_selected;

	public Piece(int x, int y, Color pieceColor) {
		changePosition(x, y);
		this.pieceColor = pieceColor;

		selected = false;

//		if(pieceColor == Color.LIGHT) {
//			pieceIcon = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Light_Man.png");
//			pieceIcon_selected = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Light_Man_Selected.png");
//		}
//		else if(pieceColor == Color.DARK) {
//			pieceIcon = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Dark_Man.png");
//			pieceIcon_selected = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Dark_Man_Selected.png");
//		}

		pieceType = PieceType.MAN;
	}

	public void changePosition(int x, int y){
		position = new Point(x, y);
	}

	public void changePosition(Point p) {
		position = p;
	}
	
	public void makeToKing(){
		pieceType = PieceType.KING;

//		if(pieceColor == Color.LIGHT) {
//			pieceIcon = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Light_King.png");
//			pieceIcon_selected = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Light_King_Selected.png");
//		}
//		else if(pieceColor == Color.DARK) {
//			pieceIcon = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Dark_King.png");
//			pieceIcon_selected = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Dark_King_Selected.png");
//		}
	}

	public void setSelected(Boolean selected){
		this.selected = selected;
	}

	public Boolean isSelected(){
		return selected;
	}

	public Point getPosition(){
		return position;
	}

	public int getX(){
		return position.x;
	}

	public int getY(){
		return position.y;
	}

	public Color getPieceColor(){
		return pieceColor;
	}

	public PieceType getPieceType(){
		return pieceType;
	}

//	public BufferedImage getPiece_Icon(){
//		return (!selected)? pieceIcon : pieceIcon_selected;
//	}

	public Boolean equals(Piece p){
		return (position.equals(p.getPosition()) && pieceColor == p.getPieceColor() && pieceType == p.getPieceType());
	}
	
	public Piece clone(){
		Piece cloned = new Piece(position.x, position.y, pieceColor);
		if(pieceType == PieceType.KING)
			cloned.makeToKing();
		return cloned;
	}

	public String toString(){
		return (position.toString() + "|" + pieceColor + "|" + pieceType);
	}
	
	public enum PieceType {
		MAN, KING;
	}
}
