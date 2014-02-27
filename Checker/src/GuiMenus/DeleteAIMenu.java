package GuiMenus;

import AIs.AIs;
import Engine.MenuHandler;
import GuiElements.*;
import GuiSubMenu.DeleteAISubMenu;
import Languages.Languages;

public class DeleteAIMenu extends Menu{

	DeleteAISubMenu deleteAISubMenu;

	public DeleteAIMenu(){
		addButtonListener(new AIButtonListener());
		addButtonListener(new NormalButtonListener());

		add(new Image(0, 0, Graphics.Images.getGraphic("/Graphics/menu_background/Menu_Background.png")));

		add(new NormalButton(3, Languages.getButtonAndMenuTitle(3), TitleOrientation.MID, 35, 35, 270, 50, 255, 255, 0));		
		add(new NormalButton(2, Languages.getButtonAndMenuTitle(2), TitleOrientation.MID, 35, 490, 270, 50, 255, 255, 0));		
		add(new NormalButton(1, Languages.getButtonAndMenuTitle(1), TitleOrientation.MID, 35, 750, 270, 50, 255, 255, 0));
		add(new NormalButton(0, Languages.getButtonAndMenuTitle(0), TitleOrientation.MID, 35, 815, 270, 50, 255, 255, 0));

		AIArea aiarea = new AIArea(6, AIs.getAIs(), 340, 20, 285, 160);
		aiarea.settings(4, 5, 20, 10);
		aiarea.initialize();
		add(aiarea);
		
		deleteAISubMenu = new DeleteAISubMenu();
		add(deleteAISubMenu);
	}

	public void createNonStaticContainersAndSubMenus(){

	}

	private class AIButtonListener extends ButtonListener{

		@Override
		public void clicked(Button button) {
			if(button instanceof AIButton){
				if(button.getID() == 6){
					deleteAISubMenu.show(((AIButton) button).getAI());
					repaint();
				}
			}
		}
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