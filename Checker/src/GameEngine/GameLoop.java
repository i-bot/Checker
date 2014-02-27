package GameEngine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import BaseEntities.BaseEntity;
import KeyInput.Keyboard;
import Server.Sender;

public class GameLoop {
	
	//	If the game is paused this value is set true and the loop will be stopped
	boolean isStopped = false;
	float timeSinceLastFrame;
	long lastFrame, thisFrame;
	
	Sender sender;
	ArrayList<BaseEntity> entities;
	
	public void start(ArrayList<BaseEntity> entities){
		this.entities = entities;
		
		if(GameSettings.multiplayer){
			sender = new Sender();
			sender.init();
		}
		
		while(true){
			lastFrame = System.currentTimeMillis();
			
			while(!isStopped){
				
				sender.send();
				
				isStopped = Keyboard.getKeyState(KeyEvent.VK_ENTER);
				
				thisFrame = System.currentTimeMillis();
				timeSinceLastFrame = ((float) thisFrame - lastFrame) / 1000f;
				lastFrame = thisFrame;

				//The next position of every entity will be computed
				for(BaseEntity be : entities){
					be.computeNextPosition(timeSinceLastFrame);
				}

				//The state of every entity will be computed
				for(BaseEntity be : entities){
					be.computeState(entities);
				}

				//Remove dead entities
				for(int i = 0; i <entities.size(); i++){
					if(!entities.get(i).isAlive()){
						entities.remove(i);
						i--;
					}
				}
				
				BufferStrategy bufferStrategy = Gui.Gui.getGuiBufferStrategy();
				Graphics g = bufferStrategy.getDrawGraphics();
				drawEntities(g);
				g.dispose();
				bufferStrategy.show();
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		isStopped = true;
	}
	
	public void continueLoop(){
		isStopped = false;
	}
	
	public void drawEntities(Graphics g){
		for(BaseEntity be : entities){
			be.drawMe(g);
		}
	}
}