package Main;

import javax.swing.JFrame;

import Engine.Loader;
import GameEngine.GameEngine;
import Gui.Gui;

public class Main {

	private static String path;
	private static GameEngine gameEngine;

	static{
		path = Main.class.getResource("Main.class").getPath();
		if(path.contains("file:/") && path.contains(".jar!")){
//		Program uses this path if it is exported as a .jar
			path = Main.class.getResource("Main.txt").getPath();
			path = path.replaceFirst("file:", "");
			path = path.substring(0, path.indexOf(".jar!"));
			path = path.substring(0, path.lastIndexOf("/"));
			System.out.println(path);
		}
		else{
//		Program uses this path if it runs on eclipse
			path = "src";
		}
	}

	public static void main(String[] args) {
		Settings.Settings.readSettings(path);
		
		Loader loader = new Loader();
		Thread loaderThread = new Thread(loader);
		loaderThread.start();
		
		Gui gui = new Gui();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize((int) Settings.Settings.getWindowDimension().getWidth(), (int) Settings.Settings.getWindowDimension().getHeight());
		gui.setUndecorated(true);
		gui.setVisible(true);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);

		gui.makeCanvasAndBufferStrategy();
		gui.repaint();
	}

	public static String getPath(){
		return path;
	}
	
	public static void setGameEngine(GameEngine gameEngine){
		Main.gameEngine = gameEngine;
	}
	
	public static GameEngine getGameEngine(){
		System.out.println("Main.getGameEngine()" + gameEngine);
		return gameEngine;
	}
	
	public static GameEngine reloadGameEngine(){
		gameEngine.interrupt();
		try {
			gameEngine.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameEngine = new GameEngine();
		return gameEngine;
	}
}
