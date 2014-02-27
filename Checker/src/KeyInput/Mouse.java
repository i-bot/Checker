package KeyInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Engine.MenuHandler;

public class Mouse implements MouseListener{
	private static ArrayList<MousePoint> list= new ArrayList<MousePoint>();
	private static Boolean gameMenuUsed;

	public void checkMouseInput(MousePoint mouse){
		if(!gameMenuUsed)MenuHandler.checkMouseInput(mouse);
		else list.add(mouse);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		Gui.Gui.repaintScreen();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		Gui.Gui.repaintScreen();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent me) {
		checkMouseInput(new MousePoint(me.getX(), me.getY(), (me.getButton() == MouseEvent.BUTTON1)));		
	}
	
	public static void setGameMenuUsed(Boolean gameMenuUsed){
		Mouse.gameMenuUsed=gameMenuUsed;
	}
	
	public static ArrayList<MousePoint> getMouseList(){
		return list;
	}
	
	public void resetMouseList(){
		list = new ArrayList<MousePoint>();
	}
}
