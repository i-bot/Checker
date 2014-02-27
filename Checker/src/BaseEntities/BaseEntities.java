package BaseEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BaseEntities {
	static double playerSpeed_X;
	protected Rectangle rectangle;
	protected BufferedImage bufferedImage;
	protected float f_posx, f_posy;
	
	public BaseEntities(int x, int y){
		f_posx=x;
		f_posy=y;
		rectangle = new Rectangle(x, y, bufferedImage.getWidth(), bufferedImage.getHeight());
	}
	
	public void drawMe(Graphics g){
		g.drawImage(bufferedImage, rectangle.x, rectangle.y, null);
	}
}