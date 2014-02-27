package myChars;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Graphics.Scaler;

public class myChars {
	public static BufferedImage allChars;
	static HashMap<Character, BufferedImage> chars;
	
	public static void initializeChars(){
		chars = new HashMap<Character, BufferedImage>();
		try {
			if(Graphics.Scaler.scale(30) == 30)allChars = ImageIO.read(myChars.class.getClassLoader().getResourceAsStream("myChars/chars_1600_900.png"));
			if(Graphics.Scaler.scale(30) == 36)allChars = ImageIO.read(myChars.class.getClassLoader().getResourceAsStream("myChars/chars_1920_1080.png"));
			if(Graphics.Scaler.scale(30) == 24)allChars = ImageIO.read(myChars.class.getClassLoader().getResourceAsStream("myChars/chars_1280_720.png"));
		} 
		catch (IOException e) {e.printStackTrace();}
		
		chars.put('a', allChars.getSubimage(Scaler.scale(0), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30)));
		chars.put('b', allChars.getSubimage(Scaler.scale(15), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30)));
		chars.put('c', allChars.getSubimage(Scaler.scale(30), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('d', allChars.getSubimage(Scaler.scale(45), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30)));
		chars.put('e', allChars.getSubimage(Scaler.scale(60), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('f', allChars.getSubimage(Scaler.scale(75), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('g', allChars.getSubimage(Scaler.scale(90), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('h', allChars.getSubimage(Scaler.scale(105), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30)));
		chars.put('i', allChars.getSubimage(Scaler.scale(120), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30)));
		chars.put('j', allChars.getSubimage(Scaler.scale(135), Scaler.scale(0), Scaler.scale(15), Scaler.scale(30)));
		
		chars.put('k', allChars.getSubimage(Scaler.scale(0), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30)));
		chars.put('l', allChars.getSubimage(Scaler.scale(15), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('m', allChars.getSubimage(Scaler.scale(30), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30)));
		chars.put('n', allChars.getSubimage(Scaler.scale(45), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('o', allChars.getSubimage(Scaler.scale(60), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30)));
		chars.put('p', allChars.getSubimage(Scaler.scale(75), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30)));
		chars.put('q', allChars.getSubimage(Scaler.scale(90), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30)));
		chars.put('r', allChars.getSubimage(Scaler.scale(105), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('s', allChars.getSubimage(Scaler.scale(120), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('t', allChars.getSubimage(Scaler.scale(135), Scaler.scale(30), Scaler.scale(15), Scaler.scale(30))); 
		
		chars.put('u', allChars.getSubimage(Scaler.scale(0), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('v', allChars.getSubimage(Scaler.scale(15), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('w', allChars.getSubimage(Scaler.scale(30), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30)));
		chars.put('x', allChars.getSubimage(Scaler.scale(45), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('y', allChars.getSubimage(Scaler.scale(60), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('z', allChars.getSubimage(Scaler.scale(75), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('A', allChars.getSubimage(Scaler.scale(90), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('B', allChars.getSubimage(Scaler.scale(105), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('C', allChars.getSubimage(Scaler.scale(120), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('D', allChars.getSubimage(Scaler.scale(135), Scaler.scale(60), Scaler.scale(15), Scaler.scale(30))); 
		
		chars.put('E', allChars.getSubimage(Scaler.scale(0), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('F', allChars.getSubimage(Scaler.scale(15), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('G', allChars.getSubimage(Scaler.scale(30), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('H', allChars.getSubimage(Scaler.scale(45), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('I', allChars.getSubimage(Scaler.scale(60), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('J', allChars.getSubimage(Scaler.scale(75), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('K', allChars.getSubimage(Scaler.scale(90), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('L', allChars.getSubimage(Scaler.scale(105), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('M', allChars.getSubimage(Scaler.scale(120), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('N', allChars.getSubimage(Scaler.scale(135), Scaler.scale(90), Scaler.scale(15), Scaler.scale(30))); 
		
		chars.put('O', allChars.getSubimage(Scaler.scale(0), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('P', allChars.getSubimage(Scaler.scale(15), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('Q', allChars.getSubimage(Scaler.scale(30), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('R', allChars.getSubimage(Scaler.scale(45), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('S', allChars.getSubimage(Scaler.scale(60), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('T', allChars.getSubimage(Scaler.scale(75), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('U', allChars.getSubimage(Scaler.scale(90), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('V', allChars.getSubimage(Scaler.scale(105), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('W', allChars.getSubimage(Scaler.scale(120), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('X', allChars.getSubimage(Scaler.scale(135), Scaler.scale(120), Scaler.scale(15), Scaler.scale(30))); 
		
		chars.put('Y', allChars.getSubimage(Scaler.scale(0), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('Z', allChars.getSubimage(Scaler.scale(15), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('(', allChars.getSubimage(Scaler.scale(30), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put(')', allChars.getSubimage(Scaler.scale(45), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('0', allChars.getSubimage(Scaler.scale(60), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('1', allChars.getSubimage(Scaler.scale(75), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('2', allChars.getSubimage(Scaler.scale(90), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('3', allChars.getSubimage(Scaler.scale(105), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('4', allChars.getSubimage(Scaler.scale(120), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('5', allChars.getSubimage(Scaler.scale(135), Scaler.scale(150), Scaler.scale(15), Scaler.scale(30))); 
		
		chars.put('6', allChars.getSubimage(Scaler.scale(0), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('7', allChars.getSubimage(Scaler.scale(15), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('8', allChars.getSubimage(Scaler.scale(30), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('9', allChars.getSubimage(Scaler.scale(45), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('+', allChars.getSubimage(Scaler.scale(60), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('-', allChars.getSubimage(Scaler.scale(75), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('*', allChars.getSubimage(Scaler.scale(90), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('/', allChars.getSubimage(Scaler.scale(105), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('=', allChars.getSubimage(Scaler.scale(120), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		chars.put(':', allChars.getSubimage(Scaler.scale(135), Scaler.scale(180), Scaler.scale(15), Scaler.scale(30))); 
		
		chars.put(';', allChars.getSubimage(Scaler.scale(0), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('"', allChars.getSubimage(Scaler.scale(15), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('\'', allChars.getSubimage(Scaler.scale(30), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('?', allChars.getSubimage(Scaler.scale(45), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put(',', allChars.getSubimage(Scaler.scale(60), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('.', allChars.getSubimage(Scaler.scale(75), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('_', allChars.getSubimage(Scaler.scale(90), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('#', allChars.getSubimage(Scaler.scale(105), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('~', allChars.getSubimage(Scaler.scale(120), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30))); 
		chars.put('!', allChars.getSubimage(Scaler.scale(135), Scaler.scale(210), Scaler.scale(15), Scaler.scale(30)));
		
		chars.put(' ', allChars.getSubimage(Scaler.scale(0), Scaler.scale(240), Scaler.scale(15), Scaler.scale(30)));
	}
	
	public static BufferedImage getMyChar(char c){
		if(chars.containsKey(c))return (chars.get(c));
		else return (chars.get('~'));
	}
	
	public static ArrayList<BufferedImage> getMyChars(String s){
		char[] cr = s.toCharArray();
		ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
		
		for(int i=0; i<cr.length; i++){
			imgs.add(getMyChar(cr[i]));
		}
		return imgs;
	}
}
