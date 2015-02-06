package Graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Images{

	static String path, graphics_path;
	static File images_file;
	static ArrayList <ArrayList<BufferedImage>> images;
	static HashMap<String, Rectangle> image_location;
	static HashMap<String, Integer> type_location;
	
	public static void load(String path, String graphics_path){
		Images.path = path;
		Images.graphics_path = graphics_path;
		load();
	}
	
	private static void load(){
		images = new ArrayList<ArrayList<BufferedImage>>();
		image_location = new HashMap<String, Rectangle>();
		type_location = new HashMap<String, Integer>();

		images_file = new File(path + graphics_path);

		Scanner graphics_scanner = null;
		try {graphics_scanner = new Scanner(images_file);} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		
		ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
		ArrayList<String> graphics_names = new ArrayList<String>();
		
		while(graphics_scanner.hasNextLine()){	
			String line = graphics_scanner.nextLine();
			if(line.contains(":")){
				if(!type_location.isEmpty()){
					images.add(temp);
					Images.setGraphic_Location(temp, graphics_names, type_location.size()-1);
				}
				
				temp = new ArrayList<BufferedImage>();
				graphics_names = new ArrayList<String>();
				type_location.put(line.substring(0, line.length() - 1), type_location.size());
			}
			else{
				try {
					temp.add(Scaler.scale(ImageIO.read(new File(path + line))));
					graphics_names.add(line);
			} 
			catch (IOException e){e.printStackTrace();}
			}
		}
		images.add(temp);
		Images.setGraphic_Location(temp, graphics_names, type_location.size()-1);
		
		graphics_scanner.close();
	}
	
	private static void setGraphic_Location(ArrayList<BufferedImage> images, ArrayList<String> names, int x_location){
		if(images.size() != names.size()) System.err.println("Graphics.setGraphic_Location(): invalid parameters(images, names)");
		for(int i = 0; i < images.size(); i++) image_location.put(names.get(i), new Rectangle(x_location, i, 0, 0));
	}
	
	public static ArrayList<BufferedImage> getType_Images(String name){
		return images.get(type_location.get(name));
	}
	
	public static BufferedImage getGraphic(String name){
		return images.get(image_location.get(name).x).get(image_location.get(name).y);
	}
}
