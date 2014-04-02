package GuiMenus;

import Engine.MenuHandler;
import GameEngine.GameEngine;
import GuiElements.Button;
import GuiElements.ButtonListener;
import GuiElements.GameScreen;
import GuiElements.Image;
import GuiElements.NormalButton;
import GuiElements.TitleOrientation;
import Languages.Languages;

public class GameMenu extends Menu{	

	GameScreen gameScreen;

	public GameMenu(){
		addButtonListener(new GameButtonListener());

		add(new Image(0, 0, Graphics.Images.getGraphic("/Graphics/menu_background/Menu_Background_Game.png")));

		add(new NormalButton(3, Languages.getButtonAndMenuTitle(3), TitleOrientation.MID, 35, 35, 270, 50, 255, 255, 0));

		add(new NormalButton(4, Languages.getButtonAndMenuTitle(4), TitleOrientation.MID, 35, 360, 270, 50, 255, 255, 0));
		add(new NormalButton(20, Languages.getButtonAndMenuTitle(20), TitleOrientation.MID, 35, 425, 270, 50, 255, 255, 0));
		add(new NormalButton(5, Languages.getButtonAndMenuTitle(5), TitleOrientation.MID, 35, 490, 270, 50, 255, 255, 0));

		add(new NormalButton(1, Languages.getButtonAndMenuTitle(1), TitleOrientation.MID, 35, 750, 270, 50, 255, 255, 0));
		add(new NormalButton(0, Languages.getButtonAndMenuTitle(0), TitleOrientation.MID, 35, 815, 270, 50, 255, 255, 0));

		gameScreen = new GameScreen(19, null, null, 340, 20, 1240, 860);
		add(gameScreen);
	}

	@Override
	public void createNonStaticContainersAndSubMenus() {
		gameScreen.update(GameEngine.getCurrentCheckerBoard());
	}

	private class GameButtonListener extends ButtonListener{

		@Override
		public void clicked(Button button){
			if(button instanceof NormalButton){	
				if(button.getID() == 20)
					GameEngine.restart();
				MenuHandler.changeMenu(button.getID());
			}
		}
	}
}