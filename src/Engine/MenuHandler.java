package Engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import Gui.Gui;
import GuiElements.*;
import GuiMenus.*;
import KeyInput.MousePoint;

public class MenuHandler {
	private	HashMap<Integer, Menu> menus = new HashMap<Integer, Menu>();
	private int menu_ID = 1;
	
	private Gui gui;

	public MenuHandler(Gui gui){
		this.gui = gui;
	}

	public void putMenu(int index, Menu menu){
		menu.setMenuHandler(this);
		menus.put(index, menu);
	}
	
	public void repaint(){
		gui.repaint();
	}
	
	public void changeMenu(int id){
		if(id == 0)System.exit(0);
		else if(menus.containsKey(id))menu_ID = id;

		repaint();
	}

	public int getShownMenu(){
		return menu_ID;
	}

	public Menu getMenu(int id){
		return (menus.containsKey(id))? menus.get(id) : null;
	}

	public void drawMe(Graphics g){
		menus.get(menu_ID).createNonStaticContainersAndSubMenus();
		menus.get(menu_ID).drawMe(g);
	}

	public void checkMouseInput(MousePoint point){
		Button b = menus.get(menu_ID).getClickedButton(point);

		repaint();
		
		if(b != null){
			ArrayList<ButtonListener> bl = menus.get(menu_ID).getButtonListeners();

			for(int i=0; i < bl.size(); i++) bl.get(i).clicked(b);			
		}
	}

	public void checkKeyboardInput(KeyEvent event){
		TextField tf = menus.get(menu_ID).getSelectedTextField();
		if(!(tf == null)){
			tf.checkKeyboardInput(event);
			if((tf.getTrigger() == TextField.Trigger.TRIGGER_ON_ENTER && event.getKeyCode() == KeyEvent.VK_ENTER) || tf.getTrigger() == TextField.Trigger.TRIGGER_ON_TEXTINPUT){
				ArrayList<TextFieldListener> tfl = menus.get(menu_ID).getTextFieldListeners();
				for(int i = 0; i < tfl.size(); i++) tfl.get(i).textField(tf);
			}
		}
		
		repaint();
	}
}