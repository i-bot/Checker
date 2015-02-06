package Gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Engine.MenuHandler;
import FileIO.FileIO;
import KeyInput.MousePoint;
import KeyInput.Window;
import Main.Main;
import Settings.Settings;
import VariableLocker.VariableLocker;
import Graphics.Images;
import Graphics.Scaler;
import GuiMenus.AIMenu;
import GuiMenus.DeleteAIMenu;
import GuiMenus.GameMenu;
import GuiMenus.LocalGameMenu;
import GuiMenus.OptionsMenu;
import GuiMenus.StartMenu;

@SuppressWarnings("serial")
public class SwingGui extends JFrame implements Gui{
	private FileIO fileIO;
	private BufferStrategy bufferStrategy;
	private Canvas canvas;
	private VariableLocker<Boolean> vl_hasLoaded = new VariableLocker<Boolean>(false);

	private Window windowListener;
	
	private MenuHandler menuHandler;
	private BufferedImage background;

	private String path, language, graphics_path;
	private Dimension windowDimension;

	public SwingGui(){
		super("Checker");

		vl_hasLoaded.set(false);

		path = Main.getPath();
		fileIO = new FileIO();
		readSettings(path);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(windowDimension.width, windowDimension.height);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
		setResizable(false);

		try {
			BufferedImage before = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Graphics/menu_background/Menu_Background.png"));
			Image scaled = before.getScaledInstance(windowDimension.width, windowDimension.height, Image.SCALE_SMOOTH);
			background = new BufferedImage(windowDimension.width, windowDimension.height, BufferedImage.TYPE_INT_RGB);
			background.getGraphics().drawImage(scaled, 0, 0 , null);
		} 
		catch (IOException e) {e.printStackTrace();}

		makeCanvasAndBufferStrategy();
		repaint();
	}

	public void readSettings(String path){
		ArrayList<String> settings = fileIO.read(path + Settings.getGui_Settings_Path());

		language = settings.get(0);

		String s_windowDimension = settings.get(1);
		windowDimension = new Dimension(Integer.parseInt(s_windowDimension.substring(0, s_windowDimension.indexOf(":"))), Integer.parseInt(s_windowDimension.substring(s_windowDimension.indexOf(":") + 1)));

		graphics_path = settings.get(2);
	}

	public void addListeners(){
		canvas.addMouseListener(new KeyInput.Mouse(this));
		canvas.addKeyListener(new KeyInput.Keyboard(this));
		windowListener = new Window();
		addWindowListener(windowListener);
	}

	public void makeCanvasAndBufferStrategy(){
		canvas = new Canvas();
		canvas.setBounds(0, 0, windowDimension.width, windowDimension.height);
		this.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		addListeners();
	}

	public void initMenuHandler(){
		menuHandler = new MenuHandler(this);
		menuHandler.putMenu(1, new StartMenu());
		menuHandler.putMenu(3, new OptionsMenu());
		menuHandler.putMenu(2, new AIMenu());
		menuHandler.putMenu(11, new DeleteAIMenu());
		menuHandler.putMenu(5, new LocalGameMenu());
		menuHandler.putMenu(19, new GameMenu(Main.getGameLoop()));
	}

	public synchronized void repaint(){
		Graphics g = bufferStrategy.getDrawGraphics();
		draw(g);
		g.dispose();
		bufferStrategy.show();
	}

	private void draw(Graphics g){
		if(!vl_hasLoaded.get())g.drawImage(background, 0, 0, null);
		else {
			if(menuHandler == null) initMenuHandler(); 
			menuHandler.drawMe(g);
		}
	}

	public void checkMouseInput(MousePoint point){
		menuHandler.checkMouseInput(point);
	}

	public void checkKeyboardInput(KeyEvent e) {
		menuHandler.checkKeyboardInput(e);
	}

	@Override
	public void run() {
		Scaler.initialize(windowDimension);
		Images.load(path, graphics_path);
		myChars.myChars.initializeChars();
		Languages.Languages.setLanguage(language);
		vl_hasLoaded.set(true);
		repaint();
	}
}
