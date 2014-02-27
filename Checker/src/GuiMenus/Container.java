package GuiMenus;

import java.awt.Graphics;
import java.util.ArrayList;

import Engine.MenuHandler;
import GuiElements.*;
import KeyInput.MousePoint;

public class Container {
	ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	Boolean visible = true;
	
	public void add(GuiElement ge){
		guiElements.add(ge);
	}
	
	public void clear(){
		guiElements = new ArrayList<GuiElement>();
	}
	
	public void repaint(){
		MenuHandler.changeMenu(MenuHandler.getShownMenu());
	}
	
	public void drawMe(Graphics g){
		if(visible)for(int i=0; i<guiElements.size(); i++)guiElements.get(i).drawMe(g);
	}
	
	public Boolean isClicked(MousePoint point){
		for(GuiElement ge : guiElements){
			if(ge instanceof Button && ge.getVisible() && ((Button) ge).isClicked(point)) return true;	
			if(ge instanceof Area<?> && ge.getVisible() && ((Area<?>) ge).isClicked(point)) return true;
		}
		return false;
	}
	
	public Button getClickedButton(MousePoint point){
		for(int i=guiElements.size()-1; i>=0; i--){
			GuiElement ge = guiElements.get(i);
			if(ge instanceof Button && ((Button) ge).isClicked(point) && ge.getVisible())return (Button) ge;
			if(ge instanceof TextField && ((TextField) ge).isClicked(point) && ge.getVisible()){
				((TextField) ge).setSelected(true);
				((TextField) ge).repaint();
				return null;
			}
			if(ge instanceof Area && ((Area<?>) ge).isClicked(point) && ge.getVisible())return ((Area<?>) ge).getClickedButton(point);
		}
		return null;
	}
	
	public Boolean isAnyTextFieldIsSelected(){
		for(int i=guiElements.size()-1; i>=0; i--){
			GuiElement b = guiElements.get(i);
			if(b instanceof TextField && ((TextField) b).isSelected() && b.getVisible())return true;
		}
		return false;
	}
	
	public TextField getSelectedTextField(){
		for(int i=guiElements.size()-1; i>=0; i--){
			GuiElement b = guiElements.get(i);
			if(b instanceof TextField && ((TextField) b).isSelected() && b.getVisible())return (TextField) b;
		}
		return null;
	}
	
	public void setVisible(Boolean visible){
		this.visible = visible;
	}
	
	public void show(){
		visible = true;
		Gui.Gui.repaintScreen();
	}
}
