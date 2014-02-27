package GuiElements;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Graphics.Scaler;
import KeyInput.MousePoint;

public abstract class Area<T> extends GuiElement{
	public abstract void initialize();
	public abstract Button getClickedButton(MousePoint point);
	
	int id;	
	protected ArrayList<T> objectsInArea;
	protected ArrayList<BufferedImage> chars = new ArrayList<BufferedImage>();
	protected ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	
	Area(int id, ArrayList<T> objectsInArea) {
		this.id = id;
		this.objectsInArea = objectsInArea;
	}
	
	Area(int id, ArrayList<T> objectsInArea, int x, int y, int width, int height) {
		this(id, objectsInArea);
		setBounds(x, y, width, height);
	}
	
	public void initializeChars(String s){
		chars = myChars.myChars.getMyChars(s);
	}
	
	public Integer getOrientationX(TitleOrientation orientation){
		int orientation_X = Scaler.scale(15);
		if(orientation == TitleOrientation.LEFT)return orientation_X;
		else if(orientation == TitleOrientation.MID)return (width-chars.size()*orientation_X)/2;
		else if(orientation == TitleOrientation.RIGHT)return (width-chars.size()*orientation_X-orientation_X);
		else return 0;
	}
	
	public int getID(){
		return id;
	}
	
	public void add(GuiElement ge){
		guiElements.add(ge);
	}
	
	public void setBounds(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		r = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public Boolean isClicked(MousePoint point){
		for(GuiElement ge : guiElements) 
				if(ge instanceof Button && ((Button) ge).isClicked(point)) 
						return true;
		return false;
	}
}