package myChars;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Graphics.Scaler;

public class myChars {
	private static BufferedImage allChars;
	static HashMap<Character, BufferedImage> chars;
	private static char[][] pattern = {
		{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'}, 
		{'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'},
		{'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D'},
		{'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'},
		{'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X'},
		{'Y', 'Z', '(', ')', '0', '1', '2', '3', '4', '5'},
		{'6', '7', '8', '9', '+', '-', '*', '/', '=', ':'},
		{';', '"', '\'', '?', ',', '.', '_', '#', '~', '!'},
		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
	};

	public static void initializeChars(){
		chars = new HashMap<Character, BufferedImage>();
		try {
			if(Graphics.Scaler.scale(30) == 30)allChars = ImageIO.read(myChars.class.getClassLoader().getResourceAsStream("myChars/chars_1600_900.png"));
			if(Graphics.Scaler.scale(30) == 36)allChars = ImageIO.read(myChars.class.getClassLoader().getResourceAsStream("myChars/chars_1920_1080.png"));
			if(Graphics.Scaler.scale(30) == 24)allChars = ImageIO.read(myChars.class.getClassLoader().getResourceAsStream("myChars/chars_1280_720.png"));
		} 
		catch (IOException e) {e.printStackTrace();}

		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 10; x++){
				chars.put(pattern[y][x], allChars.getSubimage(Scaler.scale(15 * x), Scaler.scale(30 * y), Scaler.scale(15), Scaler.scale(30)));
			}
		}
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
