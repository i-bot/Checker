package GuiMenus;

import Game.Game;
import Game.GameLoop;
import GuiElements.Button;
import GuiElements.ButtonListener;
import GuiElements.GameScreen;
import GuiElements.Image;
import GuiElements.NormalButton;
import GuiElements.TitleOrientation;
import Languages.Languages;

public class GameMenu extends Menu{	

	private GameScreen gameScreen;
	private GameLoop gameLoop;

	public GameMenu(GameLoop gameLoop){
		this.gameLoop = gameLoop;
		
		addButtonListener(new GameButtonListener());

		add(new Image(0, 0, Graphics.Images.getGraphic("/Graphics/menu_background/Menu_Background_Game.png")));

		add(new NormalButton(3, Languages.getButtonAndMenuTitle(3), TitleOrientation.MID, 35, 35, 270, 50, 255, 255, 0));

		add(new NormalButton(4, Languages.getButtonAndMenuTitle(4), TitleOrientation.MID, 35, 360, 270, 50, 255, 255, 0));
		add(new NormalButton(20, Languages.getButtonAndMenuTitle(20), TitleOrientation.MID, 35, 425, 270, 50, 255, 255, 0));
		add(new NormalButton(5, Languages.getButtonAndMenuTitle(5), TitleOrientation.MID, 35, 490, 270, 50, 255, 255, 0));

		add(new NormalButton(1, Languages.getButtonAndMenuTitle(1), TitleOrientation.MID, 35, 750, 270, 50, 255, 255, 0));
		add(new NormalButton(0, Languages.getButtonAndMenuTitle(0), TitleOrientation.MID, 35, 815, 270, 50, 255, 255, 0));

		gameScreen = new GameScreen(19, null, null, 340, 20, 1240, 860);
		gameScreen.setGameLoop(gameLoop);
		add(gameScreen);
	}
	
	public GameScreen getGameScreen(){
		return gameScreen;
	}

	@Override
	public void createNonStaticContainersAndSubMenus() {
		gameScreen.update();
	}

	private class GameButtonListener extends ButtonListener{

		@Override
		public void clicked(Button button){
			if(button instanceof NormalButton){	
				Game currentGame = gameLoop.getCurrentGame();
				gameLoop = Main.Main.reloadGameLoop();
				
				if(button.getID() == 20){
					gameLoop.init(currentGame);
					gameScreen.setGameLoop(gameLoop);
					gameLoop.start();
				}
				changeMenu(button.getID());
			}
		}
	}
}