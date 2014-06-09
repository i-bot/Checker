package GameEngine.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import GameEngine.AI_Interface.AI_Interface;
import GameEngine.CheckerBoard.CheckerBoard;
import GameEngine.CheckerBoard.Color;
import GameEngine.CheckerBoard.Move;
import GameEngine.GameRules.Rule;
import VariableLocker.VariableLocker;

public class AIMoveGetter implements Runnable{
	private VariableLocker<Move> vl_aiMove;
	
	private Method ai_computeNextMove;
	private AI_Interface ai_Interface;
	private CheckerBoard checkerBoard;
	private Rule rule;
	private Color color;
	
	public void init(Method ai_computeNextMove, AI_Interface ai_Interface, CheckerBoard checkerBoard, Rule rule, Color color){
		this.ai_computeNextMove = ai_computeNextMove;
		this.ai_Interface = ai_Interface;
		this.checkerBoard = checkerBoard;
		this.rule = rule;
		this.color = color;
		
		vl_aiMove = new VariableLocker<Move>(null);
	}
	
	@Override
	public void run() {
		try {
			Move m = (Move) ai_computeNextMove.invoke(ai_Interface, checkerBoard, rule, color);
			vl_aiMove.set(m);
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public Move getAIMove(){
		return vl_aiMove.get();
	}
}
