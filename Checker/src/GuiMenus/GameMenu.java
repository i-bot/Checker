package GuiMenus;

import GameEngine.GameEngine;

public class GameMenu extends Menu{

	GameEngine engine;
	
	public GameMenu(){
		engine = new GameEngine();
		Thread gameThread = new Thread(engine);
		gameThread.start();
	}
	
	@Override
	public void createNonStaticContainersAndSubMenus() {
		Gui.Gui.setGameMenu(true);
		
		engine.start();
	}
}