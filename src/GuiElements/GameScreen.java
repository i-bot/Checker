package GuiElements;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.GameLoop;
import Game.CheckerBoard.CheckerBoard;
import Game.CheckerBoard.Color;
import Game.CheckerBoard.Piece;
import Game.CheckerBoard.Piece.PieceType;
import Game.Player.Player;
import Graphics.Images;
import Graphics.Scaler;
import KeyInput.MousePoint;

public class GameScreen extends Button {

	private GameLoop gameLoop;

	private int t_x, t_y;
	private Rectangle board;
	private CheckerBoard checkerBoard;
	private ArrayList<GuiElement> guiElements = new ArrayList<>();
	private Label lightPlayer_name, darkPlayer_name, currentPlayerLabel;

	private GameScreen(int id, String title, TitleOrientation orientation) {
		super(id, title, orientation);
	}

	public GameScreen(int id, String title, TitleOrientation orientation, int x, int y, int width, int height) {
		super(id, title, orientation, x, y, width, height);
		setT_Point(x, y);

		board = new Rectangle(Scaler.scale(x + 220), Scaler.scale(y + 60), Scaler.scale(800), Scaler.scale(800));

		lightPlayer_name = new Label("", TitleOrientation.MID, t_x + 15, t_y + 10, 180, 30);
		darkPlayer_name = new Label("", TitleOrientation.MID, t_x + 1045, t_y + 10, 180, 30);
		currentPlayerLabel = new Label("", TitleOrientation.MID, t_x + 235, t_y + 10, 770, 30);

		darkPlayer_name = new Label("", TitleOrientation.MID, t_x + 1045, t_y + 10, 180, 30);

		guiElements.add(new Image(t_x + 220, t_y + 60, Images.getGraphic("/Graphics/game_images/Default_Board.png")));

		guiElements.add(new Image(t_x, t_y + 50, Images.getGraphic("/Graphics/game_images/Piece_Captured_Background_8x8.png")));
		guiElements.add(new Image(t_x + 1030, t_y + 50, Images.getGraphic("/Graphics/game_images/Piece_Captured_Background_8x8.png")));

		guiElements.add(lightPlayer_name);
		guiElements.add(darkPlayer_name);
		guiElements.add(currentPlayerLabel);
	}

	public void setGameLoop(GameLoop gameLoop){
		this.gameLoop = gameLoop;
	}

	public void update(){
		checkerBoard = gameLoop.getCurrentCheckerBoard();

		lightPlayer_name.setText(gameLoop.getCurrentGame().getLightPlayer().getName());
		darkPlayer_name.setText(gameLoop.getCurrentGame().getDarkPlayer().getName());

		updateCurrentPlayerLabel();
	}

	public void updateCurrentPlayerLabel(){
		if(!gameLoop.isGameOver()){
			Player currentPlayer = gameLoop.getCurrentPlayer();
			currentPlayerLabel.setText("It's your turn " + currentPlayer.getName() + " (" + ((currentPlayer.getColor().toString().toLowerCase())) + ")");
		}
		else currentPlayerLabel.setText(gameLoop.getWinner().getName() + " has won!");
	}

	private void setT_Point(int x, int y){
		t_x = x;
		t_y = y;
	}

	public Boolean isClicked(MousePoint point){
		Point p = new Point(point.getX(), point.getY());
		if(board.contains(p)){
			gameLoop.addClickedFieldToCheckerBoard(new Point((p.x - board.x) / Scaler.scale(100), (p.y - board.y) / Scaler.scale(100)));
			return true;
		}
		return false;
	}

	public Point convert(MousePoint point){
		return new Point((point.getX() - board.x) / Scaler.scale(100), (point.getY() - board.y) / Scaler.scale(100));
	}

	public Boolean contains(MousePoint point){
		return board.contains(point.toPoint());
	}

	@Override
	public void drawMe(Graphics g) {	
		
		for(GuiElement ge : guiElements) ge.drawMe(g);

		for(Piece p : checkerBoard.getPiecesOnBoard()) 
			(new Image(t_x + 225 + 100 * p.getX(), t_y + 65 + 100 * p.getY(), get_Icon(p))).drawMe(g);

		ArrayList<Piece> lightCapturedPieces = checkerBoard.getCapturedPieces(Color.LIGHT);

		for(int y = 0, i = 0; y < 6 && i < lightCapturedPieces.size(); y++)
			for(int x = 0; x < 2 && i < lightCapturedPieces.size(); x++, i++){
				Piece p = lightCapturedPieces.get(i);
				(new Image(t_x + 10 + 100 * x, t_y + 60 + 100 * y, get_Icon(p))).drawMe(g);
			}

		ArrayList<Piece> darkCapturedPieces = checkerBoard.getCapturedPieces(Color.DARK);
		
		for(int y = 0, i = 0; y < 6 && i < darkCapturedPieces.size(); y++)
			for(int x = 0; x < 2 && i < darkCapturedPieces.size(); x++, i++){
				Piece p = darkCapturedPieces.get(i);
				(new Image(t_x + 1040 + 100 * x, t_y + 60 + 100 * y, get_Icon(p))).drawMe(g);
			}
	}
	
	public BufferedImage get_Icon(Piece p)
	{
		if(p.getPieceColor() == Color.LIGHT) {
			if(p.isSelected())
			{
				if(p.getPieceType() == PieceType.MAN)
					return Images.getGraphic("/Graphics/game_images/Piece_Light_Man_Selected.png");
				else
					return Images.getGraphic("/Graphics/game_images/Piece_Light_King_Selected.png");
			}
			else
			{
				if(p.getPieceType() == PieceType.MAN)
					return Images.getGraphic("/Graphics/game_images/Piece_Light_Man.png");
				else
					return Images.getGraphic("/Graphics/game_images/Piece_Light_King.png");
			}
		}
		else if(p.getPieceColor() == Color.DARK) {
			if(p.isSelected())
			{
				if(p.getPieceType() == PieceType.MAN)
					return Images.getGraphic("/Graphics/game_images/Piece_Dark_Man_Selected.png");
				else
					return Images.getGraphic("/Graphics/game_images/Piece_Dark_King_Selected.png");
			}
			else
			{
				if(p.getPieceType() == PieceType.MAN)
					return Images.getGraphic("/Graphics/game_images/Piece_Dark_Man.png");
				else
					return Images.getGraphic("/Graphics/game_images/Piece_Dark_King.png");
			}
		}
		
		return null;
	}
}
