package GuiMenus;

import java.awt.Graphics;
import java.util.ArrayList;

import Engine.MenuHandler;
import GuiElements.*;
import GuiSubMenu.SubMenu;
import KeyInput.MousePoint;

public abstract class Menu{
	private TextField selectedTextField;
	private ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	private ArrayList<ButtonListener> buttonListeners = new ArrayList<ButtonListener>();
	private ArrayList<TextFieldListener> textFieldListeners = new ArrayList<TextFieldListener>();
	private ArrayList<SubMenu> subMenus = new ArrayList<SubMenu>();
	private ArrayList<Container> containers = new ArrayList<Container>();
	
	private MenuHandler menuHandler;

	public abstract void createNonStaticContainersAndSubMenus();

	public void add(GuiElement ge){
		guiElements.add(ge);
	}

	public void add(SubMenu sm){
		sm.setMenuHandler(menuHandler);
		subMenus.add(sm);
	}

	public void add(Container c){
		c.setMenuHandler(menuHandler);
		containers.add(c);
	}

	public void deleteAllGuiElements(){
		guiElements = new ArrayList<GuiElement>();
	}

	public void deleteAllButtonListeners(){
		buttonListeners = new ArrayList<ButtonListener>();
	}

	public void deleteAllSubMenus(){
		subMenus = new ArrayList<SubMenu>();
	}

	public void deleteAllContainers(){
		containers = new ArrayList<Container>();
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

	public void drawMe(Graphics g){
		for(int i=0; i<guiElements.size(); i++)guiElements.get(i).drawMe(g);
		for(int i=0; i<containers.size(); i++)containers.get(i).drawMe(g);
		for(int i=0; i<subMenus.size(); i++)subMenus.get(i).drawMe(g);
	}

	public void addButtonListener(ButtonListener bl){
		buttonListeners.add(bl);
	}

	public void addTextFieldListener(TextFieldListener tfl){
		textFieldListeners.add(tfl);
	}

	public ArrayList<ButtonListener> getButtonListeners(){
		ArrayList<ButtonListener> bl = new ArrayList<ButtonListener>();
		bl.addAll(buttonListeners);
		for(SubMenu sm : subMenus)bl.addAll(sm.getButtonListeners());
		return bl;
	}

	public ArrayList<TextFieldListener> getTextFieldListeners(){
		ArrayList<TextFieldListener> tfl = new ArrayList<TextFieldListener>();
		tfl.addAll(textFieldListeners);
		for(SubMenu sm : subMenus)tfl.addAll(sm.getTextFieldListeners());
		return tfl;
	}

	public Button getClickedButton(MousePoint point){
		if(selectedTextField != null){
			selectedTextField.setSelected(false);
			menuHandler.repaint();
			selectedTextField = null;
		}

		for(int i=subMenus.size()-1; i>=0; i--)
			if(subMenus.get(i).isClicked(point))return subMenus.get(i).getClickedButton(point);
		for(int i=containers.size()-1; i>=0; i--)
			if(containers.get(i).isClicked(point))return containers.get(i).getClickedButton(point);
		for(int i=guiElements.size()-1; i>=0; i--){
			GuiElement b = guiElements.get(i);
			if(b instanceof Button && ((Button) b).isClicked(point) && b.getVisible())
				return (Button) b;
			if(b instanceof TextField && ((TextField) b).isClicked(point) && b.getVisible()){
				((TextField) b).setSelected(true);
				selectedTextField = ((TextField) b);
				menuHandler.repaint();
				return null;
			}
			if(b instanceof Area && ((Area<?>) b).isClicked(point) && b.getVisible())return ((Area<?>) b).getClickedButton(point);
		}
		return null;
	}

	public TextField getSelectedTextField(){
		for(int i=subMenus.size()-1; i>=0; i--)if(subMenus.get(i).isAnyTextFieldSelected())return subMenus.get(i).getSelectedTextField();
		for(int i=containers.size()-1; i>=0; i--)if(containers.get(i).isAnyTextFieldSelected()  && containers.get(i).getVisible())return containers.get(i).getSelectedTextField();
		for(int i=guiElements.size()-1; i>=0; i--){
			GuiElement b = guiElements.get(i);
			if(b instanceof TextField && ((TextField) b).isSelected() && b.getVisible())return (TextField) b;
		}
		return null;
	}
}