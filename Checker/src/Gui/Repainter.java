package Gui;

public class Repainter implements Runnable{
	
	@Override
	public void run() {
		while(true){
			try {Thread.sleep(10);} 
			catch (InterruptedException e) {e.printStackTrace();}
			if(KeyInput.Window.windowHasBeenDeiconified){
				System.out.println("Repainter.run(): repaint");
				KeyInput.Window.windowHasBeenDeiconified = false;
				Gui.repaintScreen();
			}
		}
	}
}