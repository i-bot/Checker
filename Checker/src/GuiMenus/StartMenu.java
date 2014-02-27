package GuiMenus;

import Engine.MenuHandler;
import GuiElements.*;
import Languages.Languages;

public class StartMenu extends Menu{

	public StartMenu(){
		addButtonListener(new NormalButtonListener());
		addTextFieldListener(new TextListener());
		
		add(new Image(0, 0, Graphics.Images.getGraphic("/Graphics/menu_background/Menu_Background.png")));

		TextField tf = new TextField(300, 300, 220, 40, TextField.Trigger.TRIGGER_ON_ENTER);
		tf.setEdgeColor(127, 127, 127);
		tf.setCentreColorUnselected(195, 195, 195);
		tf.setCentreColorSelected(255, 255, 0);
		tf.setText("Hello!?hhhhhhh");
		add(tf);

		add(new NormalButton(3, Languages.getButtonAndMenuTitle(3), TitleOrientation.MID, 35, 35, 270, 50, 255, 255, 0));

		add(new NormalButton(4, Languages.getButtonAndMenuTitle(4), TitleOrientation.MID, 35, 360, 270, 50, 255, 255, 0));
		add(new NormalButton(5, Languages.getButtonAndMenuTitle(5), TitleOrientation.MID, 35, 425, 270, 50, 255, 255, 0));

		add(new NormalButton(2, Languages.getButtonAndMenuTitle(2), TitleOrientation.MID, 35, 750, 270, 50, 255, 255, 0));
		add(new NormalButton(0, Languages.getButtonAndMenuTitle(0), TitleOrientation.MID, 35, 815, 270, 50, 255, 255, 0));
	}

	public void createNonStaticContainersAndSubMenus(){}

	private class NormalButtonListener extends ButtonListener{

		@Override
		public void clicked(Button button){
			if(button instanceof NormalButton)MenuHandler.changeMenu(button.getID());
		}
	}
	
	private class TextListener extends TextFieldListener{

		@Override
		public void textField(TextField textField) {
			System.out.println("StartMenu.TextListener.textField(): " + textField.getText());
			
		}
		
	}
}
