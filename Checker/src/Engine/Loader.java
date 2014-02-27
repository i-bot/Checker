package Engine;

import Gui.Gui;

public class Loader implements Runnable{

	String path;
	
	@Override
	public void run() {
		path = Main.Main.getPath();
				
		Graphics.Scaler.initialize(Settings.Settings.getWindowDimension());
		Graphics.Images.load(path);
		myChars.myChars.initializeChars();
		Languages.Languages.setLanguage(Settings.Settings.getLanguage());
		AIs.AIs.readAIs(path);
		
		
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gui.hasLoaded = true;
		Gui.repaintScreen();
	}
}
