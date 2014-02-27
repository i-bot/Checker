package GuiSubMenu;

import java.awt.Graphics;
import java.util.ArrayList;

import GuiElements.*;
import GuiMenus.Container;
import KeyInput.MousePoint;

public abstract class SubMenu{
	ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	ArrayList<ButtonListener> buttonListeners = new ArrayList<ButtonListener>();
	ArrayList<TextFieldListener> textFieldListeners = new ArrayList<TextFieldListener>();
	ArrayList<Container> containers = new ArrayList<Container>();
	
	public void add(GuiElement ge){
		guiElements.add(ge);
	}
	
	public void addButtonListener(ButtonListener bl){
		buttonListeners.add(bl);
	}
	
	public void addTextFieldListener(TextFieldListener tfl){
		textFieldListeners.add(tfl);
	}
	
	public void add(Container c){
		containers.add(c);
	}
	
	public void deleteAllGuiElements(){
		guiElements = new ArrayList<GuiElement>();
	}
	
	public void deleteAllButtonListeners(){
		buttonListeners = new ArrayList<ButtonListener>();
	}
	
	public void deleteAllContainers(){
		containers = new ArrayList<Container>();
	}
	
	public void drawMe(Graphics g){
		for(GuiElement ge : guiElements)ge.drawMe(g);
		for(Container c : containers)c.drawMe(g);
	}
	
	public ArrayList<ButtonListener> getButtonListeners(){
		return buttonListeners;
	}
	
	public ArrayList<TextFieldListener> getTextFieldListeners(){
		return textFieldListeners;
	}
	
	public Boolean isClicked(MousePoint point){
		for(GuiElement ge : guiElements){
			if(ge instanceof Button && ge.getVisible() && ((Button) ge).isClicked(point))return true;
			if(ge instanceof TextField && ge.getVisible() && ((TextField) ge).isClicked(point))return true;
		}
		for(Container c : containers)if(c.isClicked(point))return true;	
		return false;
	}
	
	public Button getClickedButton(MousePoint point){
		for(int i=containers.size()-1; i>=0; i--)if(containers.get(i).isClicked(point))return containers.get(i).getClickedButton(point);	
		for(int i=guiElements.size()-1; i>=0; i--){
			GuiElement b = guiElements.get(i);
			if(b instanceof Button && ((Button) b).isClicked(point) && b.getVisible())return (Button) b;
			if(b instanceof TextField && ((TextField) b).isClicked(point) && b.getVisible()){
				((TextField) b).setSelected(true);
				((TextField) b).repaint();
				return null;
			}
			if(b instanceof Area && ((Area<?>) b).isClicked(point) && b.getVisible())return ((Area<?>) b).getClickedButton(point);
		}
		return null;
	}
	
	public Boolean isAnyTextFieldIsSelected(){
		for(int i=containers.size()-1; i>=0; i--)if(containers.get(i).isAnyTextFieldIsSelected())return true;
		for(int i=guiElements.size()-1; i>=0; i--){
			GuiElement b = guiElements.get(i);
			if(b instanceof TextField && ((TextField) b).isSelected() && b.getVisible())return true;
		}
		return false;
	}
	
	public TextField getSelectedTextField(){
		for(int i = containers.size()-1; i >= 0; i--) return containers.get(i).getSelectedTextField();
		for(int i = guiElements.size()-1; i >= 0; i--){
			GuiElement b = guiElements.get(i);
			if(b instanceof TextField && ((TextField) b).isSelected() && b.getVisible())return (TextField) b;
		}
		return null;
	}
}