package BaseEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class BaseEntity {
	static double playerSpeed_X;
	protected Boolean alive;
	protected Rectangle rectangle;
	protected BufferedImage bufferedImage;
	protected float f_posx, f_posy;
	
	public abstract void computeNextPosition(float timeSinceLastFrame);
	public abstract void computeState(ArrayList<BaseEntity> entities);
	
	public BaseEntity(int x, int y){
		f_posx=x;
		f_posy=y;
		rectangle = new Rectangle(x, y, bufferedImage.getWidth(), bufferedImage.getHeight());
		alive = true;
	}
	
	public void drawMe(Graphics g){
		g.drawImage(bufferedImage, rectangle.x, rectangle.y, null);
	}
	
	public Boolean isAlive(){
		return alive;
	}
}