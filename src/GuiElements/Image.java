package GuiElements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Graphics.Scaler;

public class Image extends GuiElement{
	protected BufferedImage bufferedImage;
	protected float f_posx, f_posy;
	
	public Image(int x, int y, BufferedImage bufferedImage) {
		this(x, y);
		changeImage(bufferedImage);
	}
	
	public Image(int x, int y){
		f_posx = Scaler.scale(x);
		f_posy = Scaler.scale(y);
	}

	public void changeImage(BufferedImage bufferedImage){
		this.bufferedImage = bufferedImage;
	}
	
	public void drawMe(Graphics g){
		g.drawImage(bufferedImage, (int) f_posx, (int) f_posy, null);
	}
}
