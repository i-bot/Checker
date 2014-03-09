package GuiMenus;

import Engine.MenuHandler;
import GuiElements.*;
import Languages.Languages;

public class OptionsMenu extends Menu{
	
	public OptionsMenu(){		
		addButtonListener(new NormalButtonListener());
		
		add(new Image(0, 0, Graphics.Images.getGraphic("/Graphics/menu_background/Menu_Background.png")));

		add(new NormalButton(1, Languages.getButtonAndMenuTitle(1), TitleOrientation.MID, 35, 750, 270, 50, 255, 255, 0));

		add(new NormalButton(0, Languages.getButtonAndMenuTitle(0), TitleOrientation.MID, 35, 815, 270, 50, 255, 255, 0));
		
		add(new Label("This function has not been etablished yet.", TitleOrientation.LEFT, 355, 30, 640, 920));
	}
	
	public void createNonStaticContainersAndSubMenus(){}
	
	private class NormalButtonListener extends ButtonListener{

		@Override
		public void clicked(Button button) {
			if(button instanceof NormalButton) MenuHandler.changeMenu(button.getID());
		}
	}
}
