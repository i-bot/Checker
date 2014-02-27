package GuiElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class NormalButton extends Button{

	public NormalButton(int id, String title, TitleOrientation orientation) {
		super(id, title, orientation);	
	}

	public NormalButton(int id, String title, TitleOrientation orientation, int x, int y, int width, int height){
		super(id, title, orientation, x, y, width, height);
	}

	public NormalButton(int id, String title, TitleOrientation orientation, int x, int y, int width, int height, Color color){
		super(id, title, orientation, x, y, width, height);
		setColor(color);
	}

	public NormalButton(int id, String title, TitleOrientation orientation, int x, int y, int width, int height, int red, int green, int blue){
		super(id, title, orientation, x, y, width, height);
		setColor(red, green, blue);
	}

	@Override
	public void drawMe(Graphics g) {
		if(visible){
			initializeChars(title);
			g.setColor(color);
			g.fillRect(x, y, width, height);

			for(int i=0; i<chars.size(); i++){
				BufferedImage img = chars.get(i);
				if(img != null)g.drawImage(img, x + getOrientationX(orientation) + img.getWidth() * i, y + (height - img.getHeight()) / 2, null);
			}
		}
	}
}