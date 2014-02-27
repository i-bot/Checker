package KeyInput;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window implements WindowListener{

	public static Boolean windowHasBeenDeiconified = false;
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		windowHasBeenDeiconified = true;
	}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		windowHasBeenDeiconified = true;
	}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {
		windowHasBeenDeiconified = true;
	}
}
