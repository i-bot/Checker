package GuiElements;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Graphics.Scaler;
import KeyInput.MousePoint;

public abstract class Button extends GuiElement{
	protected String title;
	protected TitleOrientation orientation;
	protected Color color;
	protected int id;
	protected ArrayList<BufferedImage> chars = new ArrayList<BufferedImage>();
	
	Button(int id, String title, TitleOrientation orientation) {
		this.id = id;
		this.title = title;
		this.orientation = orientation;
	}
	
	Button(int id, String title, TitleOrientation orientation, int x, int y, int width, int height){
		this(id, title, orientation);
		setBounds(x, y, width, height);
	}
	
	public void setColor(Color color){
		this.color=color;
	}
	
	public void setColor(int red, int green, int blue){
		if(red < 256 && red >= 0 && green < 256 && green >= 0 && blue < 256 && blue >= 0)color = new Color(red, green, blue);
	}
	
	public void initializeChars(String s){
		chars = myChars.myChars.getMyChars(s);
	}
	
	protected Integer getOrientationX(TitleOrientation orientation){
		int orientation_X = Scaler.scale(15);
		if(orientation == TitleOrientation.LEFT)return orientation_X;
		else if(orientation == TitleOrientation.MID)return (width - chars.size() * orientation_X) / 2;
		else if(orientation == TitleOrientation.RIGHT)return (width - chars.size()*orientation_X - orientation_X);
		else return 0;
	}
	
	public int getID(){
		return id;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public TitleOrientation getOrientation(){
		return orientation;
	}

	public Boolean isClicked(MousePoint point){
		return r.contains(new Point(point.getX(), point.getY())) && visible;
	}
}