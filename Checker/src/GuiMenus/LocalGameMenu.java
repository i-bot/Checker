package GuiMenus;

import Engine.MenuHandler;
import GuiElements.Button;
import GuiElements.ButtonListener;
import GuiElements.Image;
import GuiElements.NormalButton;
import GuiElements.TitleOrientation;
import Languages.Languages;

public class LocalGameMenu extends Menu{

	private Container choosePlayer1Container, choosePlayer2Container;
	
	public LocalGameMenu() {
		addButtonListener(new NormalButtonListener());
		
		add(new Image(0, 0, Graphics.Images.getGraphic("/Graphics/menu_background/Menu_Background.png")));

		add(new NormalButton(3, Languages.getButtonAndMenuTitle(3), TitleOrientation.MID, 35, 35, 270, 50, 255, 255, 0));

		add(new NormalButton(4, Languages.getButtonAndMenuTitle(4), TitleOrientation.MID, 35, 425, 270, 50, 255, 255, 0));

		add(new NormalButton(2, Languages.getButtonAndMenuTitle(2), TitleOrientation.MID, 35, 750, 270, 50, 255, 255, 0));
		add(new NormalButton(0, Languages.getButtonAndMenuTitle(0), TitleOrientation.MID, 35, 815, 270, 50, 255, 255, 0));
		
		choosePlayer1Container = new ChoosePlayer1Container();
		choosePlayer2Container = new ChoosePlayer2Container();
		
		choosePlayer1Container.setVisible(true);
		choosePlayer2Container.setVisible(false);
		
		add(choosePlayer1Container);
		add(choosePlayer2Container);
	}

	public void createNonStaticContainersAndSubMenus(){}

	private class NormalButtonListener extends ButtonListener{

		@Override
		public void clicked(Button button){
			if(button instanceof NormalButton)MenuHandler.changeMenu(button.getID());
		}
	}
}
