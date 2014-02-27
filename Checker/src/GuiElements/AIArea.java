package GuiElements;

import java.awt.Graphics;
import java.util.ArrayList;

import AIs.AI;
import AIs.AIs;
import KeyInput.MousePoint;
import Languages.Languages;

public class AIArea extends Area<AI>{
	int ai, default_ai = 0, ai_number, ai_numberOnThisPage, max_buttons_onThisPage;
	int rows, columns, row_pitch, column_pitch;

	AIArea(int id, ArrayList<AI> objectsInArea) {
		super(id, objectsInArea);
	}

	public AIArea(int id, ArrayList<AI> objectsInArea, int x, int y, int width, int height){
		super(id, objectsInArea, x, y, width, height);
	}

	public void settings(int rows, int columns, int row_pitch, int column_pitch){
		this.rows = rows;
		this.columns = columns;
		this.row_pitch = row_pitch;
		this.column_pitch = column_pitch;
	}

	@Override
	public void drawMe(Graphics g) {
		for(GuiElement ge : guiElements) ge.drawMe(g);
	}
	
	@Override
	public void initialize() {
		guiElements.clear();
		
		ai_numberOnThisPage = 1;
		ai = default_ai;
		ai_number = AIs.getAI_Number();
		max_buttons_onThisPage = (ai_number == rows*columns)? rows*columns : (ai_number - ai <= rows*columns-1)? ai_number - ai : rows*columns-1;

		if(ai + rows * columns - 1 < ai_number) add(new NormalButton(7, Languages.getButtonAndMenuTitle(7), TitleOrientation.MID, 1275, 760, width, 50, 255, 255, 0));
		if(ai - rows * columns + 1 >= 0) add(new NormalButton(8, Languages.getButtonAndMenuTitle(8), TitleOrientation.MID, 1275, 820, width, 50, 255, 255, 0));
		
		for(int z = 0, y = super.y + column_pitch; ai_numberOnThisPage <= max_buttons_onThisPage; ai++, z++, ai_numberOnThisPage++){
			int x = super.x + row_pitch + (width + row_pitch) * z;
			add(new AIButton(id, null, null, AIs.getAI(ai), x, y, width, height, 255, 255, 0));
			if(ai_numberOnThisPage % rows == 0 && ai_numberOnThisPage != 0){
				y += height + column_pitch; 
				z = -1;
			}
		}
		ai_numberOnThisPage--;
		ai--;
	}

	public void setDefaultAI(int default_ai){
		this.default_ai = default_ai;
	}

	@Override
	public Button getClickedButton(MousePoint point) {
		for(GuiElement ge : guiElements){
			if(ge instanceof Button && ((Button) ge).isClicked(point)){
				if(((Button) ge).getID() == id) return (Button) ge;
				if(((Button) ge).getID() == 7){
					setDefaultAI(ai + 1);
					initialize();
					Gui.Gui.repaintScreen();
					break;
				}
				if(((Button) ge).getID() == 8){
					setDefaultAI(ai + 1 - (ai_numberOnThisPage + rows * columns - 1));
					initialize();
					Gui.Gui.repaintScreen();
					break;
				}
			}
		}
		return null;
	}

}
