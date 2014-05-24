package KeyInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	private static boolean[] keys = new boolean[1024];
	
	public static boolean getKeyState(int keyCode){
		if(keyCode>0 && keyCode<keys.length)return keys[keyCode];
		else return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode>0 && keyCode<keys.length)keys[keyCode]=true;
		Engine.MenuHandler.checkKeyboardInput(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode>0 && keyCode<keys.length)keys[keyCode]=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
