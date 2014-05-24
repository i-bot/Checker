package Gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Gui extends JFrame{
	private static BufferStrategy bufferStrategy;
	private static Canvas canvas;
	public static Boolean hasLoaded = false;
	static BufferedImage background;
	static Thread repainterThread;
	
	public Gui(){
		super("Jump & Run");
	
		try {
			BufferedImage before = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Graphics/menu_background/Menu_Background.png"));
			Image scaled = before.getScaledInstance(Settings.Settings.getWindowDimension().width, Settings.Settings.getWindowDimension().height, Image.SCALE_SMOOTH);
			background = new BufferedImage(Settings.Settings.getWindowDimension().width, Settings.Settings.getWindowDimension().height, BufferedImage.TYPE_INT_RGB);
			background.getGraphics().drawImage(scaled, 0, 0 , null);
		} 
		catch (IOException e) {e.printStackTrace();}
	
		Repainter repainter = new Repainter();
		repainterThread = new Thread(repainter);
	}
	
	public void addListeners(){
		canvas.addMouseListener(new KeyInput.Mouse());
		canvas.addKeyListener(new KeyInput.Keyboard());
		addWindowListener(new KeyInput.Window());
	}
	
	public void makeCanvasAndBufferStrategy(){
		canvas = new Canvas();
		canvas.setBounds(0, 0, Settings.Settings.getWindowDimension().width, Settings.Settings.getWindowDimension().height);
		this.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		addListeners();

		repaintScreen();
		repainterThread.start();
		repaintScreen();
	}
	
	public static void repaintScreen(){
		Graphics g = bufferStrategy.getDrawGraphics();
		draw(g);
		g.dispose();
		bufferStrategy.show();
	}
	
	private static void draw(Graphics g){
		if(!hasLoaded)g.drawImage(background, 0, 0, null);
		else Engine.MenuHandler.drawMe(g);
	}
	
	public static BufferStrategy getGuiBufferStrategy(){
		return bufferStrategy;
	}
}
