package KeyInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	private static boolean[] keys = new boolean[1024];
	private static Boolean gameMenuUsed;

	public static boolean getKeyState(int keyCode){
		if(keyCode>0 && keyCode<keys.length)return keys[keyCode];
		else return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(gameMenuUsed && keyCode>0 && keyCode<keys.length)keys[keyCode]=true;
		else Engine.MenuHandler.checkKeyboardInput(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(gameMenuUsed && keyCode>0 && keyCode<keys.length)keys[keyCode]=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public static void setGameMenuUsed(Boolean gameMenuUsed){
		Keyboard.gameMenuUsed=gameMenuUsed;
	}
}
