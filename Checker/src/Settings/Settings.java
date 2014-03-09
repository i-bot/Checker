package Settings;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Settings {
	static ArrayList<String> settings;
	static String path, language, ais_path, graphics_path;
	static Dimension windowDimension;
	
	public static void readSettings(String path){
		Settings.path = path;
		readSettings();
	}
	
	private static void readSettings(){
		settings = new ArrayList<String>();
		
		File file = new File(path + "/Settings/Settings.txt");
		
		Scanner scanner = null;
		try {scanner = new Scanner(file);} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		
		while(scanner.hasNext()){
			settings.add(scanner.next());
		}
		scanner.close();
		
		language = settings.get(0);
		windowDimension = new Dimension(Integer.parseInt(settings.get(1).substring(0, settings.get(1).indexOf(":"))), Integer.parseInt(settings.get(1).substring(settings.get(1).indexOf(":")+1)));
		ais_path = path + settings.get(2);
		graphics_path = path + settings.get(3);
	}

	public static String getLanguage(){
		return language;
	}
	
	public static Dimension getWindowDimension(){
		return windowDimension;
	}
	
	public static String getWorlds_Path(){
		return ais_path;
	}
	
	public static String getGraphics_Path(){
		return graphics_path;
	}
	
	public static void changeSetting(int index, String newSetting){
		File file = new File(path+"/Settings/Settings.txt");
		Formatter formatter = null;

		if (file.exists())file.delete();

		try {formatter = new Formatter(file.getPath());} 
		catch (FileNotFoundException e) {e.printStackTrace();}

		settings.set(index, newSetting);

		for(String s : settings){
			formatter.format("%s\r", s);
		}
		formatter.close();
		
		readSettings();
	}

	public static void changeSetting(int index, String newSetting, String toRemove){
		settings.remove(toRemove);
		changeSetting(index, newSetting);
	}
}
