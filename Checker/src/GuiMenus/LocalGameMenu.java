package GuiMenus;

import GameEngine.Game;
import GameEngine.CheckerBoard.Color;
import GameEngine.Player.AIPlayer;
import GameEngine.Player.Player;
import GameEngine.Player.RealPlayer;
import Engine.MenuHandler;
import GuiElements.AIButton;
import GuiElements.Button;
import GuiElements.ButtonListener;
import GuiElements.Image;
import GuiElements.NormalButton;
import GuiElements.TitleOrientation;
import Languages.Languages;

public class LocalGameMenu extends Menu{

	private Container choosePlayer1Container, choosePlayer2Container;
	private Player player1, player2;
	private Game localGame;

	public LocalGameMenu() {
		addButtonListener(new LocalGameButtonListener());

		add(new Image(0, 0, Graphics.Images.getGraphic("/Graphics/menu_background/Menu_Background_Local_Game.png")));

		add(new NormalButton(3, Languages.getButtonAndMenuTitle(3), TitleOrientation.MID, 35, 35, 270, 50, 255, 255, 0));

		add(new NormalButton(4, Languages.getButtonAndMenuTitle(4), TitleOrientation.MID, 35, 360, 270, 50, 255, 255, 0));
		add(new NormalButton(5, Languages.getButtonAndMenuTitle(5), TitleOrientation.MID, 35, 425, 270, 50, 255, 255, 0));

		add(new NormalButton(1, Languages.getButtonAndMenuTitle(1), TitleOrientation.MID, 35, 750, 270, 50, 255, 255, 0));
		add(new NormalButton(0, Languages.getButtonAndMenuTitle(0), TitleOrientation.MID, 35, 815, 270, 50, 255, 255, 0));

		choosePlayer1Container = new ChoosePlayer1Container();
		choosePlayer2Container = new ChoosePlayer2Container();

		choosePlayer1Container.setVisible(true);
		choosePlayer2Container.setVisible(false);

		add(choosePlayer1Container);
		add(choosePlayer2Container);

		player1 = null;
		player2 = null;
	}

	public void createNonStaticContainersAndSubMenus(){}

	private class LocalGameButtonListener extends ButtonListener{

		@Override
		public void clicked(Button button){
			if(button != null && button.getID() == 17){
				if(button instanceof NormalButton)
					player1 = new RealPlayer(((ChoosePlayer1Container) choosePlayer1Container).getRealPlayerName(), Color.LIGHT);
				if(button instanceof AIButton){
					player1 = new AIPlayer(((AIButton) button).getAI(), Color.LIGHT);
					((AIPlayer) player1).load();
				}

				choosePlayer1Container.setVisible(false);
				choosePlayer2Container.setVisible(true);

				MenuHandler.changeMenu(MenuHandler.getShownMenu());
			}
			else if(button != null && button.getID() == 18){
				if(button instanceof NormalButton)
					player2 = new RealPlayer(((ChoosePlayer2Container) choosePlayer2Container).getRealPlayerName(), Color.DARK);
				if(button instanceof AIButton){
					player2 = new AIPlayer(((AIButton) button).getAI(), Color.DARK);
					((AIPlayer) player2).load();
				}

				choosePlayer1Container.setVisible(true);
				choosePlayer2Container.setVisible(false);

				localGame = new Game(player1, player2);

				Main.Main.getGameEngine().init(localGame);
				MenuHandler.changeMenu(19);
				Main.Main.getGameEngine().start();
			}
			else if(button instanceof NormalButton){
				choosePlayer1Container.setVisible(true);
				choosePlayer2Container.setVisible(false);

				player1 = null;
				player2 = null;

				MenuHandler.changeMenu(button.getID());
			}
		}
	}
}
