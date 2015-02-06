package KeyInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Gui.SwingGui;

public class Mouse implements MouseListener{
	private SwingGui swingGui;
	
	public Mouse(SwingGui swingGui){
		this.swingGui = swingGui;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		swingGui.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		swingGui.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent me) {
		swingGui.checkMouseInput(new MousePoint(me.getX(), me.getY(), (me.getButton() == MouseEvent.BUTTON1)));		
	}
}
