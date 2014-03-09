package Engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import Gui.Gui;
import GuiElements.*;
import GuiMenus.*;
import KeyInput.MousePoint;

@SuppressWarnings("serial")
public class MenuHandler extends JFrame{
	static 	HashMap<Integer, Menu> menus = new HashMap<Integer, Menu>();
	private static int menu_ID = 1;

	static{
		menus.put(1, new StartMenu());
		menus.put(3, new OptionsMenu());
		menus.put(2, new AIMenu());
		menus.put(11, new DeleteAIMenu());
		menus.put(5, new LocalGameMenu());
		menus.put(19, new GameMenu());
	}

	public static void changeMenu(int id){
		if(id == 0)System.exit(0);
		else if(menus.containsKey(id))menu_ID = id;

		Gui.repaintScreen();
	}

	public static int getShownMenu(){
		return menu_ID;
	}

	public static Menu getMenu(int id){
		return (menus.containsKey(id))? menus.get(id) : null;
	}

	public static void drawMe(Graphics g){
		menus.get(menu_ID).createNonStaticContainersAndSubMenus();
		menus.get(menu_ID).drawMe(g);
	}

	public static void checkMouseInput(MousePoint point){
		Button b = menus.get(menu_ID).getClickedButton(point);

		if(b != null){
			ArrayList<ButtonListener> bl = menus.get(menu_ID).getButtonListeners();

			for(int i=0; i < bl.size(); i++) bl.get(i).clicked(b);
		}
	}

	public static void checkKeyboardInput(KeyEvent event){
		TextField tf = menus.get(menu_ID).getSelectedTextField();
		if(!(tf == null)){
			tf.checkKeyboardInput(event);
			if((tf.getTrigger() == TextField.Trigger.TRIGGER_ON_ENTER && event.getKeyCode() == KeyEvent.VK_ENTER) || tf.getTrigger() == TextField.Trigger.TRIGGER_ON_TEXTINPUT){
				ArrayList<TextFieldListener> tfl = menus.get(menu_ID).getTextFieldListeners();
				for(int i = 0; i < tfl.size(); i++) tfl.get(i).textField(tf);
			}
		}
	}
}