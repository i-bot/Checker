package Engine;

import Game.GameLoop;
import Main.Main;

public class Loader implements Runnable{

	String path;
	
	@Override
	public void run() {
		path = Main.getPath();
		
		AIs.AIs.readAIs(path);
		
		GameLoop gameEngine = new GameLoop();
		Main.setGameLoop(gameEngine);
		
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
