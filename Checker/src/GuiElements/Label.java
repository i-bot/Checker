package GuiElements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Graphics.Scaler;

public class Label extends GuiElement{
	protected String text;
	protected TitleOrientation orientation;

	public Label(String text, TitleOrientation orientation) {
		setText(text);
		this.orientation = orientation;
	}

	public Label(String text, TitleOrientation orientation, int x, int y, int width, int height){
		this(text, orientation);
		setBounds(x, y, width, height);
	}
	
	public void setText(String text){
		this.text = text;
	}

	@Override
	public void drawMe(Graphics g) {
		if(visible){
			for(int z = 0; z < computeLines().size(); z++){
				ArrayList<BufferedImage> chars = myChars.myChars.getMyChars(computeLines().get(z));
				int orientation_x = getOrientation_X(orientation, chars.size());
				for(int i = 0; i < chars.size(); i++){
					BufferedImage img = chars.get(i);
					if(img != null)
						g.drawImage(img, x + orientation_x + img.getWidth() * i, y + img.getHeight() * z, null);
				}
			}
		}
	}

	protected ArrayList<String> computeLines(){
		String[] segments = text.split(Pattern.quote(" "));
		ArrayList<String> strings = new ArrayList<String>();
		for(int t_y = 0, i = 0; t_y < height; t_y += Scaler.scale(30)){
			String s = "";
			Boolean beginning = true;
			for(int x = 0; i<segments.length; i++){
				x += segments[i].length() * Scaler.scale(15);
				if(!(x <= width)){
					break;
				}
				s += ((!beginning)? " " : "") + segments[i];
				beginning = false;
			}
			strings.add(s);
		}
		return strings;
	}
	
	

	protected Integer getOrientation_X(TitleOrientation orientation, int n_chars){
		int orientation_x = Scaler.scale(15);
		if(orientation == TitleOrientation.LEFT)return 0;
		else if(orientation == TitleOrientation.MID)return (width - n_chars * orientation_x) / 2;
		else if(orientation == TitleOrientation.RIGHT)return (width - n_chars * orientation_x);
		else return 0;
	}
}
