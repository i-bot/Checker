package GuiElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Graphics.Scaler;
import KeyInput.MousePoint;

public class TextField extends GuiElement{
	protected Color edgeColor, centreColorSelected, centreColorUnselected;
	protected int max_chars, cursor_pos = 0;
	protected Boolean selected  = false;
	protected StringBuffer text = new StringBuffer("");
	protected TextField.Trigger trigger;
	protected Graphics g;

	public TextField(int x, int y, int width, int height, TextField.Trigger trigger){
		setBounds(x, y, width, height);
		setTrigger(trigger);

		max_chars = (int) ((this.width-Scaler.scale(10))/Scaler.scale(15));
	}

	public void setTrigger(TextField.Trigger trigger){
		this.trigger = trigger;
	}

	public TextField.Trigger getTrigger(){
		return trigger;
	}

	public void setEdgeColor(int red, int green, int blue){
		if(red < 256 && red >= 0 && green < 256 && green >= 0 && blue < 256 && blue >= 0)edgeColor = new Color(red, green, blue);
	}

	public void setEdgeColor(Color edgeColor){
		this.edgeColor=edgeColor;
	}

	public void setCentreColorSelected(int red, int green, int blue){
		if(red < 256 && red >= 0 && green < 256 && green >= 0 && blue < 256 && blue >= 0)centreColorSelected = new Color(red, green, blue);
	}

	public void setCentreColorSelected(Color centreColorSelected){
		this.centreColorSelected=centreColorSelected;
	}

	public void setCentreColorUnselected(int red, int green, int blue){
		if(red < 256 && red >= 0 && green < 256 && green >= 0 && blue < 256 && blue >= 0)centreColorUnselected = new Color(red, green, blue);
	}

	public void setCentreColorUnselected(Color centreColorUnselected){
		this.centreColorUnselected=centreColorUnselected;
	}

	public Boolean isSelected(){
		return selected;
	}

	public void setSelected(Boolean selected){
		this.selected = selected;
	}

	public String getText(){
		return text.toString();
	}

	public void setText(String s){
		if(text.length() <= max_chars)text = new StringBuffer(s);
		else System.err.println("TextField.setText(): Text is out of bounds!");

		if(this.text.length() == max_chars)cursor_pos = max_chars-1;
		else if(this.text.length() < max_chars)cursor_pos = this.text.length();
	}

	public Boolean isClicked(MousePoint point){
		return r.contains(new Point(point.getX(), point.getY()));
	}

	protected void addChar(char c){
		if(text.length() < max_chars){
			text.insert(cursor_pos, c);
			if((cursor_pos+1)<max_chars)cursor_pos++;
		}
	}

	public void checkKeyboardInput(KeyEvent ke){
		if(ke.getKeyCode() == 8){
			if(text.length() != 0 && cursor_pos > 0){
				text.deleteCharAt(cursor_pos-1);
				cursor_pos--;
			}
		}
		else if(ke.getKeyCode() == 127){
			if(cursor_pos < text.length()) text.deleteCharAt(cursor_pos);
		}
		else if(ke.getKeyCode() == 10)
			setSelected(false);
		else if(ke.getKeyCode() == 37){
			if(cursor_pos > 0) cursor_pos--;
		}
		else if(ke.getKeyCode() == 39){
			if(cursor_pos < text.length() && (cursor_pos + 1) < max_chars)cursor_pos++;
		}
		else if(ke.getKeyCode() != 16 && ke.getKeyCode()!=20)
			addChar(ke.getKeyChar());

		repaint();
	}

	@Override
	public void drawMe(Graphics g) {
		this.g = g;

		if(visible){
			g.setColor(edgeColor);
			g.fillRect(x, y, width, height);
			if(selected)g.setColor(centreColorSelected);
			else g.setColor(centreColorUnselected);
			g.fillRect(x+Scaler.scale(5), y+Scaler.scale(5), width-Scaler.scale(10), height-Scaler.scale(10));

			ArrayList<BufferedImage> chars = myChars.myChars.getMyChars(text.toString());
			for(int i=0; i<chars.size(); i++){
				BufferedImage img = chars.get(i);
				if(img!=null){
					g.drawImage(img, x+Scaler.scale(5)+img.getWidth()*i, y+((height-Scaler.scale(30))/2), null);
				}
			}

			if(selected){
				g.setColor(Color.BLACK);
				g.drawImage(myChars.myChars.getMyChar('_'), x+Scaler.scale(5)+cursor_pos*Scaler.scale(15), y+((height-Scaler.scale(30))/2), null);
			}
		}
	}

	public void repaint(){
		BufferStrategy bufferStrategy = Gui.Gui.getGuiBufferStrategy();
		Graphics g = bufferStrategy.getDrawGraphics();
		drawMe(g);
		g.dispose();
		bufferStrategy.show();
	}

	public enum Trigger {
		TRIGGER_ON_ENTER, TRIGGER_ON_TEXTINPUT;
	}
}