package GuiMenus;

import Engine.MenuHandler;
import GuiElements.*;
import Languages.Languages;

public class AIMenu extends Menu{

	public AIMenu(){
		addButtonListener(new NormalButtonListener());

		add(new Image(0, 0, Graphics.Images.getGraphic("/Graphics/menu_background/Menu_Background.png")));

		add(new NormalButton(3, Languages.getButtonAndMenuTitle(3), TitleOrientation.MID, 35, 35, 270, 50, 255, 255, 0));

		add(new NormalButton(9, Languages.getButtonAndMenuTitle(9), TitleOrientation.MID, 35, 360, 270, 50, 255, 255, 0));
		add(new NormalButton(10, Languages.getButtonAndMenuTitle(10), TitleOrientation.MID, 35, 425, 270, 50, 255, 255, 0));
		add(new NormalButton(11, Languages.getButtonAndMenuTitle(11), TitleOrientation.MID, 35, 490, 270, 50, 255, 255, 0));

		add(new NormalButton(1, Languages.getButtonAndMenuTitle(1), TitleOrientation.MID, 35, 750, 270, 50, 255, 255, 0));
		add(new NormalButton(0, Languages.getButtonAndMenuTitle(0), TitleOrientation.MID, 35, 815, 270, 50, 255, 255, 0));

		AIArea aiarea = new AIArea(6, AIs.AIs.getAIs(), 340, 20, 285, 160);
		aiarea.settings(4, 5, 20, 10);
		aiarea.initialize();
		add(aiarea);
	}

	public void createNonStaticContainersAndSubMenus(){

	}

	private class NormalButtonListener extends ButtonListener{

		@Override
		public void clicked(Button button) {
			if(button instanceof NormalButton){
				MenuHandler.changeMenu(button.getID());
			}
		}
	}
}