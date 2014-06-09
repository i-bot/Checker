package AIs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class AIs {
	private static int n_ais;
	private static String path;
	private static File ais_file;
	private static ArrayList<AI> ais;
	private static ArrayList<String> filepaths;

	public static void readAIs(String path){	
		AIs.path = path;
		readAIs();
	}
	
	private static void readAIs(){	
		filepaths = new ArrayList<String>();
		
		ais_file = new File(Settings.Settings.getAIs_Path());

		Scanner scanner = null;
		try {scanner = new Scanner(ais_file);} 
		catch (FileNotFoundException e) {e.printStackTrace();}

		if(scanner.hasNextLine())
			n_ais = Integer.parseInt(scanner.nextLine());

		while(scanner.hasNextLine())
			filepaths.add(scanner.nextLine());
		
		scanner.close();

		ais = new ArrayList<AI>();
		for(String s : filepaths){			
			File f = new File(path + s);
			String ai_location = "", class_location = "", name = "", version = "", date = "", author = "";
			Boolean changeable = false; 

			try {scanner = new Scanner(f);} 
			catch (FileNotFoundException e) {e.printStackTrace();}

			if(scanner.hasNextLine())ai_location = path + scanner.nextLine();
			if(scanner.hasNextLine())class_location = scanner.nextLine();
			if(scanner.hasNextLine())changeable = Boolean.parseBoolean(scanner.nextLine());
			if(scanner.hasNextLine())name = scanner.nextLine();
			if(scanner.hasNextLine())version = scanner.nextLine();
			if(scanner.hasNextLine())date = scanner.nextLine();
			if(scanner.hasNextLine())author = scanner.nextLine();
						
			ais.add(new AI(s, name, changeable, ai_location, class_location, version, date, author));
			scanner.close();
		}
	}

	public static void deleteAI(AI aiToDelete){
		String filepath = aiToDelete.getFilepath();
		
		n_ais--;
		filepaths.remove(filepath);
		
		File file = new File(path + filepath);
		deleteFolder(file);
		
		Formatter formatter = null;
		try {formatter = new Formatter(ais_file.getPath());} 
		catch (FileNotFoundException e) {e.printStackTrace();}

		formatter.format("%s", n_ais);
		for(String s : filepaths){
			formatter.format("\r%s", s);
		}
		formatter.close();

		readAIs();
	}
	
	private static Boolean deleteFolder(File dir){
		if (dir.isDirectory()){
			File[] files = dir.listFiles();
			for (File f: files){
				deleteFolder(f);
			}
		}
		return dir.delete();
	}

	public static AI getAI(int index){
		return ais.get(index);
	}
	
	public static ArrayList<AI> getAIs(){
		return ais;
	}

	public static Boolean containsFilepath(String filepath){
		return filepaths.contains(filepath);
	}

	public static Integer getAI_Number(){
		return n_ais;
	}
}
