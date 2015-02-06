package Game.Player;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import AIs.AI;
import Game.AI_Interface.AI_Interface;
import Game.CheckerBoard.CheckerBoard;
import Game.CheckerBoard.Color;
import Game.CheckerBoard.Move;
import Game.GameRules.Rule;

public class AIPlayer extends Player{

	private Method method;
	//private Class<AI_Interface> loaded;
	private AI_Interface loaded;
	private AI ai;
	private AIMoveGetter aiMoveGetter;

	public AIPlayer(AI ai, Color color_player) {
		super(color_player);

		this.ai = ai;
		this.loaded = null;
		name = ai.getName();
		method = null;

		aiMoveGetter = new AIMoveGetter();
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
			loaded = ai_loaded.newInstance();

			if(ai_loaded.getGenericInterfaces()[0].equals(AI_Interface.class))
				method = ai_loaded.getDeclaredMethod("computeNextMove", CheckerBoard.class, Rule.class, Color.class);
		} 
		catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return true;
	}

	@SuppressWarnings("deprecation")
	public Move getNextMove(CheckerBoard checkerBoard, Rule rule, Color color) throws InterruptedException{
		Move ai_move = null;
		long end_min_time = System.currentTimeMillis() + Settings.Settings.getAI_Min_Time();
		long end_max_time = System.currentTimeMillis() + Settings.Settings.getAI_Max_Time();

		aiMoveGetter.init(method, loaded, checkerBoard, rule, color);

		Thread thread = new Thread(aiMoveGetter);

		thread.start();

		long sleep_time = end_min_time - System.currentTimeMillis(); 
		if ( sleep_time > 0 )
			Thread.sleep(sleep_time);

		ai_move = aiMoveGetter.getAIMove();

		while(end_max_time > System.currentTimeMillis() && ai_move == null){
			ai_move = aiMoveGetter.getAIMove();
			Thread.sleep(1);
		}
		ai_move = aiMoveGetter.getAIMove();

		thread.interrupt();
		thread.stop();

		return ai_move;
	}

	public void clear() {
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
	public Move getAndExecuteNextMove(CheckerBoard checkerBoard, Rule rule) throws InterruptedException {
		Move aiMove = getNextMove(checkerBoard.clone(), rule.clone(), getColor());

		if(aiMove == null || !rule.checkMove(aiMove)){
			aiMove = getRandomMove(rule);
		}

		Move t_aiMove = aiMove.clone();
		checkerBoard.executeMoveWithRepainting(aiMove, rule, this);
		return t_aiMove;
	}

	@Override
	public void handleEnemyMove(Move move_enemy) {
//		System.out.println("AIPlayer.handleEnemyMove(): Enemy made this move: " + move_enemy);
	}
}
