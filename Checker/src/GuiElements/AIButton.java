package GuiElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import AIs.AI;

public class AIButton extends Button {
	private ArrayList<String> attributes;
	private AI ai;

	public AIButton(int id, String title, TitleOrientation orientation, AI ai) {
		super(id, title, orientation);
		readAttributes(ai);
	}

	public AIButton(int id, String title, TitleOrientation orientation, AI ai, int x, int y, int width, int height){
		super(id, title, orientation, x, y, width, height);
		readAttributes(ai);
	}

	public AIButton(int id, String title, TitleOrientation orientation, AI ai, int x, int y, int width, int height, Color color){
		this(id, title, orientation, ai, x, y, width, height);
		setColor(color);
	}

	public AIButton(int id, String title, TitleOrientation orientation, AI ai, int x, int y, int width, int height, int red, int green, int blue){
		this(id, title, orientation, ai, x, y, width, height);
		setColor(red, green, blue);
	}

	public void readAttributes(AI ai){
		this.ai = ai;
		
		attributes = new ArrayList<String>();
		attributes.add(ai.getName());
		attributes.add("Version: " + ai.getVersion());
		attributes.add("Date: " + ai.getDate());
		attributes.add("Writer: " + ai.getAuthor());
	}
	
	public AI getAI(){
		return ai;
	}
	
	@Override
	public void drawMe(Graphics g) {
		orientation = TitleOrientation.MID;
		
		if(visible){
			g.setColor(color);
			g.fillRect(x, y, width, height);

			for(int column = 0; column < attributes.size(); column++){
				initializeChars(attributes.get(column));
				for(int i=0; i<chars.size(); i++){
					BufferedImage img = chars.get(i);
					int gap = (height - img.getHeight() * attributes.size()) / (attributes.size() + 1);
					
					if(img != null)g.drawImage(img, x + getOrientationX(orientation) + img.getWidth() * i, y + img.getHeight() * column + gap * (column + 1), null);
				}
				
				orientation = TitleOrientation.LEFT;
			}

		}
	}
}
