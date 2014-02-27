package GuiMenus;

import AIs.AIs;
import GuiElements.AIArea;
import GuiElements.Label;
import GuiElements.NormalButton;
import GuiElements.TitleOrientation;

public class ChoosePlayer1Container extends Container {

	public ChoosePlayer1Container() {
		add(new Label("Choose the white Player:", TitleOrientation.MID, 340, 35, 1240, 50));
		
		add(new NormalButton(17, "Real Player", TitleOrientation.MID, 355, 100, 270, 50, 255, 255, 0));
		
		AIArea aiarea = new AIArea(17, AIs.getAIs(), 625, 180, 285, 160);
		aiarea.settings(3, 4, 20, 10);
		aiarea.initialize();
		add(aiarea);
	}

}
