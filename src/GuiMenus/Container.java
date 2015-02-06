package GuiMenus;

import java.awt.Graphics;
import java.util.ArrayList;

import Engine.MenuHandler;
import GuiElements.*;
import KeyInput.MousePoint;

public class Container {
	private TextField selectedTextField;
	private ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	private Boolean visible = true;
	
	private MenuHandler menuHandler;

	public void add(GuiElement ge){
		guiElements.add(ge);
	}

	public void clear(){
		guiElements = new ArrayList<GuiElement>();
	}

	public void drawMe(Graphics g){
		if(visible)for(int i=0; i<guiElements.size(); i++)guiElements.get(i).drawMe(g);
	}
	

	public void setMenuHandler(MenuHandler menuHandler){
		this.menuHandler = menuHandler;
	}
	
	public void repaint(){
		menuHandler.repaint();
	}
	
	public void changeMenu(int id){
		menuHandler.changeMenu(id);
	}

	public Boolean isClicked(MousePoint point){
		if(visible){
			for(GuiElement ge : guiElements){
				if(ge instanceof Button && ge.getVisible() && ((Button) ge).isClicked(point)) return true;	
				if(ge instanceof TextField && ge.getVisible() && ((TextField) ge).isClicked(point)) return true;	
				if(ge instanceof Area<?> && ge.getVisible() && ((Area<?>) ge).isClicked(point)) return true;
			}
		}

		if(selectedTextField != null){
			selectedTextField.setSelected(false);
			menuHandler.repaint();
			selectedTextField = null;
		}

		return false;
	}

	public Button getClickedButton(MousePoint point){
		if(selectedTextField != null){
			selectedTextField.setSelected(false);
			menuHandler.repaint();
			selectedTextField = null;
		}

		for(int i=guiElements.size()-1; i>=0; i--){
			GuiElement ge = guiElements.get(i);
			if(ge instanceof Button && ((Button) ge).isClicked(point) && ge.getVisible())return (Button) ge;
			if(ge instanceof TextField && ((TextField) ge).isClicked(point) && ge.getVisible()){
				((TextField) ge).setSelected(true);
				selectedTextField = ((TextField) ge);
				menuHandler.repaint();
				return null;
			}
			if(ge instanceof Area && ((Area<?>) ge).isClicked(point) && ge.getVisible())return ((Area<?>) ge).getClickedButton(point);
		}
		return null;
	}

	public Boolean isAnyTextFieldSelected(){
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

	public Boolean getVisible(){
		return  visible;
	}

	public void setVisible(Boolean visible){
		this.visible = visible;
	}

	public void show(){
		visible = true;
		menuHandler.repaint();
	}
}
