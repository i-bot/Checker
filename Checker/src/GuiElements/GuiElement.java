package GuiElements;

import java.awt.Graphics;
import java.awt.Rectangle;

import Graphics.Scaler;

public abstract class GuiElement{
	protected Boolean visible=true;
	protected int x, y, width, height;
	protected Rectangle r;
	
	public abstract void drawMe(Graphics g);
	
	public void setVisible(Boolean visible){
		this.visible=visible;
	}
	
	public Boolean getVisible(){
		return visible;
	}
	
	public void setBounds(int x, int y, int width, int height){
		this.x = Scaler.scale(x);
		this.y = Scaler.scale(y);
		this.width = Scaler.scale(width);
		this.height = Scaler.scale(height);
		r = new Rectangle(this.x, this.y, this.width, this.height);
	}
}
