package GuiMenus;

import AIs.AIs;
import GuiElements.AIArea;
import GuiElements.Label;
import GuiElements.NormalButton;
import GuiElements.TextField;
import GuiElements.TitleOrientation;

public class ChoosePlayer2Container extends Container {

	TextField tf;
	
	public ChoosePlayer2Container() {
		add(new Label("Choose the dark Player:", TitleOrientation.MID, 340, 35, 1240, 30));
		
		add(new NormalButton(18, "Real Player", TitleOrientation.MID, 360, 105, 285, 50, 255, 255, 0));
		
		tf = new TextField(665, 105, 895, 50, TextField.Trigger.TRIGGER_ON_ENTER);
		tf.setEdgeColor(127, 127, 127);
		tf.setCentreColorUnselected(195, 195, 195);
		tf.setCentreColorSelected(255, 255, 0);
		tf.setText("PLAYER2");
		add(tf);
		
		AIArea aiarea = new AIArea(18, AIs.getAIs(), 340, 185, 285, 160);
		aiarea.settings(4, 4, 20, 10);
		aiarea.initialize();
		add(aiarea);
	}

	public String getRealPlayerName(){
		return tf.getText();
	}

}
