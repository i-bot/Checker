package KeyInput;

import java.awt.Point;

public class MousePoint {
	int x, y;
	Boolean leftClicked, rightClicked;
	
	MousePoint(int x, int y, Boolean leftClicked){
		this.x = x;
		this.y = y;
		this.leftClicked = leftClicked;
		this.rightClicked = (leftClicked!=true);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Point toPoint(){
		return new Point(x, y);
	}
}
