package KeyInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Gui.SwingGui;

public class Keyboard implements KeyListener{
	private SwingGui swingGui;

	public Keyboard(SwingGui swingGui){
		this.swingGui = swingGui;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		swingGui.checkKeyboardInput(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
