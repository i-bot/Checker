package GameEngine;

import java.util.ArrayList;

import BaseEntities.BaseEntity;

public class GameEngine implements Runnable{

	GameLoop loop;
	
	public void run(){}
	
	public void start(){
		ArrayList<BaseEntity> entities = Levels.translate(GameSettings.level.getLocation());
		loop = new GameLoop();
		loop.start(entities);
	}
	
	public void stop(){
		loop.stop();
	}
	
	public void continueLoop(){
		loop.continueLoop();
	}
}
