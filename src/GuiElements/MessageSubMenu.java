package GuiElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Graphics.Scaler;
import KeyInput.MousePoint;
import Languages.Languages;

public class MessageSubMenu extends Button{
	protected Color edgeColor, centreColor;
	protected ArrayList<GuiElement> guiElements;
	protected String text;
	private Button ok_Button;

	public MessageSubMenu(int id, String title, TitleOrientation orientation) {
		super(id, title, orientation);	
		setText(title);
	}

	public MessageSubMenu(int id, String title, TitleOrientation orientation, int x, int y, int width, int height){
		this(id, title, orientation);
		setBounds(x, y, width, height);
		
		guiElements = new ArrayList<GuiElement>();
		guiElements.add(new Label(text, orientation, x + 20, y + 20, width - 40, height - 90));
		ok_Button = new NormalButton(16, Languages.getButtonAndMenuTitle(16), TitleOrientation.MID, (int) (x + (width - 100) / 2), y + height - 70, 100, 50, 255, 255, 0);
		guiElements.add(ok_Button);
	}

	public void setBounds(int x, int y, int width, int height){
		this.x = Scaler.scale(x);
		this.y = Scaler.scale(y);
		this.width = Scaler.scale(width);
		this.height = Scaler.scale(height);
	}

	public void setEdgeColor(int red, int green, int blue){
		if(red<256 && red>=0 && green<256 && green>=0 && blue<256 && blue>=0)edgeColor = new Color(red, green, blue);
	}

	public void setEdgeColor(Color edgeColor){
		this.edgeColor=edgeColor;
	}

	public void setCentreColor(int red, int green, int blue){
		if(red<256 && red>=0 && green<256 && green>=0 && blue<256 && blue>=0)centreColor = new Color(red, green, blue);
	}

	public void setCentreColor(Color centreColor){
		this.centreColor=centreColor;
	}
	
	public void setText(String text){
		this.text=text;
	}

	@Override
	public void drawMe(Graphics g) {
		if(visible){
			g.setColor(edgeColor);
			g.fillRect(x, y, width, height);
			g.setColor(centreColor);
			g.fillRect(x+Scaler.scale(5), y+Scaler.scale(5), width-Scaler.scale(10), height-Scaler.scale(10));
			
			for(GuiElement ge: guiElements){
				if(ge instanceof Label)((Label) ge).setText(text);
				ge.drawMe(g);
			}
		}
	}
	
	public Boolean isClicked(MousePoint point){
		return ok_Button.isClicked(point);
	}
	
}