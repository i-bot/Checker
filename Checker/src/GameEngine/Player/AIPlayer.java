package GameEngine.Player;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import AIs.AI;
import GameEngine.AI_Interface.AI_Interface;
import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Color;
import GameEngine.CheckerBoard.Move;
import GameEngine.GameRules.Rule;

public class AIPlayer extends Player{

	private Method method;
	private Class<AI_Interface> loaded;
	private AI ai;

	public AIPlayer(AI ai, Color color_player) {
		super(color_player);

		this.ai = ai;
		this.loaded = null;
		name = ai.getName();
		method = null;
	}

	public AI getAI(){
		return ai;
	}

	public Boolean load(){
		File file = new File(ai.getAI_Location());

		System.out.println("AIPlayer.load(): " + ai.getAI_Location());
		System.out.println("AIPlayer.load(): " + ai.getClass_Location());

		try {
			@SuppressWarnings("resource")
			URLClassLoader ucl = new URLClassLoader(new URL[] { file.toURI().toURL() });
			@SuppressWarnings("unchecked")
			Class<AI_Interface> ai_loaded = (Class<AI_Interface>) ucl.loadClass(ai.getClass_Location());
			loaded = ai_loaded;

			if(ai_loaded.getGenericInterfaces()[0].equals(AI_Interface.class))
				method = ai_loaded.getDeclaredMethod("computeNextMove", CheckerBoard.class, Rule.class, Color.class);
		} 
		catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return false;
		} 

		return true;
	}

	public Move getNextMove(CheckerBoard checkerBoard, Rule rule, Color color){
		try {
			return (Move) method.invoke(loaded.newInstance(), checkerBoard, rule, color);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void clear(){
		movesWithJumps = new ArrayList<>();
	}

	@Override
	public Boolean containsMove(Move m) {
		for(Move moveFromList : movesWithJumps)
			if(moveFromList.equals(m))
				return true;

		return movesWithJumps.size() == 0;
	}

	@Override
	public void executeNextMove(CheckerBoard checkerBoard, Rule rule, Player enemy) throws InterruptedException {
		long end = System.currentTimeMillis() + 1000;
		Move aiMove = getNextMove(checkerBoard.clone(), rule.clone(), this.getColor_Player());
		while(System.currentTimeMillis() < end)
			Thread.sleep(1);
		if(checkerBoard.executeMove(aiMove, rule, this))
			enemy.handleEnemyMove(aiMove);
		else checkerBoard.executeMove(getRandomMove(rule), rule, this);
	}
	
	private Move getRandomMove(Rule rule){
		ArrayList<Move> allPossibleMoves = rule.getMovesWithJumps(color_player);
		allPossibleMoves.addAll(rule.getNormalMoves(color_player));
		return allPossibleMoves.get((int) (Math.random() * allPossibleMoves.size()));
	}

	@Override
	public void handleEnemyMove(Move move_enemy) {

	}
}
