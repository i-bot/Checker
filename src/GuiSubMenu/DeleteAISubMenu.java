package GuiSubMenu;

import AIs.AI;
import GuiElements.*;
import Languages.Languages;

public class DeleteAISubMenu extends SubMenu{
	VerificationSubMenu vsb;
	MessageSubMenu msb;
	AI aiToDelete;

	public DeleteAISubMenu(){
		addButtonListener(new SubMenuListener());
		
		vsb = new VerificationSubMenu(12, "", TitleOrientation.MID, 785, 350, 350, 200);
		vsb.setEdgeColor(127, 127, 127);
		vsb.setCentreColor(195, 195, 195);
		vsb.setVisible(false);
		add(vsb);
		
		msb = new MessageSubMenu(15, "", TitleOrientation.MID, 785, 350, 350, 200);
		msb.setEdgeColor(127, 127, 127);
		msb.setCentreColor(195, 195, 195);
		msb.setVisible(false);
		add(msb);
	}
	
	public void show(AI aiToDelete){
		this.aiToDelete = aiToDelete;
		
		if(aiToDelete.isChangeable()){
			vsb.setVisible(true);
			msb.setVisible(false);
			vsb.setText(Languages.getButtonAndMenuTitle(12) + aiToDelete.getName());
		}
		else{
			vsb.setVisible(false);
			msb.setVisible(true);
			msb.setText(aiToDelete.getName() + Languages.getButtonAndMenuTitle(15));
		}
	}
	
	private class SubMenuListener extends ButtonListener{

		@Override
		public void clicked(Button button) {			
			if(button instanceof VerificationSubMenu){
				if(button.getID() == 12){
					if(((VerificationSubMenu) button).getIDFromClickedButton()==13){
						AIs.AIs.deleteAI(aiToDelete);
					}
					vsb.setVisible(false);
					msb.setVisible(false);
					changeMenu(11);
				}
			}		
			if(button instanceof MessageSubMenu){
				if(button.getID() == 15){
					vsb.setVisible(false);
					msb.setVisible(false);
					changeMenu(11);
				}
			}
		}
	}
}