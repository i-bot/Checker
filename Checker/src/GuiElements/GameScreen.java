package GuiElements;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import GameEngine.GameEngine;
import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Piece;
import GameEngine.Player.Player;
import Graphics.Images;
import Graphics.Scaler;
import KeyInput.MousePoint;

public class GameScreen extends Button {

	private GameEngine gameEngine;

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

	public void setGameEngine(GameEngine gameEngine){
		this.gameEngine = gameEngine;
	}

	public void update(){
		checkerBoard = gameEngine.getCurrentCheckerBoard();

		lightPlayer_name.setText(gameEngine.getCurrentGame().getPlayer1().getName());
		darkPlayer_name.setText(gameEngine.getCurrentGame().getPlayer2().getName());

		updateCurrentPlayerLabel();
	}

	public void updateCurrentPlayerLabel(){
		if(!gameEngine.isGameOver()){
			Player currentPlayer = gameEngine.getCurrentPlayer();
			currentPlayerLabel.setText("It's your turn " + currentPlayer.getName() + " (" + ((currentPlayer == gameEngine.getCurrentGame().getPlayer1())? "light" : "dark") + ")");
		}
		else currentPlayerLabel.setText(gameEngine.getWinner().getName() + " has won!");
	}

	private void setT_Point(int x, int y){
		t_x = x;
		t_y = y;
	}

	public Boolean isClicked(MousePoint point){
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
			(new Image(t_x + 225 + 100 * p.getX(), t_y + 65 + 100 * p.getY(), p.getPiece_Icon())).drawMe(g);

		for(int y = 0, i = 0; y < 6 && i < checkerBoard.getLightCapturedPieces().size(); y++)
			for(int x = 0; x < 2 && i < checkerBoard.getLightCapturedPieces().size(); x++, i++){
				Piece p = checkerBoard.getLightCapturedPieces().get(i);
				(new Image(t_x + 10 + 100 * x, t_y + 60 + 100 * y, p.getPiece_Icon())).drawMe(g);
			}

		for(int y = 0, i = 0; y < 6 && i < checkerBoard.getDarkCapturedPieces().size(); y++)
			for(int x = 0; x < 2 && i < checkerBoard.getDarkCapturedPieces().size(); x++, i++){
				Piece p = checkerBoard.getDarkCapturedPieces().get(i);
				(new Image(t_x + 1040 + 100 * x, t_y + 60 + 100 * y, p.getPiece_Icon())).drawMe(g);
			}
	}
}
