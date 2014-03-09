package GameEngine;

import java.awt.image.BufferedImage;

public class Piece {

	private int x, y;
	private Boolean selected;
	private Color pieceColor;
	private PieceType pieceType;
	private BufferedImage pieceIcon, pieceIcon_selected;
	
	public Piece(int x, int y, Color pieceColor) {
		changePosition(x, y);
		this.pieceColor = pieceColor;
		
		selected = false;
		
		if(pieceColor == Color.LIGHT) {
			pieceIcon = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Light.png");
			pieceIcon_selected = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Light_Selected.png");
		}
		else if(pieceColor == Color.DARK) {
			pieceIcon = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Dark.png");
			pieceIcon_selected = Graphics.Images.getGraphic("/Graphics/game_images/Piece_Dark_Selected.png");
		}
		
		pieceType = PieceType.MAN;
	}

	public void changePosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void makeToKing(){
		pieceType = PieceType.KING;
	}
	
	public void setSelected(Boolean selected){
		this.selected = selected;
	}
	
	public Boolean isSelected(){
		return selected;
	}
	
	public Integer getX(){
		return x;
	}
	
	public Integer getY(){
		return y;
	}
	
	public Color getPieceColor(){
		return pieceColor;
	}
	
	public PieceType getPieceType(){
		return pieceType;
	}
	
	public BufferedImage getPiece_Icon(){
		return (!selected)? pieceIcon : pieceIcon_selected;
	}
	
	public enum PieceColor {
		DARK, LIGHT;
	}
	
	public enum PieceType {
		MAN, KING;
	}
}
