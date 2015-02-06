package Main;

import Engine.Loader;
import Game.GameLoop;
import Gui.Gui;
import Gui.SwingGui;

public class Main {

	private static String path;
	private static GameLoop gameLoop;
	private static Gui gui;

	static {
		path = Main.class.getResource("Main.class").getPath();
		if(path.contains("file:/") && path.contains(".jar!")){
//		Program uses this path if it is exported as a .jar
			path = Main.class.getResource("Main.txt").getPath();
			path = path.replaceFirst("file:", "");
			path = path.substring(0, path.indexOf(".jar!"));
			path = path.substring(0, path.lastIndexOf("/"));
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
		
		gui = new SwingGui();

		Thread guiThread = new Thread(gui);
		guiThread.start();
		
		while(true){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gui.repaint();
		}
	}

	public static String getPath(){
		return path;
	}
	
	public static void setGameLoop(GameLoop gameLoop){
		Main.gameLoop = gameLoop;
	}
	
	public static GameLoop getGameLoop(){
		System.out.println("Main.getGameLoop()" + gameLoop);
		return gameLoop;
	}
	
	public static GameLoop reloadGameLoop(){
		gameLoop.interrupt();
		try {
			gameLoop.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameLoop = new GameLoop();
		return gameLoop;
	}
}
