package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class FileIO {
	
	public ArrayList<String> read(String path){
		ArrayList<String> lines = new ArrayList<String>();
		
		File file = new File(path);
		
		Scanner scanner = null;
		try {scanner = new Scanner(file);} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		
		while(scanner.hasNext()){
			lines.add(scanner.next());
		}
		scanner.close();
		
		return lines;
	}
	
	public void write(String path, ArrayList<String> lines){
		File file = new File(path);
		Formatter formatter = null;

		if(file.exists()) file.delete();

		try {formatter = new Formatter(file.getPath());} 
		catch (FileNotFoundException e) {e.printStackTrace();}

		for(String line : lines){
			formatter.format("%s\r", line);
		}
		formatter.close();
	}
}
