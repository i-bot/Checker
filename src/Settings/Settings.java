package Settings;

import java.util.ArrayList;

import FileIO.FileIO;

public class Settings {
	private static FileIO fileIO;
	private static ArrayList<String> settings;
	private static String path, gui_settings_path, ais_path, game_path;
	private static int jump_wait_time, ai_min_time, ai_max_time;
	
	static {
		fileIO = new FileIO();
	}
	
	public static void readSettings(String path){
		Settings.path = path;
		readSettings();
	}
	
	private static void readSettings(){
		settings = fileIO.read(path + "/Settings/Settings.txt");
		
		gui_settings_path = settings.get(0);
		ais_path = path + settings.get(1);
		game_path = path + settings.get(2);
		jump_wait_time = Integer.parseInt(settings.get(3));
		ai_min_time = Integer.parseInt(settings.get(4));
		ai_max_time = Integer.parseInt(settings.get(5));
	}
	
	public static String getGui_Settings_Path() {
		return gui_settings_path;
	}
	
	public static String getAIs_Path(){
		return ais_path;
	}
	
	public static String getGame_Path(){
		return game_path;
	}
	
	public static int getJump_Wait_Time() {
		return jump_wait_time;
	}

	public static int getAI_Min_Time(){
		return ai_min_time;
	}
	
	public static int getAI_Max_Time(){
		return ai_max_time;
	}

	public static Boolean changeSetting(String toRemove, String newSetting){
		int index = settings.indexOf(toRemove);
		
		if(index == -1) return false;
		
		settings.set(index, newSetting);
		
		fileIO.write(newSetting, settings);
		
		readSettings();
		
		return true;
	}
}
